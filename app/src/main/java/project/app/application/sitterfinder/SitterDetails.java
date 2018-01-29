package project.app.application.sitterfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SitterDetails extends ActionBarActivity {


    TextView name,email,phone,workex,serPro,type,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_details);



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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sitter_details, menu);
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
