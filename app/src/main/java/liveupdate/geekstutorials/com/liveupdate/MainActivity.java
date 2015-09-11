package liveupdate.geekstutorials.com.liveupdate;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class MainActivity extends ListActivity {

    protected List<ParseObject> mStatus;
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
            ParseQuery<ParseObject>  query = new ParseQuery<ParseObject>("Status");
            query.orderByDescending("createdAt");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> status, ParseException e) {
                    if (e == null){
                        //success
                        mStatus = status;


                        StatusAdapter adapter = new StatusAdapter(getListView().getContext(),mStatus);
                        setListAdapter(adapter);
                    }
                    else {
                        //there was a problem. Alert User
                    }
                }
            });
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ParseObject statusObject = mStatus.get(position);
        String ObjectId = statusObject.getObjectId();

        Toast.makeText(getApplicationContext(),ObjectId,Toast.LENGTH_LONG).show();
    }
}
