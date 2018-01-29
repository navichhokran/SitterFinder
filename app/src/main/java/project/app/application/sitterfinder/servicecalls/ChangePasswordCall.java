package project.app.application.sitterfinder.servicecalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import project.app.application.sitterfinder.ChangePassword;
import project.app.application.sitterfinder.SitterBean;


public class ChangePasswordCall  extends AsyncTask<String, Void, Void> {


    ProgressDialog barProgressDialog;

    public ChangePasswordCall(Context context) {
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
            json.put("call", "changePassword");
            json.put("email", params[0]);
            json.put("oldPassword", params[1]);
            json.put("newPassword", params[2]);

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

            if(responseText.trim().split("[\n\r]")[0].trim().contentEquals("true")) {
                SitterBean.showmsg(context, "Password Changed Successfull", Toast.LENGTH_LONG);
            }else{
                SitterBean.showmsg(context, "Error: Password Change Failed", Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ChangePassword x = (ChangePassword)this.context;
        x.onBackPressed();
    }

}
