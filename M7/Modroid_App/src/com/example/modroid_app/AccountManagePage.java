package com.example.modroid_app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	
	//private UserAccount holder;
	private int userIDX;
	private UserAccount holder;
	private ListView accountList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_manage_page);
		
		userIDX = (this.getIntent()).getIntExtra("UserIDX", 0);
		holder = (UserAccount) UserList.getUserByIDX(userIDX);
		
		Button bankList = (Button)findViewById(R.id.BTN_BankList);
		
		bankList.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(AccountManagePage.this, TransactionPage.class);
				
				startActivity(intent);
			}
		});
		ListView listview = (ListView) findViewById(R.id.LV_accountList);
        listview.setOnItemClickListener(this);
		
		/*
		TextView accountInfo = (TextView)findViewById(R.id.TV_holderName);
		accountInfo.setText("Welcome " + holder.getName());
		
		this.setList();*/

		/*
		ArrayList<HashMap<String, BankAccount>> map = new ArrayList<HashMap<String, BankAccount>>();
		
		ArrayAdapter<BankAccount> adp = new ArrayAdapter<BankAccount>(this, android.R.layout.activity_list_item,holder.getAccountList());
		accountList.setAdapter(adp);
		
		
		LinearLayout lLayout = (LinearLayout)findViewById(R.id.LL_list);
		
		for(BankAccount ba: holder.getAccountList()){
			HashMap<String,BankAccount> hm = new HashMap<String,BankAccount>();
			hm.put(ba.toString(),ba);
			map.add(hm);
			TextView v = new TextView(AccountManagePage.this);
			v.setText(Integer.toString(ba.getAccountNumber()));
			v.setVisibility(View.VISIBLE);
			lLayout.addView(v);
		}
		*/
		//SimplaAdapter adp = new SimpleAdapter(this,v,);

		
		
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
	
	
   public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
            // Then you start a new Activity via Intent
            Intent intent = new Intent();
            intent.setClass(this, TransactionPage.class);
            intent.putExtra("position", position);
            intent.putExtra("user", userIDX);
            // Or / And
            intent.putExtra("id", id);
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
	
	private void setList(){
		accountList = (ListView)findViewById(R.id.LV_accountList);
		String[] list = new String[holder.getAccountList().size()];
		for(int i=0;i<list.length;i++){
			list[i] = holder.getAccountList().get(i).toString();
		}
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		accountList.setAdapter(adp);
	}
	
	/*void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  }*/
	

}
