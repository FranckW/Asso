package franck.asso.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        EditText firstNameInput = (EditText) findViewById(R.id.signUpFirstName);
        EditText lastNameInput = (EditText) findViewById(R.id.signUpLastName);
        EditText passwordInput = (EditText) findViewById(R.id.signUpPassword);
        EditText birthDateInput = (EditText) findViewById(R.id.signUpBirthDate);
        EditText emailInput = (EditText) findViewById(R.id.signUpEmail);
        EditText phoneInput = (EditText) findViewById(R.id.signUpPhone);
        EditText addressInput = (EditText) findViewById(R.id.signUpAddress);
        boolean boolGender = maleButton.isPressed();
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String birthDate = birthDateInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String address = addressInput.getText().toString();
        //To match the rest api
        String gender;
        if (boolGender) {
            gender = "male";
        } else gender = "female";
        if (this.checkEmailAvailability(email)) {
            this.addMember(gender, firstName, lastName, password, birthDate, email, phone, address);
        } else {
            Toast.makeText(getApplicationContext(), "Email already taken. Please choose another one", Toast.LENGTH_LONG).show();
        }
    }

    private class EmailAvailableHolder {
        boolean isAvailable;
    }

    public boolean checkEmailAvailability(String email) {
        final EmailAvailableHolder checkEmailHolder = new EmailAvailableHolder();
        checkEmailHolder.isAvailable = false;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://appli.yourtribes-soft.com:1701/emailIsAvailable/" + email;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            checkEmailHolder.isAvailable = true;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while checking email availability, check your network", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
        return checkEmailHolder.isAvailable;
    }

    public void addMember(String gender, String firstName, String lastName, String password, String birthdate, String email, String phone, String address) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://appli.yourtribes-soft.com:1701/addMember/" + gender + "/" + firstName + "/" + lastName + "/" + password + "/" + birthdate + "/" + email + "/" + phone + "/" + address;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //It is ok, there is nothing to do
                        Toast.makeText(getApplicationContext(), "Account successfully created !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while creating member, check your network", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}
