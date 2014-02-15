package com.example.modroid_app;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Register_Page extends Activity {
	
	private UserList list;
	private Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register__page);
		
		submit = (Button)findViewById(R.id.BTN_submit);
		list = new UserList();
		
		//EditText createPSW = (EditText)findViewById(R.id.ET_createPSW);
		//EditText secondPSW = (EditText)findViewById(R.id.ET_typeinPSWAgain);
		
		((EditText)findViewById(R.id.ET_typeinPSWAgain)).addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable e) {
				String firstPSW = ((EditText)findViewById(R.id.ET_createPSW)).getText().toString();
				String secondPSW = ((EditText)findViewById(R.id.ET_typeinPSWAgain)).getText().toString();
				if(!secondPSW.equals(firstPSW)) {
					
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
		});
		
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = ((EditText)findViewById(R.id.ET_createUsername)).getText().toString();
				String firstPSW = ((EditText)findViewById(R.id.ET_createPSW)).getText().toString();
				String secondPSW = ((EditText)findViewById(R.id.ET_typeinPSWAgain)).getText().toString();
				String emailAddress = ((EditText)findViewById(R.id.ET_emailAddress)).getText().toString();
				if(name != null && firstPSW != null
						&& secondPSW != null && emailAddress != null) {
					if(secondPSW.equals(firstPSW)) {
						
						list.addNewUser(name, firstPSW);
						System.out.println(name+"   "+firstPSW);
						list.printAll();
						finish();
					}
				} else {
					
				}
			}
			
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register__page, menu);
		return true;
	}

}
