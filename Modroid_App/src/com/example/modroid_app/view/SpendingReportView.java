package com.example.modroid_app.view;
import java.util.ArrayList;
import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.database.DatabaseHandler;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.Transaction;
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
	ArrayAdapter<String> adapter; // check here too
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report);
		final int userId = (this.getIntent()).getIntExtra("user", 0);
		Log.d("spending getIntExtra userid", String.valueOf(userId));
		list = db.getBankListByUser(userId);
		listView = (ListView)findViewById(R.id.TranslistView);
		
		for(int j = 0; j < list.size(); j++){
			List<Transaction> transList = db.getWithdrawals(list.get(j));
			List<Double> amountList = new ArrayList<Double>();
			for(int i = 0; i < transList.size(); i++) {
				amountList.add(transList.get(i).getAmount());
			}
			withdrawlsByBank.add(amountList);
		}
		
		for(List<Double> b: withdrawlsByBank){
			for(Double w: b){
				withdrawls.add(w);
				Log.d("withdrawList", b.toString());
			}
		}
		List<Transaction> transList;
		List<String> withdrawlList = new ArrayList<String>();
		for(int j = 0; j < list.size(); j++){
			transList = db.getWithdrawals(list.get(j));
			for(int i = 0; i< transList.size(); i++) {
				String a = transList.get(i).getDate() + " // " + transList.get(i).getAmount();
				withdrawlList.add(a);
			}
		}
		
		
		
		List<Transaction> Translist = db.getTransactionsByDate(20140323, 20140324);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, withdrawlList);
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
