package com.example.firstproj;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;


public class SuccessLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success_login);
	//	EditText bom = (EditText) findViewById(R.id.succ);
		//bom.setText(wow);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.success_login, menu);
		return true;
	}
	
	public void printing(EditText a, EditText b) {
	
		
	}

}
