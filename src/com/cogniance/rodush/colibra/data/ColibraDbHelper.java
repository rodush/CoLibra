package com.cogniance.rodush.colibra.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
//import android.util.Log;

/**
 * This class is responsible to create DB at first launch, upgrade db if application upgraded
 * manage names of db filename, tables, table fields.
 * 
 * @author rodush
 */
public class ColibraDbHelper extends SQLiteOpenHelper implements BaseColumns {

	private static final int DB_VERSION = 1;

	/**
	 * Name of database
	 */
	public static final String DB_NAME = "colibra.db";

	/**
	 * Tables
	 */
	public static final String TABLE_USERS = "user";
	public static final String TABLE_AUTHORS = "author";
	public static final String TABLE_BOOKS = "book";
	public static final String TABLE_TECHNOLOGY = "technology";
	public static final String TABLE_BOOK_AUTHOR = "book_author";
	public static final String TABLE_USER_BOOK = "user_book";
	public static final String TABLE_BOOK_RATE = "book_rate";

	/**
	 * Tables fields
	 */
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL_ADDRESS = "email_address";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String AUTHOR_ID = "author_id";
	public static final String AUTHOR_NAME = "name";
	public static final String BOOK_ID = "book_id";
	public static final String BOOK_NAME = "name";
	public static final String BOOK_PUBLISH_YEAR = "publish_year";
	public static final String BOOK_TECHNOLOGY_ID = "technology_id";
	public static final String TECHNOLOGY_NAME = "name";
	public static final String RATE_BOOK_ID = "book_id";
	public static final String RATE_VAL = "rate";
	public static final String USER_ID = "user_id";

	/**
	 * Queries to create tables
	 */
	private static final String CREATE_USERS_TABLE_SQL = "CREATE TABLE "
			+ TABLE_USERS + "(_id integer primary key autoincrement, "
			+ USERNAME + " text not null, "
			+ PASSWORD + " text not null, "
			+ EMAIL_ADDRESS + " text,"
			+ FIRST_NAME + " text, " + LAST_NAME + " text)";

	private static final String CREATE_BOOKS_TABLE_SQL = "CREATE TABLE "
			+ TABLE_BOOKS + " (_id integer primary key autoincrement, "
			+ BOOK_NAME + " text not null, " + BOOK_TECHNOLOGY_ID
			+ " int not null, " + BOOK_PUBLISH_YEAR + " int(4) not null)";

	private static final String CREATE_AUTHORS_TABLE_SQL = "CREATE TABLE "
			+ TABLE_AUTHORS + " (_id integer primary key autoincrement, "
			+ AUTHOR_NAME + " text not null)";

	private static final String CREATE_TECHNOLOGY_TABLE_SQL = "CREATE TABLE "
			+ TABLE_TECHNOLOGY + " (_id integer primary key autoincrement, "
			+ TECHNOLOGY_NAME + " text not null)";

	private static final String CREATE_BOOKS_AUTHORS_TABLE_SQL = "CREATE TABLE "
			+ TABLE_BOOK_AUTHOR
			+ " ("
			+ BOOK_ID
			+ " integer, "
			+ AUTHOR_ID
			+ " integer not null)";

	private static final String CREATE_USERS_BOOKS_TABLE_SQL = "CREATE TABLE "
			+ TABLE_USER_BOOK + " (" + USER_ID + " integer not null, "
			+ BOOK_ID + " integer not null)";

	private static final String CREATE_BOOKS_RATE_TABLE_SQL = "CREATE TABLE "
			+ TABLE_BOOK_RATE + " (" + RATE_BOOK_ID + " integer not null, "
			+ RATE_VAL + " float not null)";

	
	/**
	 * The Constructor
	 * @param Context context
	 */
	public ColibraDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//-------------------------------------------
		// Create tables
		//-------------------------------------------
		db.execSQL(CREATE_USERS_TABLE_SQL);
		db.execSQL(CREATE_BOOKS_TABLE_SQL);
		db.execSQL(CREATE_AUTHORS_TABLE_SQL);
		db.execSQL(CREATE_BOOKS_AUTHORS_TABLE_SQL);
		db.execSQL(CREATE_USERS_BOOKS_TABLE_SQL);
		db.execSQL(CREATE_BOOKS_RATE_TABLE_SQL);
		db.execSQL(CREATE_TECHNOLOGY_TABLE_SQL);
		
		
		
		//-------------------------------------------
		// Create some users
		//-------------------------------------------
		
		ContentValues values = new ContentValues();
		
		values.put(USERNAME, "rodush");
		values.put(PASSWORD, "rodush");
		values.put(EMAIL_ADDRESS, "rdushko@cogniance.com");
		values.put(FIRST_NAME, "Roman");
		values.put(LAST_NAME, "Dushko");
		db.insert(TABLE_USERS, null, values);
		
		values.put(USERNAME, "demo");
		values.put(PASSWORD, "demo");
		values.put(EMAIL_ADDRESS, "rodush@ua.fm");
		values.put(FIRST_NAME, "Demo");
		values.put(LAST_NAME, "User");
		db.insert(TABLE_USERS, null, values);
		
		
		//-------------------------------------------
		// Create some authors
		//-------------------------------------------

		ContentValues authorValues = new ContentValues();
		
		authorValues.put(AUTHOR_NAME, "Алексей Голощапов");
		db.insert(TABLE_AUTHORS, null, authorValues);
		
		authorValues.put(AUTHOR_NAME, "Andy Goodmans");
		db.insert(TABLE_AUTHORS, null, authorValues);
		
		authorValues.put(AUTHOR_NAME, "Лев Толстой");
		db.insert(TABLE_AUTHORS, null, authorValues);
		

		//-------------------------------------------
		// Create some technologies
		//-------------------------------------------
		
		ContentValues technologyValues = new ContentValues();
		
		technologyValues.put(TECHNOLOGY_NAME, "PHP");
		long tech1id = db.insert(TABLE_TECHNOLOGY, null, technologyValues);
		
		technologyValues.put(TECHNOLOGY_NAME, "Android");
		long tech2id = db.insert(TABLE_TECHNOLOGY, null, technologyValues);

		
		//-------------------------------------------
		// Create some books
		//-------------------------------------------
		
		ContentValues bookValues = new ContentValues();
		
		bookValues.put(BOOK_NAME, "Google Android: development for mobile platforms");
		bookValues.put(BOOK_PUBLISH_YEAR, 2010);
		bookValues.put(BOOK_TECHNOLOGY_ID, tech2id);
		db.insert(TABLE_BOOKS, null, bookValues);
		
		bookValues.put(TECHNOLOGY_NAME, "PHP: Power Programming");
		bookValues.put(BOOK_PUBLISH_YEAR, 2008);
		bookValues.put(BOOK_TECHNOLOGY_ID, tech1id);
		db.insert(TABLE_BOOKS, null, bookValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_RATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHORS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNOLOGY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_BOOK);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_AUTHOR);
		onCreate(db);
	}

}
