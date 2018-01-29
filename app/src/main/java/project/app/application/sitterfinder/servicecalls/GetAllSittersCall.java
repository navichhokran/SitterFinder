package project.app.application.sitterfinder.servicecalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import project.app.application.sitterfinder.EmployerHome;
import project.app.application.sitterfinder.SitterBean;

/**
 * Created by LENOVO PC on 06-11-2015.
 */
public class GetAllSittersCall   extends AsyncTask<String, Void, Void> {


    ProgressDialog barProgressDialog;

    public GetAllSittersCall(Context context) {
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
            json.put("call", "allSitters");
            json.put("location", params[0]);
            json.put("status", params[1]);
            json.put("type", params[2]);
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
            SitterBean.allSitterJson = responseText;
            EmployerHome x = (EmployerHome)this.context;
            x.getlistData();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
