package com.itcuties.android.apps.itcsqliteexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.itcuties.android.apps.itcsqliteexample.dao.TodoDAO;
import com.itcuties.android.apps.itcsqliteexample.data.Todo;

/**
 * Main activity which displays a list of TODOs.
 * 
 * @author itcuties
 *
 */
public class MainActivity extends ListActivity {

	// DAO
	private TodoDAO dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Create DAO object
		dao = new TodoDAO(this);
		
		// Set the list adapter and get TODOs list via DAO
		setListAdapter(new ListAdapter(this, dao.getTodos()));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO item that was clicked
		Todo todo = (Todo)getListAdapter().getItem(position);
		
		// Delete TODO object from the database
		dao.deleteTodo(todo.getId());
		
		// Set the list adapter and get TODOs list via DAO
		setListAdapter(new ListAdapter(this, dao.getTodos()));
		
		// Display success information
		Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_LONG).show();
		
	}
	
	/* ************************************************************* *
	 * Menu service methods
	 * ************************************************************* */ 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Since we have only ONE option this code is not complicated :)
		
		// Create an intent
		Intent intent = new Intent(this, AddTodoActivity.class);
		// Start activity
		startActivity(intent);
		// Finish this activity
		this.finish();
		
		// Close the database
		dao.close();
		
		return super.onOptionsItemSelected(item);
	}

}
