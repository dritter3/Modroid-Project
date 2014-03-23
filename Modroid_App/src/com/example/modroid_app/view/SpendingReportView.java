package com.example.modroid_app.view;
import java.util.ArrayList;
import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.SQLHelper.DatabaseHandler;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.User;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SpendingReportView extends Activity {
	private DatabaseHandler db = new DatabaseHandler(this);
	private List<Integer> list;
	private List<List<Double>> withdrawlsByBank = new ArrayList<List<Double>>();
	private List<Double> withdrawls = new ArrayList<Double>();
	private ListView listView;
	ArrayAdapter<Double> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report);
		final int userId = (this.getIntent()).getIntExtra("user", 0);
		Log.d("spending getIntExtra userid", String.valueOf(userId));
		list = db.getBankListByUser(userId);
		listView = (ListView)findViewById(R.id.TranslistView);
		
		for(Integer a: list) {
			withdrawlsByBank.add(db.getWidrawls(a));
					
		}
		
		for(List<Double> b: withdrawlsByBank){
			Log.d("withdrawis not empty","O");
			for(Double w: b){
				Log.d("here","O");
				withdrawls.add(w);
				Log.d("withdrawList", b.toString());
			}
		}
		Log.d("worked thisfar", "2");
		adapter = new ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, withdrawls);
		Log.d("worked thisfar", "3");
		listView.setAdapter(adapter);
		Log.d("worked thisfar", "4");
		listView.setDivider(new ColorDrawable(Color.YELLOW));
		Log.d("worked thisfar", "5");
		listView.setDividerHeight(2);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_account_manage, menu);
		return true;
	}
}
