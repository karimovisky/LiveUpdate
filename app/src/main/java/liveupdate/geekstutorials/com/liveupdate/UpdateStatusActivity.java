package liveupdate.geekstutorials.com.liveupdate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UpdateStatusActivity extends AppCompatActivity {

    protected EditText mStatusUpdate;
    protected Button mStatusUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        //initialize
        mStatusUpdate = (EditText) findViewById(R.id.updateStatusTextBox);
        mStatusUpdateButton = (Button) findViewById(R.id.statusUpdateButton);
        //listen to status update button
        mStatusUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the current user
                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUsername = currentUser.getUsername();

                //get the status from edit text and convert it to string
                String newStatus = mStatusUpdate.getText().toString();

                if (newStatus.isEmpty()) {
                    //problem in storing new status advice the user
                    Toast.makeText(UpdateStatusActivity.this, "Status should not be empty" ,Toast.LENGTH_LONG).show();
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                    builder.setMessage("Status should not be empty");
                    builder.setTitle("Oops!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //close the dialog
                            dialog.dismiss();
                        }
                    });*/
                }
                else {
                    //save the status in parse
                    ParseObject statusObject = new ParseObject("Status");
                    statusObject.put("newStatus", newStatus);
                    statusObject.put("username", currentUsername);
                    statusObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //successfully stored the new status in parse
                                Toast.makeText(UpdateStatusActivity.this, "Success!", Toast.LENGTH_LONG).show();

                                //take user to the home page
                                Intent takeUserToHomePage = new Intent(UpdateStatusActivity.this, MainActivity.class);
                                UpdateStatusActivity.this.startActivity(takeUserToHomePage);
                            } else {
                                //problem in storing new status advice the user
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle("Sorry!");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //close the dialog
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_status, menu);
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
