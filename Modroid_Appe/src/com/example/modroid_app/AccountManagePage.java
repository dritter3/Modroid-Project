package com.example.modroid_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class AccountManagePage extends Activity {
	private Button addBankAccount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_manage_page);
		
		addBankAccount = (Button)findViewById(R.id.BTN_AddBankAccount);
		
		addBankAccount.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent loadAdding = new Intent(AccountManagePage.this, AddBankAccount.class);
				startActivity(loadAdding);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_manage_page, menu);
		return true;
	}

}
