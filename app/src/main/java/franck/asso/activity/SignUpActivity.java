package franck.asso.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

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
}
