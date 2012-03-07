package com.cogniance.rodush.colibra.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
//import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ColibraDbAdapter extends ContentProvider {

	/**
	 * Declare URI constants
	 */
	public static final Uri CONTENT_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter");
	public static final Uri USERS_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/user");
	public static final Uri BOOKS_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/book");
	public static final Uri AUTHOR_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/author");
	public static final Uri BOOK_AUTHOR_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/book/author");
	public static final Uri BOOK_RATE_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/book/rate");
	public static final Uri USER_BOOK_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/user/book");
	public static final Uri TECHNOLOGY_URI = Uri
			.parse("content://com.cogniance.rodush.colibradbadapter/technology");

	// DB fields
	public static final String ROW_ID = "_id";
	public static final String USERNAME = "username";

	/**
	 * DB helper
	 */
	protected ColibraDbHelper dbHelper;

	/**
	 * DB instance
	 */
	protected SQLiteDatabase db;

	/**
	 * What does this method do???
	 * 
	 * @return ColibraDbAdapter
	 * @throws SQLException
	 */
	// public ColibraDbAdapter open() throws SQLException {
	// dbHelper = new ColibraDbHelper(context);
	// db = dbHelper.getWritableDatabase();
	// return this;
	// }
	//
	// public void close() {
	// dbHelper.close();
	// }

	protected static int CASE_USERS = 1;
	protected static int CASE_USER_ID = 2;
	protected static int CASE_BOOKS = 3;
	protected static int CASE_BOOK_ID = 4;
	protected static int CASE_AUTHORS = 5;
	protected static int CASE_AUTHOR_ID = 6;
	protected static int CASE_TECHNOLOGIES = 7;
	protected static final int CASE_TECHNOLOGY_ID = 8;
	protected static final int CASE_USER_BOOK = 9;
	protected static int CASE_BOOK_AUTHOR = 10;

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	static {
		sURIMatcher.addURI("com.cogniance.rodush.colibradbadapter",
				"user/book", CASE_USER_BOOK);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri url, ContentValues inValues) {
		ContentValues values = new ContentValues(inValues);

		String tableName;
		Uri uri2;

		switch (sURIMatcher.match(url)) {
		case CASE_USER_BOOK:
			tableName = ColibraDbHelper.TABLE_USER_BOOK;
			uri2 = USER_BOOK_URI;
			break;

		default:
			tableName = ColibraDbHelper.TABLE_BOOKS;
			uri2 = BOOKS_URI;
			break;
		}

		// Insert a row in the DB table
		long rowId = db.insert(tableName, null, values);

		// Get URI of created row
		Uri uri = ContentUris.withAppendedId(uri2, rowId);

		// Notify content resolver about changes
		getContext().getContentResolver().notifyChange(uri, null);

		return uri;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new ColibraDbHelper(getContext());
		db = dbHelper.getWritableDatabase();

		return (db == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		Cursor c = db.query(ColibraDbHelper.TABLE_BOOKS, projection, selection,
				selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Get name of SQLite database file
	 * 
	 * @return String
	 */
	public String getDbName() {
		return ColibraDbHelper.DB_NAME;
	}
}
