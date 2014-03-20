package com.example.modroid_app.view;

import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.SQLHelper.DatabaseHandler;
import com.example.modroid_app.model.User;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserAccountManageView extends Activity {
	private DatabaseHandler db = new DatabaseHandler(this);
	private List<User> list;
	private ListView listView;
	ArrayAdapter<User> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account_manage);
		list = db.getUserList();
		listView = (ListView)findViewById(R.id.listView);
		for(User a: list) {
			System.out.println(a);
		}
		adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, list);
		
		listView.setAdapter(adapter);
		
		listView.setDivider(new ColorDrawable(Color.YELLOW));
		listView.setDividerHeight(2);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_account_manage, menu);
		return true;
	}
	
	

}
