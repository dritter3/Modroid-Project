package com.example.modroid_app;


	import android.os.Bundle;
	import android.app.Activity;
	import android.view.Menu;
	import android.view.View;
	import android.widget.Button;


	public class AddBankAccount extends Activity {
		private Button addBankAccount;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_bank_account);

		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.add_bank_account, menu);
			return true;
		}

	}
