package com.example.modroid_app.view;

import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.database.DatabaseHandler;
import com.example.modroid_app.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * A account management activity that allows user to manage there bank accounts.
 * 
 * @author Team 45
 * @version 1.0
 */
public class UserAccountManageView extends Activity {
   
    @Override
    protected void onCreate(final Bundle instanceState) {
        super.onCreate(instanceState);
        setContentView(R.layout.activity_user_account_manage);
        
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
        final List<User> list = dbHandler.getUserList();
        final ListView listView = (ListView) findViewById(R.id.UserlistView);
       // for (User aUser : list) {
          //  System.out.println(aUser);
        //}
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setDivider(new ColorDrawable(Color.YELLOW));
        listView.setDividerHeight(2);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return true;
    }

}
