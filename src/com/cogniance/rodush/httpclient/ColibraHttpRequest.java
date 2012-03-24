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
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

import com.cogniance.rodush.library.Author;
import com.cogniance.rodush.library.Book;
import com.google.gson.Gson;

public class ColibraHttpRequest {

	private static final String SERVICE_URL = "http://10.0.2.2/index.html";
	private static final String TAG = "HTTPClient";

	// Create a new HttpClient and Post Header
	HttpClient httpclient = new DefaultHttpClient();
	HttpResponse response;
	private String responseString = null;

	// Create a local instance of cookie store
	CookieStore cookieStore = new BasicCookieStore();

	// Create local HTTP context
	HttpContext localContext = new BasicHttpContext();

	public String postData(String actionName, List<NameValuePair> nameValuePairs) {

		HttpPost httppost = new HttpPost(SERVICE_URL);
		// HttpGet httpget = new HttpGet(SERVICE_URL);

		try {
			// Bind custom cookie store to the local context
			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

			// Add your data
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			response = httpclient.execute(httppost, localContext);

			// process a Response
			responseString = processResponse(response);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
//			finally {
//			// When HttpClient instance is no longer needed,
//			// shut down the connection manager to ensure
//			// immediate deallocation of all system resources
//			httpclient.getConnectionManager().shutdown();
//		}

		return responseString;
	}

	protected String processResponse(HttpResponse response) throws IOException {

		StatusLine statusLine = response.getStatusLine();
		
		List<Cookie> cookies = cookieStore.getCookies();
        for (int i = 0; i < cookies.size(); i++) {
        	Log.d(TAG, cookies.get(i).toString());
        }

		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.getEntity().writeTo(out);
			out.close();
			responseString = out.toString();
		} else {
			// Closes the connection.
			response.getEntity().getContent().close();
			throw new IOException(statusLine.getReasonPhrase());
		}
		
		// FIXME: Remove this hardcoded string
//		responseString = "{firstName: Roman, lastName: Dushko}";
//		responseString = "{ name:'Demo book', publish_year:2011, category: 1, currentState: 1, "
//							+ "author: { firstName: Roman, lastName: Dushko } }";
//		
//		Gson gson = new Gson();
//		Book book = gson.fromJson(responseString, Book.class);
//		Log.d(TAG, book.getAuthor().getFullName());
//		Book books = gson.fromJson(responseString, Book.class);

		return responseString;
	}

}
