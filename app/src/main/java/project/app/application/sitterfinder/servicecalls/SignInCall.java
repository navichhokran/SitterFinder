package project.app.application.sitterfinder.servicecalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.app.application.sitterfinder.SitterBean;
import project.app.application.sitterfinder.ViewProfile;

/**
 * Created by LENOVO PC on 04-11-2015.
 */
public class SignInCall extends AsyncTask<String, Void, Void> {


    ProgressDialog barProgressDialog;

    public SignInCall(Context context) {
        this.context = context;
    }

    Context context;
    String responseText = null;


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
            String response = "";
            json.put("call", "signin");
            json.put("email", params[0]);
            json.put("password", params[1]);

            //Log.d("calling Readstream", "called");
            response = WS.readstream(json);
            //Log.d("Returning response", response + "");
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
            Log.i("response:",responseText);
            /*
            if(responseText.trim().contentEquals("true")) {
                SitterBean.reset();
                SitterBean.showmsg(context, "Login Successfull", Toast.LENGTH_LONG);
                Intent i = new Intent(context, ViewProfile.class);
                context.startActivity(i);
            }else{
                SitterBean.showmsg(context, "Invalid Login Credentials", Toast.LENGTH_LONG);
            }
            */
            JSONArray jsonArray = new JSONArray(responseText.trim());
            Log.i("info", jsonArray.length() + "");
            if (jsonArray.length() > 0) {
                SitterBean.reset();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject val = jsonArray.getJSONObject(i);
                    SitterBean.emailId = val.getString("EmailId");
                    SitterBean.name = val.getString("Name");
                    SitterBean.location = val.getString("Location");
                    SitterBean.phone = val.getString("ContactDetails");
                    SitterBean.workExperience = val.getString("WorkExperience");
                    SitterBean.serviceProvided = val.getString("ServiceProvided");
                    SitterBean.status = val.getString("status");
                    SitterBean.type = val.getString("sitterType");
                }
                SitterBean.showmsg(context, "Login Successfull", Toast.LENGTH_LONG);
                Intent i = new Intent(context, ViewProfile.class);
                context.startActivity(i);

            } else {
                SitterBean.showmsg(context, "Login Failed",
                        Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            SitterBean.showmsg(context, "Login Failed",
                    Toast.LENGTH_LONG);

        }
    }
}