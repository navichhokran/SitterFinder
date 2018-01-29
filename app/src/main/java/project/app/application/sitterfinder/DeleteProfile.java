package project.app.application.sitterfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import project.app.application.sitterfinder.servicecalls.DeleteProfileCall;


public class DeleteProfile extends ActionBarActivity {

    Button delete,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);

        delete = (Button) findViewById(R.id.btnDelete);
        cancel = (Button) findViewById(R.id.btnCancel);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] serverCallParams = new String[1];
                serverCallParams[0] = SitterBean.emailId;
                new DeleteProfileCall(DeleteProfile.this).execute(serverCallParams);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_profile, menu);
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
