package com.example.firstproj;

import android.os.Bundle;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends FragmentActivity  {
	//private final String adminId = "admin";
	//private final String adminPassword = "pass123";
	
	private UserList list;
	//private UserAccount toAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		final EditText inputId;
		final EditText inputPassword;
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		inputId = (EditText)findViewById(R.id.edit_id);
		inputPassword = (EditText) findViewById(R.id.edit_password);
		list = new UserList();
	
		
		Button loginButton = (Button) findViewById(R.id.button_login);
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {

				String idString = inputId.getText().toString();
				String passwordString = inputPassword.getText().toString();
				System.out.println("you just clicked the button");
				if(list.verifyAccount(idString, passwordString) ) {
					Intent intent = new Intent(LoginPage.this, SuccessLogin.class);
					startActivity(intent);
				} else {
					LoginAlert alert = new LoginAlert();
					alert.show(getSupportFragmentManager(), "oh");
					}
				}
			});
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_page, menu);
		return true;
	}

}
