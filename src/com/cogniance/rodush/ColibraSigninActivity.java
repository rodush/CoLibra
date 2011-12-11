package com.cogniance.rodush;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cogniance.rodush.httpclient.ColibraHttpRequest;

public class ColibraSigninActivity extends Activity {
	
	protected ColibraHttpRequest request = new ColibraHttpRequest();
	protected String responseString;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        
        final RadioButton rb_new_user = (RadioButton) findViewById(R.id.radio0);
        final RadioButton rb_sign_in = (RadioButton) findViewById(R.id.radio1);
        final Button submit_btn = (Button) findViewById(R.id.button1);
        
        // when user clicks on new - show extra fields to him
        rb_new_user.setOnClickListener(new_user_click);
        
        rb_sign_in.setOnClickListener(sign_in_click);
        
        submit_btn.setOnClickListener(submit_click);
    };
    
    public OnClickListener new_user_click = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// show extra details box
			final LinearLayout extra_box = (LinearLayout) findViewById(R.id.signup_extra_details);
			extra_box.setVisibility(View.VISIBLE);
		}
	};
    
    public OnClickListener sign_in_click = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// show extra details box
			final LinearLayout extra_box = (LinearLayout) findViewById(R.id.signup_extra_details);
			extra_box.setVisibility(View.INVISIBLE);
		}
	};	
	
	public OnClickListener submit_click = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			final RadioButton rb_new_user = (RadioButton) findViewById(R.id.radio0);
			// Get all data posted by user
			// we do not need to check another button because it's a RadioButon control
			final String mode = rb_new_user.isSelected() ? "new" : "old";
			
			final TextView username = (TextView)findViewById(R.id.editText2);
			final TextView password = (TextView)findViewById(R.id.editText3);
			final TextView repeat_pass = (TextView)findViewById(R.id.editText4);
			final TextView full_name = (TextView)findViewById(R.id.editText1);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(mode == "new" ? 4 : 2);
	        nameValuePairs.add(new BasicNameValuePair("username", username.toString()));
	        nameValuePairs.add(new BasicNameValuePair("password", password.toString()));
	        if (mode == "new") {
	        	nameValuePairs.add(new BasicNameValuePair("repeat_pass", repeat_pass.toString()));
		        nameValuePairs.add(new BasicNameValuePair("full_name", full_name.toString()));
	        }
			
	        responseString = request.postData("signin", nameValuePairs);
			
	        
	        Toast toast = Toast.makeText(
	        		getApplicationContext(),
	        		responseString,
	        		Toast.LENGTH_SHORT
	        );
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.show();
	        
			
//			try
//			{
//				InputStream iStream = response.getEntity().getContent(); 
//			}			
//			catch(IOException ioe)
//			{
				// TODO: Log exception message 
//			}
		}
	};
	
	
	
	
	
	
	
	
	/*
	public OnClickListener submit_click = new View.OnClickListener() {
		@Override
		public void onClick(View btn) {
			final RadioButton rb_new_user = (RadioButton) findViewById(R.id.radio0);
			// Get all data posted by user
			// we do not need to check another button because it's a RadioButon control
			final String mode = rb_new_user.isSelected() ? "new" : "old";
			
			final TextView username = (TextView)findViewById(R.id.editText2);
			final TextView password = (TextView)findViewById(R.id.editText3);
			final TextView repeat_pass = (TextView)findViewById(R.id.editText4);
			final TextView full_name = (TextView)findViewById(R.id.editText1);
			
			byte[] bytesOfPassword = new byte[64];
			try {
				bytesOfPassword = password.toString().getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Get encoder:
			 try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				
				if (mode == "new")
				{
					// register new user and auto sign-in
					if (password.toString() != repeat_pass.toString())
					{
						throw new Exception("Passwords are missmatched");
					}
				}
				else
				{
					// sign-in old user: find by username and password
					String query = new String("SELECT count(*) FROM user WHERE username = '" + username + "'" +
							"AND password = '" +  md.digest(bytesOfPassword) + "'");
					
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	};
	*/
	
}