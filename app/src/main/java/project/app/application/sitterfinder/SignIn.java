package project.app.application.sitterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.app.application.sitterfinder.servicecalls.SignInCall;


public class SignIn extends ActionBarActivity {

    EditText email,pwd;
    Button signIn,signUp,forgotPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (EditText)findViewById(R.id.txtsigninEmail);
        pwd = (EditText)findViewById(R.id.txtsigninPassword);

        signIn = (Button) findViewById(R.id.btnSignIn);
        signUp = (Button) findViewById(R.id.btnSignup);
        forgotPwd = (Button) findViewById(R.id.btnForgotPwd);
        email.setText("");
        pwd.setText("");
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    return;
                }

                String[] serverCallParams = new String[2];
                serverCallParams[0] =email.getText().toString();
                serverCallParams[1] = pwd.getText().toString();
                new SignInCall(SignIn.this).execute(serverCallParams);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this,SignUp.class);
                startActivity(i);
            }
        });

        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this,ForgotPassword.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }


    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();

        email.setText("");
        pwd.setText("");
    }


    public boolean validate() {

        if(!validateLength(email, "Email"))
            return false;
        if(!validateLength(pwd,"New Password"))
            return false;

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            SitterBean.showmsg(this, "Email ID not valid", Toast.LENGTH_SHORT);
            return false;
        }
        if(pwd.getText().toString().length()<5){
            SitterBean.showmsg(this,"Password length should be greater than 5",Toast.LENGTH_SHORT);
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


        return super.onOptionsItemSelected(item);
    }
}
