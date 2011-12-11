package com.cogniance.rodush.colibra.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 */

/**
 * @author rodush
 *
 */
public class ColibraDbHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "colibra";
	private static final int DB_VERSION = 1;
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL_ADDRESS = "email_address";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	
	private static final String CREATE_SQL = "CREATE TABLE users (_id integer primary key autoincrement, " +
	  USERNAME + " text not null, " + PASSWORD + " text not null, " + EMAIL_ADDRESS + " text not null," +
			FIRST_NAME +" text not null, " + 
			LAST_NAME + " text not null)";

	public ColibraDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SQL);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(this.getClass().getName(),
				String.format("Upgrading database from %d to %d", oldVersion, newVersion));
	}

}
