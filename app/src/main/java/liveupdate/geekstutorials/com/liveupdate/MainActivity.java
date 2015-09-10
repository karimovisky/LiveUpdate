package liveupdate.geekstutorials.com.liveupdate;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseUser;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Parse.initialize(this, "waMH893fzAzWWkv3uRs2eMhT8BI2jvXRjEClFUmG", "j99Ce402LkEUuC1FhOyNVkrMXBHJAuTAL25CFQMv");
        }
        catch (Exception e) {
        }
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
        } else {
            // show the signup or login screen
            Intent takeUserToLoginActivity = new Intent(this, LoginActivity.class);
            startActivity(takeUserToLoginActivity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.updateStatus:
                //take user to update status activity
                Intent intent = new Intent(this, UpdateStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.LogoutUser:
                //log out the user
                ParseUser.logOut();
                //take the user back to the login screen
                Intent takeUserToLoginScreen = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(takeUserToLoginScreen);
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
