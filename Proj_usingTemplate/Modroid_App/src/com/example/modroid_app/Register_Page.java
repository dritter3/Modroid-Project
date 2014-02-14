package com.example.modroid_app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Register_Page extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register__page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register__page, menu);
		return true;
	}

}
