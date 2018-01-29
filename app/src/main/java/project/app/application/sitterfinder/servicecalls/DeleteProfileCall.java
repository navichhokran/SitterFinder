package project.app.application.sitterfinder.servicecalls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import project.app.application.sitterfinder.MainActivity;
import project.app.application.sitterfinder.SitterBean;


public class DeleteProfileCall  extends AsyncTask<String, Void, Void> {


    ProgressDialog barProgressDialog;

    public DeleteProfileCall(Context context) {
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
            json.put("call", "deleteProfile");
            json.put("email", params[0]);

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
                SitterBean.showmsg(context, "Acount Deleted Successfull", Toast.LENGTH_LONG);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("finish", true); // if you are checking for this in your other Activities
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //context.finish();
            }else{
                SitterBean.showmsg(context, "Error: Acount Deletion Failed", Toast.LENGTH_LONG);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
