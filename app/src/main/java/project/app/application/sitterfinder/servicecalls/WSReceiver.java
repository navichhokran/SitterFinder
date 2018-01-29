package project.app.application.sitterfinder.servicecalls;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class WSReceiver
{
	public String readstream(JSONObject json) throws JSONException
	{
		String result=null;
		try
		{			     
			//Log.d("Entering WSReceiver","entered");
	        HttpParams httpParams = new BasicHttpParams();
	        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
	        HttpConnectionParams.setSoTimeout(httpParams, 10000);
	        HttpClient client = new DefaultHttpClient(httpParams);

	        String url = "http://navichhokran.netai.net/index.php";
	       
	        HttpPost request = new HttpPost(url);
	        request.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF8")));
	        request.setHeader("json", json.toString());
			Log.d("Posting Request ", "posted");
			Log.d("Json : ", json.toString());
	        HttpResponse response = client.execute(request);
	        Log.d("Request posted","got response");
	        HttpEntity entity = response.getEntity();
	        
	        // If the response does not enclose an entity, there is no need
	        if (entity != null)
	        {
	            InputStream instream = entity.getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
	            StringBuilder sb = new StringBuilder();

	            String line = null;
	            try 
	            {
	                while ((line = reader.readLine()) != null)
	                {
	                    sb.append(line + "\n");
	                }
	            } 
	            catch (IOException e)
	            {
	                e.printStackTrace();
	            } 
	            finally 
	            {
	                try 
	                {
	                	instream.close();
	                } 
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }            
	            result = sb.toString();
				Log.d("Response ",result);
	        }
	    } 
		catch(UnknownHostException e)
		{
			result=e.getMessage();
		}
		catch (Exception e)
	    {
			result = e.getMessage();
			Log.d("Exception occured", e+"");
	    }
		return result;
	}
}


