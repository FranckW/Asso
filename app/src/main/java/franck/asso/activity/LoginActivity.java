package franck.asso.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import franck.asso.R;
import franck.asso.model.User;

/**
 * Created by franc on 15/10/2015.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view) {
        EditText login = (EditText) findViewById(R.id.inputLogin);
        EditText password = (EditText) findViewById(R.id.inputPassword);
        /*tester les login et password dans la base de données pour voir si ça correspond
           à un utilisateur, sinon, rester sur cette page*/
        User.getInstance().setLogin(login.getText().toString());
        User.getInstance().setPassword(password.getText().toString());
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}
