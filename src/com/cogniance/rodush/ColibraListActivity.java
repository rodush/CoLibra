package com.cogniance.rodush;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ColibraListActivity extends ListActivity {
	
	protected ListView books_list;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // check if user is authenticated
//        if (!userIsAuthenticated) {
        	// @TODO: Forward to signing Activity
//        }
        
		// Prepare data for the list
        // 1. Get last added books / most read books
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list, 
        		R.id.book_name, getResources().getStringArray(R.array.books)));
	}

	protected void onListItemClicked(ListView lv, View v, int position, long id) {
		super.onListItemClick(lv, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();
		Toast.makeText(this, "The clicked item is " + keyword, Toast.LENGTH_LONG).show();
	}

}
