package franck.asso.activity;

import android.app.Activity;
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
        RadioButton femaleButton = (RadioButton) findViewById(R.id.radioButtonFemale);
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
        if ((maleButton.isPressed() == false && femaleButton.isPressed() == false) || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() ||
                birthDate.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
        } else {
            //To match the rest api
            String gender;
            if (boolGender) {
                gender = "male";
            } else gender = "female";
            this.addMember(gender, firstName, lastName, password, birthDate, email, phone, address);
        }
    }

    public void addMember(String gender, String firstName, String lastName, String password, String birthdate, String email, String phone, String address) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://vps212972.ovh.net/yourTribes-api/checkEmail?email=" + email;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            //email available, let's send a new request to create the member
                            RequestQueue addMemberQueue = Volley.newRequestQueue(getApplicationContext());
                            String url = "http://vps212972.ovh.net/yourTribes-api/createMember";
                            StringRequest addMemberStringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Error while creating member, check your network", Toast.LENGTH_LONG).show();
                                }
                            });
                            addMemberQueue.add(addMemberStringRequest);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error while creating member, check your network", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while checking email availability, check your network", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}
