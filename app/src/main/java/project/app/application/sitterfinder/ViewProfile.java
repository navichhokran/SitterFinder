package project.app.application.sitterfinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ViewProfile extends ActionBarActivity {


    Button editProfile,deleteProfile,changePwd,logout;
    TextView name,email,phone,workex,serPro,type,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        changePwd = (Button) findViewById(R.id.btnChangePwd);
        editProfile =(Button) findViewById(R.id.btnEditProfile);
        deleteProfile =(Button) findViewById(R.id.btnDeleteProfile);
        logout =(Button) findViewById(R.id.btnLogout);

        name = (TextView) findViewById(R.id.TxtUname);
        email = (TextView) findViewById(R.id.TxtEmail);
        phone = (TextView) findViewById(R.id.TxtPhone);
        workex = (TextView) findViewById(R.id.TxtWorkEx);
        serPro = (TextView) findViewById(R.id.TxtServiceProvided);
        type = (TextView) findViewById(R.id.TxtType);
        status = (TextView) findViewById(R.id.TxtStatus);


        name.setText(SitterBean.name);
        email.setText(SitterBean.emailId);
        phone.setText(SitterBean.phone);
        workex.setText(SitterBean.workExperience);
        serPro.setText(SitterBean.serviceProvided);
        type.setText(SitterBean.type);
        status.setText(SitterBean.status);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SitterBean.reset();
                onBackPressed();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProfile.this,EditProfile.class);
                startActivity(i);
            }
        });

        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProfile.this,ChangePassword.class);
                startActivity(i);
            }
        });
        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProfile.this,DeleteProfile.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        name.setText(SitterBean.name);
        email.setText(SitterBean.emailId);
        phone.setText(SitterBean.phone);
        workex.setText(SitterBean.workExperience);
        serPro.setText(SitterBean.serviceProvided);
        type.setText(SitterBean.type);
        status.setText(SitterBean.status);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
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
