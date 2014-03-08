package com.example.modroid_app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddBankAccount extends Activity {

	private Button cancel;
	private Button add;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bank_account);
		
		
		/*
		AlertDialog.Builder builder= new AlertDialog.Builder(AddBankAccount.this);
		builder.setTitle("User Agreement");
	    builder.setPositiveButton("accept", null);
	    builder.setNegativeButton("reject", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				finish();
			}
	    });
	    builder.setMessage("You are adding a new account, click accept to continue, or reject to go back.");
	    builder.show();*/
		

		final int userIDX = (this.getIntent()).getIntExtra("user", 0);
		
		
		add = (Button)findViewById(R.id.BTN_AddingAccount);
		cancel = (Button)findViewById(R.id.BTN_CancelAdding);


	
		
		
		//---cancel BTN
		cancel.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
					finish();
		}
	});
		//---end of cancel BTN
		
		
		//--submit BTN feature
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String accountNum = ((EditText)findViewById(R.id.ET_accountNumber)).getText().toString();
				String vCode = ((EditText)findViewById(R.id.ET_vCode)).getText().toString();
				String billingAddr = ((EditText)findViewById(R.id.ET_billingAddress)).getText().toString();
				String holderName = ((EditText)findViewById(R.id.ET_HolderName)).getText().toString();
				String alias = (((EditText)findViewById(R.id.ET_AccountAlias)).getText().toString());
				
				if ( accountNum.equals("") || vCode.equals("")
						|| billingAddr.equals("") || holderName.equals("")) {
					
					AlertDialog.Builder builder= new AlertDialog.Builder(AddBankAccount.this);
					builder.setTitle("Error");
				    builder.setPositiveButton("OK", null);
				    builder.setMessage("You have to fill all requried filds in order to verify your account");  
				    builder.show(); 
				} else {
					if(alias.equals("")){
						alias = "NULL";
					}
					final int num = Integer.parseInt(accountNum);
					final int code = Integer.parseInt(vCode);
					if(((UserAccount)UserList.getUserByIDX(userIDX)).addAccount(num, code, billingAddr, holderName, alias)) {
						Intent add = new Intent();
						add.putExtra("num",num);
						AddBankAccount.this.setResult(10,add);
							AlertDialog.Builder builder= new AlertDialog.Builder(AddBankAccount.this);
							builder.setTitle("Confirm message");
						    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
								public void onClick(DialogInterface dialog, int which){
									Intent add = new Intent();
									add.putExtra("aNum",accountNum);
									AddBankAccount.this.setResult(1,add);
									AddBankAccount.this.finish();
								}
						    });
						    builder.setMessage("Account has been successfully created.");
						    builder.show();
							
						} else {
							new AlertDialog.Builder(AddBankAccount.this)
						    .setTitle("Error")  
						    .setPositiveButton("OK", null)  
						    .setMessage("AccountNumber is already existed.")  
						    .show();
						}
					} 
				}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_bank_account, menu);
		return true;
	}

}
