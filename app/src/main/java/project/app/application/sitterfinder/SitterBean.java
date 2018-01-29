package project.app.application.sitterfinder;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LENOVO PC on 03-11-2015.
 */
public class SitterBean {

    public static String emailId;
    public static String name;
    public static String location;
    public static String phone;
    public static String workExperience;
    public static String serviceProvided;
    public static String type;
    public static String status;

    public static String allSitterJson;

    public static void reset(){
        emailId="";
        name="";
        location="";
        phone="";
        workExperience="";
        serviceProvided="";
        type="";
        status="";
        allSitterJson="";
    }

    public static void showmsg(Context context,String msg,int duration){
        Toast toast=Toast.makeText(context,msg,duration);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setBackgroundColor(Color.parseColor("#646464"));
        v.setTextColor(Color.WHITE);
        toast.show();
    }
}
