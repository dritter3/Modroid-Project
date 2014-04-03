package com.example.modroid_app.view;
import java.util.ArrayList;
import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.database.DatabaseHandler;
import com.example.modroid_app.model.Transaction;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/**
 * A report activity that generates the spending report for user to view.
 * 
 * @author Team 45
 * @version 1.0
 */
public class SpendingReportView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_report);
        
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
        
        final Intent intent = this.getIntent();
        final int userId = intent.getIntExtra("user", 0);
        final int startDate = intent.getIntExtra("start", 0);
        final int endDate = intent.getIntExtra("end", 0);

        Log.d("spending getIntExtra userid", String.valueOf(userId));
        final List<Integer> list = dbHandler.getBankListByUser(userId); // get bank account list for the
                                             // user
        //List<List<Double>> withdrawlsByBank = new ArrayList<List<Double>>();
        //List<Double> withdrawls = new ArrayList<Double>();
        List<String> withdrawlList;
        withdrawlList = new ArrayList<String>();
        
        List<String> print;
        print = new ArrayList<String>();

        final ListView listView = (ListView) findViewById(R.id.TranslistView);
        
        List<Transaction> transList;
        
        List<Transaction> trans;
        trans = new ArrayList<Transaction>();
        
        for (int j = 0; j < list.size(); j++) {
            transList = dbHandler.getWithdrawals(list.get(j));
            for (int i = 0; i < transList.size(); i++) {
                if (transList.get(i).getDate() <= endDate
                        && transList.get(i).getDate() >= startDate) {
                    final String toAdd = transList.get(i).getComment();
                    if (!withdrawlList.contains(toAdd)) {
                        withdrawlList.add(toAdd);
                    }
                    trans.add(transList.get(i));
                }
            }
        }

        double total = 0;
        final String dateTitle = "from " + startDate + " to " + endDate + ":";
        print.add(dateTitle);
        for (final String type : withdrawlList) {
            double amount = 0;
            for (final Transaction tran : trans) {
                if (tran.getComment().equalsIgnoreCase(type)) {
                    amount += tran.getBalance();
                }
            }
            String temp = type + ":     " + amount;
            print.add(temp);
            total += amount;
        }
        final String totalAmount = "total:     " + total;
        print.add(totalAmount);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, print);
        listView.setAdapter(adapter);
        listView.setDivider(new ColorDrawable(Color.YELLOW));
        listView.setDividerHeight(2);

        Button back = (Button) findViewById(R.id.Rep_BTN_return);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                Intent intent = new Intent(SpendingReportView.this,
                        AccountManagePage.class);
                intent.putExtra("UserId", userId);
                startActivity(intent);
                SpendingReportView.this.finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return true;
    }
}
