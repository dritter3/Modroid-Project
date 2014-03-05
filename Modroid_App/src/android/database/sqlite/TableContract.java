package android.database.sqlite;


import android.provider.BaseColumns;

public final class TableContract {

	public TableContract(){}
	
	public static abstract class FeedEntry implements BaseColumns {
		public static final String TABLE_NAME = "UserList";	
		public static final String COLUMN_NAME = "UserName";
		public static final String COLUMN_MAIL = "UserEmail";
		public static final String COLUMN_PASSWORD = "UserPassword";
		
		public static final String[] COLUMNS = {
			COLUMN_NAME,
			COLUMN_PASSWORD,
			COLUMN_MAIL
		};
	}
}
