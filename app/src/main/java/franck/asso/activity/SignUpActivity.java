package franck.asso.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;

import franck.asso.R;

/**
 * Created by franc on 15/10/2015.
 */
public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onGenderButtonClicked(View view) {
        RadioButton maleButton = (RadioButton) findViewById(R.id.radioButtonMale);
        RadioButton femaleButton = (RadioButton) findViewById(R.id.radioButtonFemale);
        if (femaleButton.isPressed()) {
            maleButton.setChecked(false);
        } else if (maleButton.isPressed()) {
            femaleButton.setChecked(false);
        }
    }

    public void signUpValidation(View view) {
        RadioButton maleButton = (RadioButton) findViewById(R.id.radioButtonMale);
        RadioButton femaleButton = (RadioButton) findViewById(R.id.radioButtonFemale);
        EditText firstNameInput = (EditText) findViewById(R.id.signUpFirstName);
        EditText lastNameInput = (EditText) findViewById(R.id.signUpLastName);
        EditText passwordInput = (EditText) findViewById(R.id.signUpPassword);
        EditText birthDateInput = (EditText) findViewById(R.id.signUpBirthDate);
        EditText emailInput = (EditText) findViewById(R.id.signUpEmail);
        EditText phoneInput = (EditText) findViewById(R.id.signUpPhone);
        EditText addressInput = (EditText) findViewById(R.id.signUpAddress);
        boolean gender = maleButton.isPressed();
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String birthDate = birthDateInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String address = addressInput.getText().toString();
        if ((maleButton.isPressed() == false && femaleButton.isPressed() == false) || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() ||
                birthDate.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Please fill all the fields");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        } else {
            /*Check all the fields (mail no taken)*/

            /*Add to database*/
        }
    }
}
