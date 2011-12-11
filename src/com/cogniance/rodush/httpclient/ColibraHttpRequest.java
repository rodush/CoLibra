package com.cogniance.rodush.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
//import java.util.ArrayList;
//import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.google.gson.Gson;


public class ColibraHttpRequest {
	
	private static final String SERVICE_URL = "http://10.0.2.2/";
	private static final String TAG = "HTTPClient";
	
    // Create a new HttpClient and Post Header
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
	private String responseString = null;
    
	public String postData(String actionName, NameValuePair... nameValuePairs) {

	    HttpPost httppost = new HttpPost(SERVICE_URL);
//		HttpGet httpget = new HttpGet(SERVICE_URL);
	    
	    try {
	        // Add your data
	        httppost.setEntity(new UrlEncodedFormEntity(Arrays.asList(nameValuePairs)));

	        // Execute HTTP Post Request
	        response = httpclient.execute(httppost);
	        
	        // process a Response
	        responseString  = processResponse(response);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    	Log.e(TAG, e.toString());
	    	
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    	Log.e(TAG, e.toString());
	    }
	    
	    return responseString;
	}
	
	protected String processResponse(HttpResponse response) throws IOException {
		
 		StatusLine statusLine = response.getStatusLine();
        
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            responseString = out.toString();
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
		
		Gson gson = new Gson();
		Object oResponse = gson.fromJson(responseString, null);
		
		return responseString;
	}

}
