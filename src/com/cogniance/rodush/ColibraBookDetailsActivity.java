package com.cogniance.rodush;

import com.cogniance.rodush.colibra.data.ColibraDbAdapter;
import com.cogniance.rodush.colibra.data.ColibraDbHelper;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

@EActivity(R.layout.book_details)
public class ColibraBookDetailsActivity extends Activity implements OnRatingBarChangeListener {

	protected int book_id;

	private Cursor myCursor, rateCursor;
	private static final String[] bookDetailsMap = new String[] {
			ColibraDbHelper._ID, ColibraDbHelper.BOOK_NAME,
			ColibraDbHelper.BOOK_PUBLISH_YEAR,
			ColibraDbHelper.BOOK_TECHNOLOGY_ID
	};
	
	private static final String[] bookRateMap = new String[] {
		ColibraDbHelper.RATE_BOOK_ID, ColibraDbHelper.RATE_VAL
	};
	
	@ViewById(R.id.reserve_btn)
	Button reserveButton;
	
	@ViewById
	TextView book_year;
	
	@ViewById
	TextView book_name;
	
	@ViewById
	TextView technology;
	
	@ViewById
	RatingBar ratingBar;
	
	@ViewById
	ImageView book_img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle extras = this.getIntent().getExtras();
		book_id = extras.getInt("book_id");

		myCursor = managedQuery(
						ColibraDbAdapter.BOOKS_URI,
						bookDetailsMap,
						"_ID=" + book_id,
						null,
						null
				   );
		myCursor.moveToNext();
//		rateCursor  = managedQuery(
//				ColibraDbAdapter.BOOK_RATE_URI,
//				bookRateMap,
//				"book_id=" + book_id,
//				null,
//				null
//		   );
	}

	@Click(R.id.reserve_btn)
	void reserveButton() {
		// Reserve this book for the user
		ContentValues initialValues = new ContentValues();
		initialValues.put(ColibraDbHelper.USER_ID, 1);
		initialValues.put(ColibraDbHelper.BOOK_ID, book_id);
		ContentProviderClient client = getContentResolver()
				.acquireContentProviderClient(ColibraDbAdapter.USER_BOOK_URI);

		// TODO: check if such book is not reserved by this user
		try {
			client.insert(ColibraDbAdapter.USER_BOOK_URI, initialValues);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 Toast.makeText(getApplicationContext(),
		 "Book reserved successfully", //+ reserveMap.toString(),
		 Toast.LENGTH_SHORT).show();
	}
	
	@AfterViews
	void updateUI(){
		book_name.setText(myCursor.getString(1));
		book_year.setText(myCursor.getString(2));
		technology.setText(myCursor.getString(3));
		book_img.setImageResource(R.drawable.icon);
		ratingBar.setRating(4);
		ratingBar.setOnRatingBarChangeListener(this);
	}
	
	@Override
	public void onRatingChanged(RatingBar ratingBarStub, float rating, boolean fromUser) {
		ContentProviderClient client = getContentResolver()
				.acquireContentProviderClient(ColibraDbAdapter.BOOK_RATE_URI);
		ContentValues values = new ContentValues();
		values.put(ColibraDbHelper.RATE_BOOK_ID, book_id);
		values.put(ColibraDbHelper.RATE_VAL, rating + "");
		try {
			// Check if there is a rate for this book already
			
			// insert new rate
			if(false){
				
			}
			// update prev rate
			else{
				client.update(ColibraDbAdapter.BOOK_RATE_URI, values, "book_id=" + book_id, null);
			}
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
