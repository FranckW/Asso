package franck.asso.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import franck.asso.R;
import franck.asso.model.Member;

/**
 * Created by franc on 22/10/2015.
 */
public class MemberActivity extends Activity {
    protected Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        member = getIntent().getExtras().getParcelable("member");
        TextView genderText = (TextView) findViewById(R.id.memberGender);
        TextView firstNameText = (TextView) findViewById(R.id.memberFirstName);
        TextView lastNameText = (TextView) findViewById(R.id.memberLastName);
        TextView birthDateText = (TextView) findViewById(R.id.memberBirthDate);
        TextView emailText = (TextView) findViewById(R.id.memberEmail);
        TextView phoneText = (TextView) findViewById(R.id.memberPhoneNumber);
        TextView addressText = (TextView) findViewById(R.id.memberAddress);
        String genderPrefix;
        if (member.getGender()) {
            genderPrefix = "Mr.";
        } else {
            genderPrefix = "Mrs.";
        }
        genderText.setText(genderPrefix);
        firstNameText.setText(member.getFirstName());
        lastNameText.setText(member.getLastName());
        birthDateText.setText(member.getBirthDate());
        emailText.setText(member.getEmail());
        phoneText.setText(member.getPhoneNumber());
        addressText.setText(member.getAddress());
    }

    /**
     * Called when the user touches the send an email button. Allow user to send email to members using his prefered email service
     */
    public void sendAnEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", member.getEmail(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }

    /**
     * Called when the user touches the call button. Allow user to call the member using phone, skype etc...
     */
    public void call(View view) {
        String number = "tel:" + member.getPhoneNumber().trim();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
        startActivity(callIntent);
    }
    
    /**
     * Called when the user touches the back button
     */
    public void backToList(View view) {
        Intent intent = new Intent(this, MembersListActivity.class);
        startActivity(intent);
    }
}
