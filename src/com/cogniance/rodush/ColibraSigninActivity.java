package com.cogniance.rodush;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cogniance.rodush.httpclient.ColibraHttpRequest;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.signin)
public class ColibraSigninActivity extends Activity {
	
	protected ColibraHttpRequest request = new ColibraHttpRequest();
	protected String responseString;
	
	@ViewById(R.id.radio0)
	RadioButton rb_new_user;
	
	@ViewById(R.id.radio1)
	RadioButton rb_sign_in;
	
	@ViewById(R.id.button1)
	Button submit_btn;
	
	@ViewById(R.id.signup_extra_details)
	LinearLayout extra_box;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
    };
    
    @Click
    void radio0() {
    	// show extra details box
    	extra_box.setVisibility(View.VISIBLE);
    }
    
    @Click
    void radio1() {
    	extra_box.setVisibility(View.INVISIBLE);
    }
    
    @Click
    void button1() {
    	// Get all data posted by user
		// we do not need to check another button because it's a RadioButon control
		final String mode = rb_new_user.isSelected() ? "new" : "old";
		
		final TextView username = (TextView)findViewById(R.id.editText2);
		final TextView password = (TextView)findViewById(R.id.editText3);
		final TextView repeat_pass = (TextView)findViewById(R.id.editText4);
		final TextView full_name = (TextView)findViewById(R.id.editText1);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
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
        
        /*
        final SharedPreferences prefs = context.getSharedPreferences();
        Editor editor = prefs.edit();
        editor.putString("field_name", data);
        editor.commit();
        data = prefs.getString("field_name");
     	*/

        
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), ColibraListActivity.class);
        startActivity(intent);
    }
    
    
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