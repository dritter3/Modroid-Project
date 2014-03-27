package com.example.modroid_app.view;

import java.util.Calendar;

import com.example.modroid_app.R;
import com.example.modroid_app.R.layout;
import com.example.modroid_app.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class SelectDate extends Activity {

	
	
	private DatePicker startdpResult;
	private DatePicker enddpResult;
	private Button btn;
 
	private int userID;
	private int startYear;
	private int startMonth;
	private int startDay;
	
	private int endYear;
	private int endMonth;
	private int endDay;
 
	static final int START_DATE_DIALOG_ID = 0;
	static final int END_DATE_DIALOG_ID = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_date);
		
		userID = (this.getIntent()).getIntExtra("user", 0);
		addListenerOnButton();
	
		startdpResult = (DatePicker) findViewById(R.id.startdpResult);
		
		startdpResult.init(2014, 02, 01,new OnDateChangedListener(){

            @Override
            public void onDateChanged(DatePicker view, int year,
                    int month, int day) {
                // TODO Auto-generated method stub
                startYear=year;
                startMonth=month;
                startDay=day;

            }
        });
		//startdpResult.init(2013, 01, 01, null);
		enddpResult = (DatePicker) findViewById(R.id.enddpResult);
		
		enddpResult.init(2014, 02, 30,new OnDateChangedListener(){

            @Override
            public void onDateChanged(DatePicker view, int year,
                    int month, int day) {
                // TODO Auto-generated method stub
                endYear=year;
                endMonth=month;
                endDay=day;

            }
        });
	}
	
	public void addListenerOnButton() {
		 
		btn = (Button) findViewById(R.id.btnChangeDate);
 
		btn.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(SelectDate.this, SpendingReportView.class);
				intent.putExtra("user", userID);
				intent.putExtra("start", (startYear*10000+(startMonth+1)*100+startDay));
				intent.putExtra("end", (endYear*10000+(endMonth+1)*100+endDay));
				
				startActivityForResult(intent,0);
				
				SelectDate.this.finish();
				
				System.out.println(startYear+"-"+startDay+"-"+startMonth);
				System.out.println(endYear+"-"+endDay+"-"+endMonth);
 
			}
 
		});
		
		
		/*Button back = (Button)findViewById(R.id.SD_BTN_return);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(SelectDate.this, AccountManagePage.class);
				intent.putExtra("UserId", userId);
				startActivity(intent);
				SelectDate.this.finish();
				
			}
		});*/
	}
	
	
	public void setCurrentDateOnView() {
		startdpResult = (DatePicker) findViewById(R.id.startdpResult);
 
		final Calendar c = Calendar.getInstance();
		startYear = c.get(Calendar.YEAR);
		startMonth = c.get(Calendar.MONTH);
		startDay = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into datepicker
		startdpResult.init(2013, 01, 01, null);
 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_date, menu);
		return true;
	}

}
