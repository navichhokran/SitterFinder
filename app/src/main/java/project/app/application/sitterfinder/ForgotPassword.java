package project.app.application.sitterfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.app.application.sitterfinder.servicecalls.ForgotPwdCall;


public class ForgotPassword extends ActionBarActivity {
EditText txtemail ;
    Button forgotpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotpwd = (Button)findViewById(R.id.btnEmailPwd);
        txtemail = (EditText) findViewById(R.id.txtForgotEmail);
        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    return;
                }

                String[] serverCallParams = new String[2];
                serverCallParams[0] =txtemail.getText().toString();
                new ForgotPwdCall(ForgotPassword.this).execute(serverCallParams);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
        return true;
    }



    public boolean validate() {

        if(!validateLength(txtemail,"Email"))
            return false;

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtemail.getText().toString()).matches()){
            SitterBean.showmsg(this, "Email ID not valid", Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }
    public boolean validateLength(EditText txt,String str) {
        if(txt.getText().toString().length()<=0){
            SitterBean.showmsg(this,str+" Can't be null",Toast.LENGTH_SHORT);
            return false;
        }
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
