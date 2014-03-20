package com.example.modroid_app.view;

import com.example.modroid_app.R;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.Transaction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class TransactionPage extends Activity {
	
	private double acccountNumber;
	private EditText amount;
	private TextView bal;
	private BankAccount bank;
	private Transaction trans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_page);
		Intent intent = getIntent();
		
		int position = intent.getIntExtra("position", 0);
	/*	User user = UserList.getUserByIDX(intent.getIntExtra("user", 0));
		UserAccount userAcc = (UserAccount) user;
		bank = userAcc.getAccountByOrder(position);
		
		if(bank.isEmpty()) {
			bank.addTransaction(new Transaction(0));
		}
		
		bal = (TextView)findViewById(R.id.TV_ShowBalance);
		bal.setText(Double.toString(bank.getLastTransaction().getbalance()));
		
		amount = (EditText)findViewById(R.id.ET_Amount);
		
		
		
		Button deposit = (Button)findViewById(R.id.BTN_Deposit);
		deposit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				double a = Double.parseDouble(amount.getText().toString());
				String currBalS = (String) bal.getText();
				double currBal = Double.parseDouble(currBalS);
				Transaction newTrans = new Transaction(currBal);
				newTrans.makeTrans(a);
				//bank.addTransaction(newTrans);
				bal.setText(Double.toString(currBal + a));
				
			}
		});
		
		Button withdraw = (Button)findViewById(R.id.BTN_Withdraw);
		withdraw.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				double a = Double.parseDouble(amount.getText().toString());
				String currBalS = (String) bal.getText();
				double currBal = Double.parseDouble(currBalS);
				Transaction newTrans = new Transaction(currBal);
				if(currBal >= a) {
					newTrans.makeTrans(-a);
					bank.addTransaction(newTrans);
					bal.setText(Double.toString(currBal - a));
				}
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.transaction_page, menu);
		return true;
	}

}
