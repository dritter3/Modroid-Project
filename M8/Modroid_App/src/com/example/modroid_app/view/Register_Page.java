package com.example.modroid_app.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.modroid_app.R;
import com.example.modroid_app.SQLHelper.*;
import com.example.modroid_app.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.DialogInterface;

/**
 * An account registration activity that allows user to register.
 * 
 * @author Team 45
 * @version 1.0
 */
public class Register_Page extends Activity {

    private final DatabaseHandler dbHandler = new DatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__page);

        Button submit = (Button) findViewById(R.id.BTN_submit);
        Button cancel = (Button) findViewById(R.id.BTN_cancel);

        final TextView dupWarning = (TextView) findViewById(R.id.TV_duplicatedName);
        final TextView notMatchWarning = (TextView) findViewById(R.id.TV_pswNotMatchWarning);
        final TextView emailFormatWarning = (TextView) findViewById(R.id.TV_emailAddressFormatWarning);

        dupWarning.setVisibility(View.INVISIBLE);
        notMatchWarning.setVisibility(View.INVISIBLE);
        emailFormatWarning.setVisibility(View.INVISIBLE);

        // --Alert if the account name is already existed
        ((EditText) findViewById(R.id.ET_createUsername))
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable e) {
                        String accountName = ((EditText) findViewById(R.id.ET_createUsername))
                                .getText().toString();
                        // CHECK THIS PART

                        if (dbHandler.checkDuplicateName(accountName)) {
                            dupWarning.setVisibility(View.VISIBLE);
                        } else {
                            dupWarning.setVisibility(View.INVISIBLE);
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                            int arg2, int arg3) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start,
                            int before, int count) {
                    }
                });

        // --end of duplicated name alert

        // --Alert if the first psw and second psw are not match
        ((EditText) findViewById(R.id.ET_typeinPSWAgain))
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable e) {
                        String firstPSW = ((EditText) findViewById(R.id.ET_createPSW))
                                .getText().toString();
                        String secondPSW = ((EditText) findViewById(R.id.ET_typeinPSWAgain))
                                .getText().toString();
                        if (!secondPSW.equals(firstPSW)) {
                            notMatchWarning.setVisibility(View.VISIBLE);
                        } else {
                            notMatchWarning.setVisibility(View.INVISIBLE);
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                            int arg2, int arg3) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start,
                            int before, int count) {
                    }
                });
        // ---end of not match alert

        // --check email address format
        ((EditText) findViewById(R.id.ET_emailAddress))
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable e) {
                        String emailAdd = ((EditText) findViewById(R.id.ET_emailAddress))
                                .getText().toString();
                        Pattern p = Pattern.compile("[\\w||[.]]+@\\w+[.]\\w+");
                        Matcher verify = p.matcher(emailAdd);
                        if (verify.matches()) {
                            emailFormatWarning.setVisibility(View.INVISIBLE);
                        } else {
                            emailFormatWarning.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                            int arg2, int arg3) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start,
                            int before, int count) {
                    }
                });

        // --check email address format

        // ---cancel BTN
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // ---end of cancel BTN

        // --submit BTN feature
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.ET_createUsername))
                        .getText().toString();
                String firstPSW = ((EditText) findViewById(R.id.ET_createPSW))
                        .getText().toString();
                String secondPSW = ((EditText) findViewById(R.id.ET_typeinPSWAgain))
                        .getText().toString();
                String emailAddress = ((EditText) findViewById(R.id.ET_emailAddress))
                        .getText().toString();

                if (name.equals("") || firstPSW.equals("")
                        || secondPSW.equals("") || emailAddress.equals("")) {

                    new AlertDialog.Builder(Register_Page.this)
                            .setTitle("Error")
                            .setPositiveButton("OK", null)
                            .setMessage(
                                    "Invalid infomation. Please check your information and try again!")
                            .show();
                } else {
                    if (secondPSW.equals(firstPSW)) {

                        User checkDuplicate = dbHandler.getUserInfo(name);
                        User newUser = new User(name, firstPSW, emailAddress);

                        // check if the userName already exists
                        if (checkDuplicate == null
                                && !newUser.equals(checkDuplicate)) {

                            dbHandler.addUser(newUser);

                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    Register_Page.this);
                            builder.setTitle("Message");
                            builder.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            finish();
                                        }
                                    });
                            builder.setMessage("Account has been successfully created.");
                            builder.show();

                        } else {
                            new AlertDialog.Builder(Register_Page.this)
                                    .setTitle("Error")
                                    .setPositiveButton("OK", null)
                                    .setMessage(
                                            "AccountName is already existed, please try a different name.")
                                    .show();
                        }
                    } else {
                        new AlertDialog.Builder(Register_Page.this)
                                .setTitle("Error")
                                .setPositiveButton("OK", null)
                                .setMessage(
                                        "The passwords you typed in do not match")
                                .show();
                    }
                }
            }
        });
        // --end of submit BTN

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register__page, menu);
        return true;
    }

}
