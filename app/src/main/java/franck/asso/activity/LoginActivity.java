package franck.asso.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import franck.asso.R;
import franck.asso.model.User;
import franck.asso.utils.Utility;

/**
 * Created by franc on 15/10/2015.
 */
public class LoginActivity extends Activity {
    protected static String login;
    protected static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view) {
        EditText loginInput = (EditText) findViewById(R.id.inputLogin);
        EditText passwordInput = (EditText) findViewById(R.id.inputPassword);
        login = loginInput.getText().toString();
        password = passwordInput.getText().toString();
        this.doLogin(login, password);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void doLogin(String login, String password) {
        if (Utility.validateEmail(login)) {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://appli.yourtribes-soft.com:1701/loginMatchesPassword/" + login + "/" + password;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("true")) {
                                User.getInstance().setLogin(LoginActivity.login);
                                User.getInstance().setPassword(LoginActivity.password);
                                Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Incorrect login or password", Toast.LENGTH_LONG).show();
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error while logging in, check your network", Toast.LENGTH_LONG).show();
                }
            });
            queue.add(stringRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_LONG).show();
        }
    }

}
