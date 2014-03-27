package com.example.modroid_app.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.DatePicker;
import com.example.modroid_app.R;

public class DatePickerPage extends Activity{
	private DatePicker startdpResult;
	private DatePicker enddpResult;
	private Button btnChangeDate;
 
	private int userID;
	private int startYear;
	private int startMonth;
	private int startDay;
	
	private int endYear;
	private int endMonth;
	private int endDay;
 
	static final int START_DATE_DIALOG_ID = 0;
	static final int END_DATE_DIALOG_ID = 1;
	
	//private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_date_picker);
		userID = (this.getIntent()).getIntExtra("UserId", 0);
	//	addListenerOnButton();
 
		
		startdpResult = (DatePicker) findViewById(R.id.startdpResult);
     	startdpResult.init(2013, 01, 01, null);
		enddpResult = (DatePicker) findViewById(R.id.enddpResult);
		
	}
 
/*	public void setCurrentDateOnView() {
		startdpResult = (DatePicker) findViewById(R.id.startdpResult);
 
		final Calendar c = Calendar.getInstance();
		startYear = c.get(Calendar.YEAR);
		startMonth = c.get(Calendar.MONTH);
		startDay = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into datepicker
		startdpResult.init(2013, 01, 01, null);
 
	}
	
	public void addListenerOnButton() {
 
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
 
		btnChangeDate.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DatePickerPage.this, SpendingReportView.class);
				intent.putExtra("user", userID);
				intent.putExtra("start", (startYear*10000+startMonth*100+startDay));
				intent.putExtra("end", (endYear*10000+endMonth*100+endDay));
				
				startActivityForResult(intent,0);
				
				System.out.println(startYear+"-"+startDay+"-"+startMonth);
				System.out.println(endYear+"-"+endDay+"-"+endMonth);
 
			}
 
		});
 
	}
 
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case START_DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, startDatePickerListener, 
                         startYear, startMonth,startDay);
		case END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, endDatePickerListener, 
                    endYear, endMonth,endDay);
		}
		return null;
	}
 
	private DatePickerDialog.OnDateSetListener startDatePickerListener 
                = new DatePickerDialog.OnDateSetListener() {
 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			startYear = selectedYear;
			startMonth = selectedMonth;
			startDay = selectedDay;
 
			// set selected date into datepicker also
			//startdpResult.init(startYear, startMonth, startDay, null);
 
		}
	};
	
	private DatePickerDialog.OnDateSetListener endDatePickerListener 
    = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
		endYear = selectedYear;
		endMonth = selectedMonth;
		endDay = selectedDay;
		
		// set selected date into datepicker also
		//enddpResult.init(endYear, endMonth, endDay, null);
		
		}
	};*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_account_manage, menu);
		return true;
	}
}
