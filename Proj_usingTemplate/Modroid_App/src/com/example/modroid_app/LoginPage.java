package com.example.modroid_app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends Activity {

	public static final String EXTRA_NAME = "name";
	
	
	//----for M4
	
	private String mName;
	private String mPassword;
	
	private Button loginButton;
	private TextView errorMSG;
	private int userIDX;
	
	//private UserList list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		
		loginButton = (Button) findViewById(R.id.loginRequest);
		errorMSG = (TextView) findViewById(R.id.error_msg);
		
		errorMSG.setVisibility(View.INVISIBLE);
		
		//UserList.printAll();
		
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mName = ((EditText)findViewById(R.id.ET_createUsername)).getText().toString();
				mPassword = ((EditText)findViewById(R.id.ET_createPSW)).getText().toString();
				userIDX = UserList.findUserName(mName);
		    	if(UserList.verifyAccount(mName, mPassword)&&userIDX >= 0){
		    		
		    		if(!mName.equals("admin")){
		    			Intent loadLoginPage = new Intent(LoginPage.this, AccountManagePage.class);
		    			loadLoginPage.putExtra("UserIDX", userIDX);
		    			startActivity(loadLoginPage);
		    		} else {
		    			Intent loadAdminPage = new Intent(LoginPage.this, AdminManage.class);
		    			startActivity(loadAdminPage);
		    		}
		    	} else {
		    			errorMSG.setVisibility(View.VISIBLE);
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
