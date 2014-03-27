package com.example.modroid_app.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.SQLHelper.DatabaseHandler;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.Transaction;
import com.example.modroid_app.model.User;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SpendingReportView extends Activity {
	private DatabaseHandler db = new DatabaseHandler(this);
	private List<Integer> list; // bank account list
	private List<List<Double>> withdrawlsByBank = new ArrayList<List<Double>>();
	private List<Double> withdrawls = new ArrayList<Double>();
	private ListView listView;
	private int startDate;
	private int endDate;
	
	private List<Transaction> listByDate;
	
	ArrayAdapter<String> adapter; // check here too
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report);
		final int userId = (this.getIntent()).getIntExtra("user", 0);
		startDate = (this.getIntent()).getIntExtra("start", 0);
		endDate = (this.getIntent()).getIntExtra("end", 0);
		
		Log.d("spending getIntExtra userid", String.valueOf(userId));
		list = db.getBankListByUser(userId); //get bank account list for the user
		
		List<String> withdrawlList = new ArrayList<String>();
		List<String> print = new ArrayList<String>();
		
		//
		listByDate = db.getTransactionsByDate(startDate, endDate);
		//
		
/*		for(Transaction tbd : listByDate) {
			if(list.contains(tbd.getBankAccount())){
				String a = tbd.getDate() + " // " + tbd.getAmount() + "   " + tbd.getComment();
				withdrawlList.add(a);
			}
		}*/
		
		
		
		
		listView = (ListView)findViewById(R.id.TranslistView);
		
		for(int j = 0; j < list.size(); j++){
			List<Transaction> transList = db.getWidrawls(list.get(j));
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
		
		List<Transaction> trans = new ArrayList<Transaction>();
		
		
		//all transactions by date
		//getWidrwals = db.getBankListByUser(userId);
		for(int j = 0; j < list.size(); j++){
			transList = db.getWidrawls(list.get(j));
			//transList = db.getBankListByUser(userId);
			//List<Transaction> l = db.getBankListByUser(userId);
			for(int i = 0; i< transList.size(); i++) {
				if(transList.get(i).getDate()<= endDate && transList.get(i).getDate() >= startDate ){
				String a = transList.get(i).getComment();
				if(!withdrawlList.contains(a)) {
					withdrawlList.add(a);
				}
				trans.add(transList.get(i));
				}
			}
		}
		
		
		double total = 0;
		print.add(("from "+startDate+" to " + endDate+":"));
		for(String type : withdrawlList) {
			double amount = 0;
			for(Transaction t: trans) {
				if(t.getComment().equalsIgnoreCase(type)){
					amount += t.getBalance();
				}
			}
			String temp = type+":     " + amount;
			print.add(temp);
			total += amount;
		}
		
		print.add(("total:     " + total));
		
		
		
		
		List<Transaction> Translist = db.getTransactionsByDate(startDate, endDate);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, print );
		listView.setAdapter(adapter);
		listView.setDivider(new ColorDrawable(Color.YELLOW));
		listView.setDividerHeight(2);
		
		
		
		Button back = (Button)findViewById(R.id.Rep_BTN_return);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(SpendingReportView.this, AccountManagePage.class);
				intent.putExtra("UserId", userId);
				startActivity(intent);
				SpendingReportView.this.finish();
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_account_manage, menu);
		return true;
	}
}
