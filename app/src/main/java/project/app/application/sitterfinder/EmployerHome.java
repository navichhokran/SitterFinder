package project.app.application.sitterfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import project.app.application.sitterfinder.servicecalls.GetAllSittersCall;
import project.app.application.sitterfinder.servicecalls.ViewSitterDetailCall;


public class EmployerHome extends ActionBarActivity {

    ListView listView;
    Button search;
    EditText location;
    Spinner status,type;
    List<String> allSitterEmail = new ArrayList<String>();
    List<String> allSitterDetails = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home);

        listView = (ListView) findViewById(R.id.UserDetailListIn);
        search = (Button) findViewById(R.id.btnSearch);

        location = (EditText) findViewById(R.id.txtLocation);
        status = (Spinner) findViewById(R.id.sitterStatus);
        type = (Spinner) findViewById(R.id.sitterType);

        TextView emptyText = (TextView)findViewById(android.R.id.empty);

        QueryDBAllSitters();
        getlistData();
        listView.setEmptyView(emptyText);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                String[] serverCallParams = new String[3];
                serverCallParams[0] = allSitterEmail.get(itemPosition);
                new ViewSitterDetailCall(EmployerHome.this).execute(serverCallParams);
            }

        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryDBAllSitters();
                getlistData();
            }
        });
    }


    public void getlistData(){
        try {
            getlistValues();
            String[] values = new String[allSitterDetails.size()];
            values = allSitterDetails.toArray(values);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployerHome.this,android.R.layout.simple_list_item_1, android.R.id.text1, values);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
            String values[] ={};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployerHome.this,android.R.layout.simple_list_item_1, android.R.id.text1, values);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
        }
    }

    private void QueryDBAllSitters() {
        String[] serverCallParams = new String[3];

        serverCallParams[0] = location.getText().toString();
        serverCallParams[1] = status.getSelectedItem().toString();
        serverCallParams[2] = type.getSelectedItem().toString();
        new GetAllSittersCall(EmployerHome.this).execute(serverCallParams);
    }

    private void getlistValues() {
        try {
            allSitterEmail = new ArrayList<String>();
            allSitterDetails = new ArrayList<String>();
            String json = SitterBean.allSitterJson;
            Log.i("json :", json + "");
            JSONArray jsonArray = new JSONArray(json.trim());
            Log.i("json[] length:", jsonArray.length() + "");
            if (jsonArray.length() > 0) {
                SitterBean.reset();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject val = jsonArray.getJSONObject(i);
                    allSitterEmail.add(val.getString("EmailId"));
                    String temp = "";
                    temp += "\nName : "+ val.getString("Name");
                    temp += "\nLocation : "+ val.getString("Location");
                    temp += "\nStatus : "+ val.getString("status");
                    temp += "\nType : "+ val.getString("sitterType");
                    allSitterDetails.add(temp);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("err :",e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
