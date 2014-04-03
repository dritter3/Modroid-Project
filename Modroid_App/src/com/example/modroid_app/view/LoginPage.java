package com.example.modroid_app.view;


import com.example.modroid_app.R;
import com.example.modroid_app.database.DatabaseHandler;
import com.example.modroid_app.database.UserTableContract.FeedEntry;
import com.example.modroid_app.model.User;
import com.example.modroid_app.model.Admin.AdminAccount;

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
	private DatabaseHandler db = new DatabaseHandler(this);
	
	//----for M4
	
	private String mName;
	private String mPassword;
	
	private Button loginButton;
	private TextView errorMSG;
	
	//private UserList list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		
		User checkDuplicate = db.getUserInfo("admin");
		if(checkDuplicate == null) {
			db.addUser("admin", "pass123", null);
		}
		
		loginButton = (Button) findViewById(R.id.loginRequest);
		errorMSG = (TextView) findViewById(R.id.error_msg);
		
		errorMSG.setVisibility(View.INVISIBLE);
		
		//UserList.printAll();
		
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mName = ((EditText)findViewById(R.id.ET_createUsername)).getText().toString();
				mPassword = ((EditText)findViewById(R.id.ET_createPSW)).getText().toString();

				User checkDuplicate = db.getUserInfo(mName);
				
				// check user's id & password
				if(db.checkDuplicateUser(AdminAccount.user) && AdminAccount.user.getName().equals(mName) &&
						AdminAccount.user.getPSW().equals(mPassword)) {
					Intent adminPage = new Intent(LoginPage.this, UserAccountManageView.class);
					startActivity(adminPage);
				} else if(checkDuplicate != null &&
						checkDuplicate.getName().equals(mName) &&
						checkDuplicate.getPSW().equals(mPassword)) {

		    		Intent loadLoginPage = new Intent(LoginPage.this, AccountManagePage.class);
		    		// send user'd row id to AccountManagePage
					loadLoginPage.putExtra("UserId", db.getUserIdRow(checkDuplicate));
		    		startActivity(loadLoginPage);
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
