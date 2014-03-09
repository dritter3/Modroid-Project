package com.example.modroid_app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;




public class BankAccountList extends ListActivity {
	

	private int userIDX;
	private UserAccount holder;
	private ListView accountList;
	
	
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
    
/*	userIDX = (this.getIntent()).getIntExtra("UserIDX", 0);
	holder = (UserAccount) UserList.getUserByIDX(userIDX);*/
	
	/*String[] list = new String[holder.getAccountList().size()];
	for(int i=0;i<list.length;i++){
		list[i] = holder.getAccountList().get(i).toString();
	}*/
	
    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };
    // use your custom layout
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        R.layout.activity_bank_account_list, R.id.label, values);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
  }
} 