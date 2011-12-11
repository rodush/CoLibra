package com.cogniance.rodush.library;

import java.util.Date;

public class Book {
	
	public static final int STATE_FREE = 0;
	public static final int STATE_BOOKED = 1;
	public static final int STATE_TAKEN = 2;
	
	protected Author author;
	protected String name = null;
	protected Date doi; // date of issue
	protected int category; // ID of category from local dictionary
	protected int currentState; // marker of current state of book.
	
}
