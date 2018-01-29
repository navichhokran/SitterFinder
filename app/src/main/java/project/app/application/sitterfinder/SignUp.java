package project.app.application.sitterfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import project.app.application.sitterfinder.servicecalls.SignupCall;


public class SignUp extends ActionBarActivity {

    Button signup;
    EditText name,email,password,password2,phone,location,workEx,serProvided;
    Spinner type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = (Button)findViewById(R.id.btnSignup2);

        name=(EditText) findViewById(R.id.edtxName);
        password=(EditText) findViewById(R.id.edtxPassword);
        password2=(EditText) findViewById(R.id.edtxPassword2);
        email=(EditText) findViewById(R.id.edtxEmail);
        phone=(EditText) findViewById(R.id.edtxPhone);
        location=(EditText) findViewById(R.id.edtxLocation);
        workEx=(EditText) findViewById(R.id.edtxWorkEx);
        serProvided=(EditText) findViewById(R.id.edtxSerProvided);

        type =(Spinner) findViewById(R.id.sitterType);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate()) {
                    return;
                }
                String[] serverCallParams = new String[9];
                serverCallParams[0] = name.getText().toString();
                serverCallParams[1] = email.getText().toString();
                serverCallParams[2] = password.getText().toString();
                serverCallParams[3] = location.getText().toString();
                serverCallParams[4] = phone.getText().toString();
                serverCallParams[5] = type.getSelectedItem().toString();
                serverCallParams[6] = "Available";
                serverCallParams[7] = workEx.getText().toString();
                serverCallParams[8] = serProvided.getText().toString();
                new SignupCall(SignUp.this).execute(serverCallParams);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    public boolean validate() {
        if(!validateLength(name,"User Name"))
            return false;
        if(!validateLength(email,"Email"))
            return false;
        if(!validateLength(password, "New Password"))
            return false;
        if(!validateLength(password2,"Confirm Password"))
            return false;
        if(!validateLength(location,"Location"))
            return false;
        if(!validatePhoneLength(phone, "Phone Number"))
            return false;
        if(!validateLength(workEx,"Work Experience"))
            return false;
        if(!validateLength(serProvided,"Service Provider"))
            return false;

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            SitterBean.showmsg(this,"Email ID not valid",Toast.LENGTH_SHORT);
            return false;
        }
        if(!password.getText().toString().contentEquals(password2.getText().toString())){
            SitterBean.showmsg(this,"Password Missmatch",Toast.LENGTH_SHORT);
            return false;
        }
        if(password.getText().toString().length()<5){
            SitterBean.showmsg(this,"Password length should be greater than 5",Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    private boolean validatePhoneLength(EditText txt, String str) {
        if(txt.getText().toString().trim().length()!=10){
            SitterBean.showmsg(this,str+" should be 10 digits long",Toast.LENGTH_SHORT);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
