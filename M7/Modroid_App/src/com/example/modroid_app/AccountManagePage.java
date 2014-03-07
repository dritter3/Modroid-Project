package com.example.modroid_app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AccountManagePage extends Activity {

	
	//private UserAccount holder;
	private int userIDX;
	private UserAccount holder;
	//private ListView accountList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_manage_page);
		
		userIDX = (this.getIntent()).getIntExtra("UserIDX", 0);
		holder = (UserAccount) UserList.getUserByIDX(userIDX);
		
		TextView accountInfo = (TextView)findViewById(R.id.TV_holderName);
		accountInfo.setText("Welcome " + holder.getName());
		
		LinearLayout lLayout = (LinearLayout)findViewById(R.id.LL_list);

		for(BankAccount ba: holder.getAccounts()){
			TextView v = new TextView(AccountManagePage.this);
			v.setText(Integer.toString(ba.getAccountNumber()));
			v.setVisibility(View.VISIBLE);
			lLayout.addView(v);
		}

		Button addAccount = (Button)findViewById(R.id.BTN_AddBankAccount);
		addAccount.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent loadAddAccount = new Intent(AccountManagePage.this, AddBankAccount.class);
        		loadAddAccount.putExtra("user", userIDX);
        		startActivityForResult(loadAddAccount,0);
        	}
        });
        

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_manage_page, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);

		LinearLayout lLayout = (LinearLayout)findViewById(R.id.LL_list);

		if(resultCode == 1) {
			TextView v = new TextView(AccountManagePage.this);
			String text = data.getStringExtra("aNum");
			v.setText(text);
			lLayout.addView(v);
		}
		
	}
	

}
