package com.cogniance.rodush.colibra.data;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ColibraDbAdapter {
	// DB fields
	public static final String ROW_ID = "_id";
	public static final String USERNAME = "username";
	
	protected Context context;
	
	protected ColibraDbHelper dbHelper;
	protected SQLiteDatabase database;
	
	public void ColibraAdapter(Context context) {
		this.context = context;
	}
	
	public ColibraDbAdapter open() throws SQLException {
		dbHelper = new ColibraDbHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
}
