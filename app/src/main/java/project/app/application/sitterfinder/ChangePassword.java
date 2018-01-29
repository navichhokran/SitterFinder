package project.app.application.sitterfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.app.application.sitterfinder.servicecalls.ChangePasswordCall;


public class ChangePassword extends ActionBarActivity {

EditText oldpwd,newpwd,conpwd;
    Button save,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        save = (Button)findViewById(R.id.btnSave);
        cancel = (Button)findViewById(R.id.btnCancel);

        oldpwd = (EditText) findViewById(R.id.txtOldPassword);
        newpwd = (EditText) findViewById(R.id.txtNewPassword);
        conpwd = (EditText) findViewById(R.id.txtConNewPassword);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(oldpwd.getText().toString().length()<5){
                    msg("Old Password is less than 5 characters");
                    return;
                }
                if(newpwd.getText().toString().length()<5){
                    msg("New Password is less than 5 characters");
                    return;
                }
                if(!newpwd.getText().toString().contentEquals(conpwd.getText().toString())){
                    msg("New password didn't match with Confirm password");
                    return;
                }

                String[] serverCallParams = new String[3];
                serverCallParams[0] = SitterBean.emailId;
                serverCallParams[1] = oldpwd.getText().toString();
                serverCallParams[2] = newpwd.getText().toString();
                new ChangePasswordCall(ChangePassword.this).execute(serverCallParams);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void msg(String msg){
        SitterBean.showmsg(getApplication(),msg,Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
