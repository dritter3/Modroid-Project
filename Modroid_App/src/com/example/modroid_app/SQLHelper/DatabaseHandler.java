package com.example.modroid_app.SQLHelper;
import java.util.ArrayList;
import java.util.List;

import com.example.modroid_app.SQLHelper.UserTableContract.FeedEntry;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.Transaction;
import com.example.modroid_app.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper{
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_User_ENTRIES = 
			"CREATE TABLE " + FeedEntry.TABLE_NAME_User + " (" +
			FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_User_NAME + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_User_PASSWORD + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_User_MAIL + TEXT_TYPE +
					 " )";
	// previous one
	/*private static final String SQL_CREATE_Bank_ENTRIES = 
			"CREATE TALBE " + FeedEntry.TABLE_NAME_Bank + " (" +
			FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_Bank_Number + " INTEGER" + COMMA_SEP +
					FeedEntry.COLUMN_Bank_Verify + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_Bank_BillAddress + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_Bank_holderName + TEXT_TYPE + COMMA_SEP + 
					FeedEntry.COLUMN_Bank_Balance + " INTEGER" +
					" )";*/
	private static final String SQL_CREATE_Bank_ENTRIES = 
			"CREATE TABLE " + FeedEntry.TABLE_NAME_Bank + " (" +
			FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_Bank_Number + " INTEGER" + COMMA_SEP +
					FeedEntry.COLUMN_Bank_Verify + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_Bank_BillAddress + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_Bank_holderName + TEXT_TYPE + COMMA_SEP + 
					FeedEntry.COLUMN_Bank_Balance + " INTEGER" + COMMA_SEP +
					FeedEntry.COLUMN_Bank_UserId + " INTEGER" +
					" )";
	
			
	public static final String SQL_CREATE_Transaction_ENTRIES = 
			"CREATE TABLE " + FeedEntry.TABLE_NAME_Transaction + " (" +
					FeedEntry._ID + " INTEGER PRIMARY KEY, " +
					FeedEntry.COLUMN_Trans_Date + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_Trans_Bank_Account + " INTEGER" + COMMA_SEP +
					FeedEntry.COLUMN_Trans_Bank_Balance + " INTEGER" + COMMA_SEP +
					FeedEntry.COLUMN_Trans_Amount + " INTEGER" + COMMA_SEP +
					FeedEntry.COLUMN_Trans_Comment + TEXT_TYPE +
					" )";
					
	
	
	private static final String SQL_DELETE_User_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_User;
	private static final String SQL_DELETE_Bank_ENTRIES = 
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_Bank;
	private static final String SQL_DELETE_Transaction_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_Transaction;
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Users.db"; // should change this to another name
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_User_ENTRIES);
		db.execSQL(SQL_CREATE_Bank_ENTRIES);
		db.execSQL(SQL_CREATE_Transaction_ENTRIES);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_User_ENTRIES);
		db.execSQL(SQL_DELETE_Bank_ENTRIES);
		db.execSQL(SQL_DELETE_Transaction_ENTRIES);
		onCreate(db);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	// check if I have to delete this
	/**
	 * Add user to db
	 * @param name
	 * @param password
	 * @param emailAddress
	 */
	public void addUser(String name, String password, String emailAddress) {
		Log.d("addUser : ", name);
		
		SQLiteDatabase db = this.getWritableDatabase();
		Log.d("pass",password);
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_User_NAME, name);
		values.put(FeedEntry.COLUMN_User_PASSWORD, password);
		values.put(FeedEntry.COLUMN_User_MAIL, emailAddress);

		
		//long userId = 
		db.insert(FeedEntry.TABLE_NAME_User,
				null,
				values);
		
		db.close();
	}
	
	
	/*
	public long addUserBank(long userId, long bankId) {

		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_UB_UserId, userId);
		values.put(FeedEntry.COLUMN_UB_BankId, bankId);
		
		long id = db.insert(FeedEntry.TALBE_NAME_User_Bank, null, values);
		
		return id;
		
	}*/
	
	/**
	 * Add BankAccount to db
	 * @param acc
	 * @param user
	 * @return
	 */
	public long addBank(BankAccount acc, User user) {

		//SQLiteDatabase db = this.getWritableDatabase();
		int userId = getUserIdRow(user);
	/*	ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_Bank_Number, acc.getAccountNumber());
		values.put(FeedEntry.COLUMN_Bank_Verify, acc.getVerificationCode());
		values.put(FeedEntry.COLUMN_Bank_BillAddress, acc.getBillAddress());
		values.put(FeedEntry.COLUMN_Bank_holderName, acc.getHolderName());
		values.put(FeedEntry.COLUMN_Bank_Balance, acc.getBalance());
		values.put(FeedEntry.COLUMN_Bank_UserId, userId);
		
		long id = db.insert(FeedEntry.TABLE_NAME_Bank, null, values);
		*/
		return addBank(acc, userId);
	}
	
	/**
	 * Add BankAccount to db with user's id
	 * @param acc
	 * @param userId
	 * @return
	 */
	public long addBank(BankAccount acc, int userId) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_Bank_Number, acc.getAccountNumber());
		values.put(FeedEntry.COLUMN_Bank_Verify, acc.getVerificationCode());
		values.put(FeedEntry.COLUMN_Bank_BillAddress, acc.getBillAddress());
		values.put(FeedEntry.COLUMN_Bank_holderName, acc.getHolderName());
		values.put(FeedEntry.COLUMN_Bank_Balance, acc.getBalance());
		values.put(FeedEntry.COLUMN_Bank_UserId, userId);
		
		long id = db.insert(FeedEntry.TABLE_NAME_Bank, null, values);
		
		return id;
	}
	
	/**
	 * make transaction and save it into db
	 * @param trans
	 * @param bankId
	 * @return
	 */
	public long addTransaction(Transaction trans, int bankId) { // check if it should return long
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_Trans_Date, trans.getDate());
		values.put(FeedEntry.COLUMN_Trans_Bank_Account, bankId);
		values.put(FeedEntry.COLUMN_Trans_Bank_Balance, trans.getBalance());
		values.put(FeedEntry.COLUMN_Trans_Amount, trans.getAmount());
		values.put(FeedEntry.COLUMN_Trans_Comment, trans.getComment());
		
		long id = db.insert(FeedEntry.TABLE_NAME_Transaction, null, values);
		Log.d("addTransaction",String.valueOf(trans.getAmount()));
		updateBankBalance(trans.getBalance(), bankId);
		
		return id;
	}
	
	public List<Double> getWidrawls(int bankId){
		SQLiteDatabase db = this.getReadableDatabase();
		
		
		String qu = "SELECT * FROM " + FeedEntry.TABLE_NAME_Transaction + " WHERE " +
				 FeedEntry.COLUMN_Trans_Bank_Account + " = " + bankId;
		Cursor c = db.rawQuery(qu, null);
		
		List<Double> list = new ArrayList<Double>();
		if(c.moveToFirst()) {
			do{
				if(c.getDouble(4)<0){
					list.add(c.getDouble(4));
					Log.d("getTransactionList", String.valueOf(c.getDouble(1)));
				}
			} while(c.moveToNext());
		}
		
		return list;
	}
	
	/**
	 * update Bank Account's balance when transaction is made
	 * @param bal
	 * @param bankId
	 */
	public void updateBankBalance(double bal, int bankId) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_Bank_Balance, bal);
		
		int count = db.update(FeedEntry.TABLE_NAME_Bank, values,
				FeedEntry.COLUMN_Bank_Number + " LIKE ?", new String[] {String.valueOf(bankId)});
	}
	
	/**
	 * Get user's row id
	 * @param user
	 * @return
	 */
	public int getUserIdRow(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User input is null!");
		}
		SQLiteDatabase db = this.getReadableDatabase();
		int key = 0;
		Cursor c = db.query(FeedEntry.TABLE_NAME_User,
				null,
				FeedEntry.COLUMN_User_NAME + " = ?",
				new String[] {user.getName()},
				null,
				null,
				null);
		// check if it's null or Primary Key
		if (c != null) {
			if(c.moveToFirst()) {
				c.moveToFirst(); // check out why do i have to do twice
				key = c.getInt(0);
			}
		}
		return key;
		
	}
	
	
	public void addUser(User user) {
		Log.d("addUser2", user.toString());
		SQLiteDatabase db = this.getWritableDatabase();
		

		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_User_NAME, user.getName());
		values.put(FeedEntry.COLUMN_User_PASSWORD, user.getPSW());
		values.put(FeedEntry.COLUMN_User_MAIL, user.getEmail());
		
		db.insert(FeedEntry.TABLE_NAME_User,
				null,
				values);
		Log.d("getUserInfo/ " + user.getName() + " : ", user.toString());
		db.close();
		
	}
	
	public User getUserInfo(String userName) {
		if (userName == null) {
			throw new IllegalArgumentException("the data is null!");
		}
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor c = db.query(FeedEntry.TABLE_NAME_User, 
				FeedEntry.COLUMNS_User, 
				FeedEntry.COLUMN_User_NAME + " = ?",
				new String[] {userName},
				null,
				null, 
				null);
		User user = new User();
		if(c != null) {
			if(c.moveToFirst()){
				//c.moveToFirst();
				do{
			user.setName(c.getString(0));
			user.setPassword(c.getString(1));
			user.setEmail(c.getString(2));
		}while(c.moveToNext());
				}}
		Log.d("getting user's info: ", user.toString());
		if(user.getName() == null) {
			return null;
		}
		return user;
		
	}
	
	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		
		String query = "SELECT * FROM " + FeedEntry.TABLE_NAME_User;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		
		User user = null;
		if(c.moveToFirst()) {
			do {
				user = new User();
				user.setName(c.getString(1));
				user.setEmail(c.getString(2));
				user.setPassword(c.getString(3));
				
				list.add(user);
			} while(c.moveToNext());
		}
		Log.d("lists: " , list.toString());
		
		return list;

	}
	
	/**
	 * get the User's Bank Account list
	 * @param userId
	 * @return
	 */
	public List<Integer> getBankListByUser(int userId) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		
		String qu = "SELECT * FROM " + FeedEntry.TABLE_NAME_Bank + " WHERE " +
				 FeedEntry.COLUMN_Bank_UserId + " = " + userId;
		Cursor c = db.rawQuery(qu, null);
		
		List<Integer> list = new ArrayList<Integer>();
		if(c.moveToFirst()) {
			do{
				list.add(c.getInt(1));
				
			} while(c.moveToNext());
		}
		
		return list;
						
	}
	
	public List<BankAccount> getBankList() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<BankAccount> list = new ArrayList<BankAccount>();
		String qu = "SELECT * FROM " + FeedEntry.TABLE_NAME_Bank;
		Cursor c = db.rawQuery(qu, null);
		BankAccount bank = null;
		if(c.moveToFirst()) {
			do{
				Log.d("bank Int(0):", String.valueOf(c.getInt(1)));
				Log.d("bank Int(1):", String.valueOf(c.getInt(2)));
				Log.d("bank Int(2):", c.getString(3));
				Log.d("bank Int(3):", c.getString(4));
				bank = new BankAccount(c.getInt(1), c.getInt(2), c.getString(3)
						, c.getString(4), "");
				list.add(bank);
			} while(c.moveToNext());
		}
		Log.d("bankList : ", list.toString());
		
		return list;
	}
	
	public double getBankBalance(int bankId) {
		SQLiteDatabase db = this.getReadableDatabase();
		double result = 0;
		String qu = "SELECT * FROM " + FeedEntry.TABLE_NAME_Bank + 
				" WHERE " + FeedEntry.COLUMN_Bank_Number + " = " + bankId;
		Cursor c = db.rawQuery(qu,null);
		if(c.moveToFirst()) {
			do{
				result = c.getDouble(3);
			} while(c.moveToNext());
		}
		return result;
	}
	
	/**
	 * check duplicate userName
	 * @return
	 */

	public boolean checkDuplicateName(String aName) { // check what the hell is this for
		User user1 = getUserInfo(aName);
		if (user1 == null) { 
			return false;
		}
		return user1.getName().equals(aName);
	}
	
	public boolean checkDuplicateUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("the data is null!");
		}
		Log.d("Collecting this user's info : ", user.toString());
		Log.d("the user's name : ", user.getName());
		User user1 = getUserInfo(user.getName());
		if (user.equals(user1)) {
			Log.d("checkDuplicateUser : ", "true");
			return true;
		}
		Log.d("checkDuplicateUser : ", "false");
		return false;
	}
	
	/**
	 * Check if there's duplicate Bank Account
	 * @param bankNum
	 * @return
	 */
	public boolean checkDuplicateBankAccount(int bankNum) {
		SQLiteDatabase db = this.getReadableDatabase();
		String qu = "SELECT * FROM " + FeedEntry.TABLE_NAME_Bank +
				" WHERE " + FeedEntry.COLUMN_Bank_Number + " = " + bankNum;
		Cursor c = db.rawQuery(qu, null);

		if(c.moveToFirst()) {
			Log.d("duplicate BankAccount: ", c.getString(0));
			return true;	// if it contains it
		}
		return false;
	}
	
	
	/*
	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		
	}
	*/
	
	


}
