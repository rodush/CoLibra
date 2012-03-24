package com.cogniance.rodush.library;

public class Book {
	
	public static final int STATE_FREE = 0;
	public static final int STATE_BOOKED = 1;
	public static final int STATE_TAKEN = 2;
	
	protected Author author;
	protected String name = null;
	protected int publish_year; // year of issue
	protected int category; // ID of category from local dictionary
	protected int currentState; // marker of current state of book.
	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the publish_year
	 */
	public int getDoi() {
		return publish_year;
	}
	/**
	 * @param doi the publish_year to set
	 */
	public void setDoi(int publish_year) {
		this.publish_year = publish_year;
	}
	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}
	/**
	 * @return the currentState
	 */
	public int getCurrentState() {
		return currentState;
	}
	/**
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}
	
}
