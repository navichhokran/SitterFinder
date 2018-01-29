package project.app.application.sitterfinder.servicecalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import project.app.application.sitterfinder.EditProfile;
import project.app.application.sitterfinder.SitterBean;


public class EditProfileCall   extends AsyncTask<String, Void, Void> {


    ProgressDialog barProgressDialog;

    public EditProfileCall(Context context) {
        this.context = context;
    }

    Context context;
    String responseText = null;
    String[] updateValues = new String[10];

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        barProgressDialog = new ProgressDialog(context);
        barProgressDialog.setTitle("Application");
        barProgressDialog.setMessage("Loading ...");
        barProgressDialog.setCancelable(false);
        // barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
        barProgressDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        WSReceiver WS = new WSReceiver();
        JSONObject json = new JSONObject();
        try {
            updateValues = params;
            String response = "";
            json.put("call", "editProfile");
            json.put("name", params[0]);
            json.put("location", params[1]);
            json.put("phone", params[2]);
            json.put("type", params[3]);
            json.put("status", params[4]);
            json.put("workExperience", params[5]);
            json.put("serviceProvided", params[6]);
            json.put("email", params[7]);

            //Log.d("calling Readstream", "called");
            response = WS.readstream(json);
            Log.d("Returning response", response + "");
            responseText = response;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseText = e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        barProgressDialog.dismiss();
        try {

            if(responseText.trim().split("[\n\r]")[0].trim().contentEquals("true")) {
                SitterBean.showmsg(context, "Profile Updated Successfull", Toast.LENGTH_LONG);
                SitterBean.name = updateValues[0];
                SitterBean.location =updateValues[1];
                SitterBean.phone =updateValues[2];
                SitterBean.type =updateValues[3];
                SitterBean.status =updateValues[4];
                SitterBean.workExperience =updateValues[5];
                SitterBean.serviceProvided =updateValues[6];
                SitterBean.emailId =updateValues[7];


            }else{
                SitterBean.showmsg(context, "Error: Profile Updating Failed", Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        EditProfile x = (EditProfile)this.context;
        x.onBackPressed();
    }
}
