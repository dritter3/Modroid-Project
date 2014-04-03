package com.example.modroid_app.database;


import android.provider.BaseColumns;

public final class UserTableContract {

	public UserTableContract(){}
	
	public static abstract class FeedEntry implements BaseColumns {
		public static final String TABLE_NAME_User = "UserTable";	
		public static final String COLUMN_User_NAME = "UserName";
		public static final String COLUMN_User_MAIL = "UserEmail";
		public static final String COLUMN_User_PASSWORD = "UserPassword";

		public static final String[] COLUMNS_User = {
			COLUMN_User_NAME,
			COLUMN_User_PASSWORD,
			COLUMN_User_MAIL
		};
		
		
		
		public static final String TABLE_NAME_Bank = "BankAccountTable";
		public static final String COLUMN_Bank_Number = "BankAccountNumber";
		public static final String COLUMN_Bank_Verify = "BankVerificationCode";
		public static final String COLUMN_Bank_BillAddress = "BankBillAddress";
		public static final String COLUMN_Bank_holderName = "BankHolderName";
		public static final String COLUMN_Bank_Balance = "BankBalance";
		public static final String COLUMN_Bank_UserId = "BankUserId";
		public static final String[] COLUMNS_Bank = {
			COLUMN_Bank_Number,
			COLUMN_Bank_Verify,
			COLUMN_Bank_BillAddress,
			COLUMN_Bank_holderName,
			COLUMN_Bank_Balance,
			COLUMN_Bank_UserId
		};
		
		public static final String TABLE_NAME_Transaction = "TransactionTable";
		public static final String COLUMN_Trans_Date = "TransData";
		public static final String COLUMN_Trans_Bank_Account = "TransBankAccountNumber";
		public static final String COLUMN_Trans_Bank_Balance = "TransBalance";
		public static final String COLUMN_Trans_Amount = "TransAmount";
		public static final String COLUMN_Trans_Comment = "TransComment";
		public static final String[] COLUMNS_Transaction = {
			COLUMN_Trans_Bank_Account,
			COLUMN_Trans_Bank_Balance,
			COLUMN_Trans_Amount,
			COLUMN_Trans_Comment
		};
		
	}
}
