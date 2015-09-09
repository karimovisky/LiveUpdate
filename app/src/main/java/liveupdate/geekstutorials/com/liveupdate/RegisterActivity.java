package liveupdate.geekstutorials.com.liveupdate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mUserEmail;
    protected EditText mUserPassword;
    protected Button mRegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "waMH893fzAzWWkv3uRs2eMhT8BI2jvXRjEClFUmG", "j99Ce402LkEUuC1FhOyNVkrMXBHJAuTAL25CFQMv");

        mUsername = (EditText) findViewById(R.id.usernameRegisterEditText);
        mUserEmail = (EditText) findViewById(R.id.emailRegisterEditText);
        mUserPassword = (EditText) findViewById(R.id.passwordRegisterEditText);
        mRegisterButton = (Button) findViewById(R.id.registerButton);

        //listen to register button click
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get the username, password and email Convert them to string
                String username = mUsername.getText().toString().trim();
                String email = mUserEmail.getText().toString().trim();
                String password = mUserPassword.getText().toString().trim();

                //store user in parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //user signed up success
                            Toast.makeText(getBaseContext(), "Success Welcome", Toast.LENGTH_SHORT).show();
                            //take user home page
                            Intent takeuserHome = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(takeuserHome);
                        } else {
                            //error signing up user. advice user
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
