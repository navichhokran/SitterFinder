package project.app.application.sitterfinder.servicecalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import project.app.application.sitterfinder.SignUp;
import project.app.application.sitterfinder.SitterBean;

/**
 * Created by LENOVO PC on 03-11-2015.
 */
public class SignupCall extends AsyncTask<String, Void, Void> {


    ProgressDialog barProgressDialog;

    public SignupCall(Context context) {
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
            json.put("call", "signup");
            json.put("name", params[0]);
            json.put("email", params[1]);
            json.put("password", params[2]);
            json.put("location", params[3]);
            json.put("phone", params[4]);
            json.put("type", params[5]);
            json.put("status", params[6]);
            json.put("workExperience", params[7]);
            json.put("serviceProvided", params[8]);

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
            SitterBean.showmsg(context, responseText.trim().split("[\n\r]")[0].trim(), Toast.LENGTH_LONG);
            Log.i("response :", responseText);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SignUp x = (SignUp)this.context;
        x.onBackPressed();
    }

}
