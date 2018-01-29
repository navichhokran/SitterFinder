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

import project.app.application.sitterfinder.servicecalls.EditProfileCall;


public class EditProfile extends ActionBarActivity {

    EditText name,location,phone,workex,serPro;
    Spinner type,status;

    Button editPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editPro = (Button) findViewById(R.id.btnupdatePro);
        name = (EditText) findViewById(R.id.editName);
        phone = (EditText) findViewById(R.id.editPhone);
        location = (EditText) findViewById(R.id.editLocation);
        workex = (EditText) findViewById(R.id.editWorkEx);
        serPro = (EditText) findViewById(R.id.editSerPro);
        type = (Spinner) findViewById(R.id.sitterType);
        status = (Spinner) findViewById(R.id.sitterStatus);


        name.setText(SitterBean.name);
        location.setText(SitterBean.location);
        phone.setText(SitterBean.phone);
        workex.setText(SitterBean.workExperience);
        serPro.setText(SitterBean.serviceProvided);

        if(SitterBean.type.contentEquals("Baby Sitter"))
            type.setSelection(1,false);
        else if(SitterBean.type.contentEquals("Animal Sitter"))
            type.setSelection(2,false);

        if(SitterBean.status.contentEquals("Busy"))
            status.setSelection(1,false);
        /*
        type.setText(SitterBean.type);
        status.setText(SitterBean.status);
*/

        editPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate())
                    return;

                String[] serverCallParams = new String[8];
                serverCallParams[0] =name.getText().toString();
                serverCallParams[1] = location.getText().toString();
                serverCallParams[2] = phone.getText().toString();
                serverCallParams[3] = type.getSelectedItem().toString();
                serverCallParams[4] = status.getSelectedItem().toString();
                serverCallParams[5] = workex.getText().toString();
                serverCallParams[6] = serPro.getText().toString();
                serverCallParams[7] = SitterBean.emailId;
                new EditProfileCall(EditProfile.this).execute(serverCallParams);

            }
        });
    }


    public boolean validateLength(EditText txt,String str) {
        if(txt.getText().toString().length()<=0){
            SitterBean.showmsg(this,str+" Can't be null",Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }
    public boolean validate() {
        if(!validateLength(name,"User Name"))
            return false;
        if(!validateLength(location,"Location"))
            return false;
        if(!validateLength(workex,"Work Experience"))
            return false;
        if(!validateLength(serPro,"Service Provider"))
            return false;
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
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
