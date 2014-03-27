package com.example.modroid_app.view;

import java.lang.reflect.Array;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.SQLHelper.DatabaseHandler;
import com.example.modroid_app.model.BankAccount;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AccountManagePage extends Activity implements OnItemClickListener {

	

	private int userID; // user's row Id
	private ListView accountList;
	private DatabaseHandler db = new DatabaseHandler(this);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_manage_page);
		
		// get rowId of the User
		userID = (this.getIntent()).getIntExtra("UserId", 0);
		
		Button bankList = (Button)findViewById(R.id.BTN_BankList);
		
		bankList.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(AccountManagePage.this, TransactionPage.class);
				
				startActivity(intent);
			}
		});
		ListView listview = (ListView) findViewById(R.id.LV_accountList);
        listview.setOnItemClickListener(this);
        this.setList();
		/*
		TextView accountInfo = (TextView)findViewById(R.id.TV_holderName);
		accountInfo.setText("Welcome " + holder.getName());
		
		this.setList();*/

		//SimplaAdapter adp = new SimpleAdapter(this,v,);

		Button addAccount = (Button)findViewById(R.id.BTN_AddBankAccount);
		addAccount.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent loadAddAccount = new Intent(AccountManagePage.this, AddBankAccount.class);
        		// send user's Id to AddBankAccount page
        		loadAddAccount.putExtra("user", userID);
        		startActivityForResult(loadAddAccount,0);
        	}
        });
        
		Button spendingReport = (Button)findViewById(R.id.BTN_SpendingReport);
		spendingReport.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent setDate = new Intent(AccountManagePage.this, SelectDate.class);
        		// send user's Id to AddBankAccount page
        		setDate.putExtra("user", userID);
        		startActivityForResult(setDate,0);
        		
        		AccountManagePage.this.finish();
        	}
        });

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_manage_page, menu);
		return true;
	}
	
	/**
	 * The method when pressed Bank Account
	 * move to Transaction page
	 */
   public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
            // Then you start a new Activity via Intent
            Intent intent = new Intent();
            intent.setClass(this, TransactionPage.class);
            intent.putExtra("position", position);
            intent.putExtra("user", userID);
            //Cursor c = (Cursor) parent.getItemAtPosition(position); // check here again
            int bankAccountNumber = Integer.parseInt((String)parent.getItemAtPosition(position));
            //System.out.println("Get Position: " + c.getString(0));
            Log.d("Click Item", String.valueOf(bankAccountNumber));
            // the row id of item that's clicked
            intent.putExtra("bankAccountNumber", bankAccountNumber);
            startActivity(intent);
    }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		//userIDX = data.getIntExtra("aNum",0);
		//holder = (UserAccount) UserList.getUserByIDX(userIDX);

		if(resultCode == 1) {
			this.setList();
		}
	}
	// This is for in case I want to see all the Bank List
	/*private void setList(){
		accountList = (ListView)findViewById(R.id.LV_accountList);
		List<BankAccount> bankList = db.getBankList();
		String[] list = new String[bankList.size()];
		

		for(int i=0;i<list.length;i++){
			list[i] = bankList.get(i).toString();
		}
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		accountList.setAdapter(adp);
	}*/
	
	private void setList(){
		accountList = (ListView)findViewById(R.id.LV_accountList);
		List<Integer> bankList = db.getBankListByUser(userID);
		String[] list = new String[bankList.size()];
		

		for(int i=0;i<list.length;i++){
			list[i] = bankList.get(i).toString();
		}
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		accountList.setAdapter(adp);
	}

	

}
