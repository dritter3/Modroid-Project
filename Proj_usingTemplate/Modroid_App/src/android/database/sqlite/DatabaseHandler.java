package android.database.sqlite;
import com.example.modroid_app.AdminAccount;
import com.example.modroid_app.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.TableContract.FeedEntry;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper{
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = 
			"CREATE TABLE " + FeedEntry.TABLE_NAME + " ( " +
			FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_MAIL + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_PASSWORD + TEXT_TYPE +
					 " )";
	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "User.db";
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
		addUser(new AdminAccount());
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	// check if I have to delete this
	public void addUser(String name, String password, String emailAddress) {
		Log.d("addUser : ", name);
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME, name);
		values.put(FeedEntry.COLUMN_PASSWORD, password);
		values.put(FeedEntry.COLUMN_MAIL, emailAddress);

		
		db.insert(FeedEntry.TABLE_NAME,
				null,
				values);

		db.close();

		
	}
	
	public void addUser(User user) {
		Log.d("addUser2", user.toString());
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME, user.getName());
		values.put(FeedEntry.COLUMN_PASSWORD, user.getPSW());
		values.put(FeedEntry.COLUMN_MAIL, user.getEmail());
		
		db.insert(FeedEntry.TABLE_NAME,
				null,
				values);
		Log.d("getUserInfo/ " + user.getName() + " : ", user.toString());
		db.close();
		
	}
	
	public User getUserInfo(String userName) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor c = db.query(FeedEntry.TABLE_NAME, 
				FeedEntry.COLUMNS, 
				FeedEntry.COLUMN_NAME + " = ?",
				new String[] {userName},
				null,
				null, 
				null);
		User user = new User();
		if(c != null) {
			if(c.moveToFirst()){
				c.moveToFirst();
			user.setName(c.getString(0));
			Log.d("setting name", c.getString(0));
			user.setPassword(c.getString(1));
			user.setEmail(c.getString(2));
		}}
		if(user.getName() == null) {
			return null;
		}
		
		Log.d("getUserInfo/ " + userName + " : ", user.toString());
		//db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
		return user;
		
	}
	/*
	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		
	}
	*/
	
	


}
