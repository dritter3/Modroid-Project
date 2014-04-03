package com.example.modroid_app.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.modroid_app.R;
import com.example.modroid_app.SQLHelper.DatabaseHandler;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.Transaction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;


/**
 * A transaction activity that allows user to do transaction
 * and shows transaction activities.
 * 
 * @author Team 45
 * @version 1.0
 */
public class TransactionPage extends Activity {

    private EditText amount;
    private TextView balTextview;
    private BankAccount bank;
    private Transaction trans;
    private int bankAccountNumber;
    private DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_page);
        Intent intent = getIntent();

        int position = intent.getIntExtra("position", 0);

        bankAccountNumber = intent.getIntExtra("bankAccountNumber", 0);

        // BankAccountNumber shoulnd't be 0
        if (bankAccountNumber == 0) {
            Log.d("TransactionPage", "NoBankAccount!");
        }

        balTextview = (TextView) findViewById(R.id.TV_ShowBalance);
        balTextview.setText(Double.toString(db
                .getBankBalance(bankAccountNumber)));

        amount = (EditText) findViewById(R.id.ET_Amount);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        final String formattedDate = df.format(calendar.getTime());

        Button deposit = (Button) findViewById(R.id.BTN_Deposit);
        deposit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                double a = Double.parseDouble(amount.getText().toString());
                String currBalS = (String) balTextview.getText();
                double currBal = Double.parseDouble(currBalS);
                Transaction newTrans = new Transaction(currBal, Integer
                        .parseInt(formattedDate),
                        ((EditText) findViewById(R.id.ET_comment)).getText()
                                .toString());
                newTrans.makeTrans(a);
                db.addTransaction(newTrans, bankAccountNumber);
                // balTextview.setText(Double.toString(currBal + a));

                balTextview.setText(Double.toString(db
                        .getBankBalance(bankAccountNumber)));

            }
        });

        Button withdraw = (Button) findViewById(R.id.BTN_Withdraw);
        withdraw.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                double a = Double.parseDouble(amount.getText().toString());
                String currBalS = (String) balTextview.getText();
                double currBal = Double.parseDouble(currBalS);
                Transaction newTrans = new Transaction(currBal, Integer
                        .parseInt(formattedDate),
                        ((EditText) findViewById(R.id.ET_comment)).getText()
                                .toString());
                if (currBal >= a) {
                    newTrans.makeTrans(-a);
                    db.addTransaction(newTrans, bankAccountNumber);
                    // balTextview.setText(Double.toString(currBal - a));

                    balTextview.setText(Double.toString(db
                            .getBankBalance(bankAccountNumber)));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.transaction_page, menu);
        return true;
    }

}
