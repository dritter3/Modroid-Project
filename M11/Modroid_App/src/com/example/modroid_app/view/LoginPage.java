package com.example.modroid_app.view;

import com.example.modroid_app.R;
import com.example.modroid_app.database.DatabaseHandler;
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

/**
 * A login activity that asks user for account name and PSW.
 * 
 * @author Team 45
 * @version 1.0
 */
public class LoginPage extends Activity {

    /** this the instance of DatabaseHandler of the login page.*/
    private final DatabaseHandler dbHandler = new DatabaseHandler(this);
    
    @Override
    protected void onCreate(final Bundle instanceState) {
        
        super.onCreate(instanceState);
        setContentView(R.layout.activity_login_page);

        final String admingAccount = "admin";
        final User checkDuplicate = dbHandler.getUserInfo(admingAccount);
        if (checkDuplicate == null) {
            dbHandler.addUser(admingAccount, "pass123", null);
        }

        Button loginButton = (Button) findViewById(R.id.loginRequest);
        final TextView errorMSG = (TextView) findViewById(R.id.error_msg);
        errorMSG.setVisibility(View.INVISIBLE);
        
        loginButton.setOnClickListener(new OnClickListener() {
            public void onClick(final View view) {
                final String mName = ((EditText) findViewById(R.id.ET_createUsername))
                .getText().toString();
                
                final String mPassword = ((EditText) findViewById(R.id.ET_createPSW))
                        .getText().toString();

                // check user's id & password
                if (dbHandler.checkDuplicateUser(AdminAccount.USER)
                        && AdminAccount.USER.getName().equals(mName)
                        && AdminAccount.USER.getUserPSW().equals(mPassword)) {
                    final Intent adminPage = new Intent(LoginPage.this,
                            UserAccountManageView.class);
                    startActivity(adminPage);
                } else if (checkDuplicate != null
                        && checkDuplicate.getName().equals(mName)
                        && checkDuplicate.getUserPSW().equals(mPassword)) {

                    Intent loadLoginPage = new Intent(LoginPage.this,
                            AccountManagePage.class);
                    // send user'd row id to AccountManagePage
                    loadLoginPage.putExtra("UserId",
                            dbHandler.getUserIdRow(checkDuplicate));
                    startActivity(loadLoginPage);
                } else {
                    final TextView errorMSG = (TextView) findViewById(R.id.error_msg);
                    errorMSG.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final   Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_page, menu);
        return true;

    }

}
