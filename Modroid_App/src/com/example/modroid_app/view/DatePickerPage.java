package com.example.modroid_app.view;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.lang.reflect.Array;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.modroid_app.R;
import com.example.modroid_app.database.DatabaseHandler;
import com.example.modroid_app.model.BankAccount;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);
		userID = (this.getIntent()).getIntExtra("UserId", 0);
		addListenerOnButton();
 
	}
 
	public void setCurrentDateOnView() {
		startdpResult = (DatePicker) findViewById(R.id.startdpResult);
 
		final Calendar c = Calendar.getInstance();
		startYear = c.get(Calendar.YEAR);
		startMonth = c.get(Calendar.MONTH);
		startDay = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into datepicker
		startdpResult.init(startYear, startMonth, startDay, null);
 
	}
	
	public void addListenerOnButton() {
 
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
 
		btnChangeDate.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DatePickerPage.this, SpendingReportView.class);
				intent.putExtra("user", userID);
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
			startdpResult.init(startYear, startMonth, startDay, null);
 
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
		enddpResult.init(endYear, endMonth, endDay, null);
		
		}
	};
}
