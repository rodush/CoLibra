package com.cogniance.rodush;

import com.cogniance.rodush.colibra.data.ColibraDbAdapter;
import com.cogniance.rodush.colibra.data.ColibraDbHelper;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

public class ColibraBookDetailsActivity extends Activity {

	protected int book_id;

	private Cursor myCursor;
	private static final String[] bookDetailsMap = new String[] {
			ColibraDbHelper._ID, ColibraDbHelper.BOOK_NAME,
			ColibraDbHelper.BOOK_PUBLISH_YEAR,
			ColibraDbHelper.BOOK_TECHNOLOGY_ID };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_details);

		Bundle extras = this.getIntent().getExtras();

		book_id = extras.getInt("book_id");

		myCursor = managedQuery(ColibraDbAdapter.BOOKS_URI, bookDetailsMap,
				"_ID=" + book_id, null, null);
		myCursor.moveToNext();

		TextView bookName = (TextView) findViewById(R.id.book_name);
		TextView bookYear = (TextView) findViewById(R.id.book_year);
		TextView technology = (TextView) findViewById(R.id.technology);

		 bookName.setText(myCursor.getString(1));
		 bookYear.setText(myCursor.getString(2));
		 technology.setText(myCursor.getString(3));

		Button reserveButton = (Button) findViewById(R.id.reserve_btn);
		reserveButton.setOnClickListener(onReserveClickListener);
	}

	public OnClickListener onReserveClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View btn) {
			// Reserve this book for the user
			ContentValues initialValues = new ContentValues();
			initialValues.put(ColibraDbHelper.USER_ID, 1);
			initialValues.put(ColibraDbHelper.BOOK_ID, book_id);
			ContentProviderClient client = getContentResolver()
					.acquireContentProviderClient(
							ColibraDbAdapter.USER_BOOK_URI);

			// TODO: check if such book is not reserved by this user
			try {
				client.insert(ColibraDbAdapter.USER_BOOK_URI, initialValues);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Toast.makeText(getApplicationContext(),
			// "Book reserved: " + reserveMap.toString(),
			// Toast.LENGTH_SHORT).show();
		}
	};
}
