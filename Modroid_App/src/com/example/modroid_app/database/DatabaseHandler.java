package com.example.modroid_app.database;

import java.util.ArrayList;
import java.util.List;

import com.example.modroid_app.database.UserTableContract.FeedEntry;
import com.example.modroid_app.model.BankAccount;
import com.example.modroid_app.model.Transaction;
import com.example.modroid_app.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Q
 *
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * TextType.
     */
    private static final String TEXT_TYPE = " TEXT";
    /**
     * Comma String.
     */
    private static final String COMMA_SEP = ",";
    
    /**
     * Equals String.
     */
    private static final String EQUAL = " = ";
    
    /**
     * Where String.
     */
    private static final String WHERE = " WHERE ";
    
    /**
     * Select String.
     */
    private static final String SELECT = "SELECT * FROM ";
    
    /**
     * Integer String.
     */
    private static final String INTEGER = " INTEGER";
    
    /**
     * String to create User table.
     */
    private static final String CREATE_USER =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_User + " ("
                + FeedEntry._ID + " INTEGER PRIMARY KEY,"
                + FeedEntry.COLUMN_User_NAME
                    + TEXT_TYPE + COMMA_SEP
                    + FeedEntry.COLUMN_User_PASSWORD
                    + TEXT_TYPE + COMMA_SEP
                    + FeedEntry.COLUMN_User_MAIL + TEXT_TYPE
                    + " )";

    /**
     * String to create Bank table.
     */
    private static final String CREATE_BANK =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_Bank + " ("
            + FeedEntry._ID + " INTEGER PRIMARY KEY,"
                    + FeedEntry.COLUMN_Bank_Number
                    + INTEGER + COMMA_SEP
                    + FeedEntry.COLUMN_Bank_Verify
                    + TEXT_TYPE + COMMA_SEP
                    + FeedEntry.COLUMN_Bank_BillAddress
                    + TEXT_TYPE + COMMA_SEP
                    + FeedEntry.COLUMN_Bank_holderName
                    + TEXT_TYPE + COMMA_SEP
                    + FeedEntry.COLUMN_Bank_Balance
                    + INTEGER + COMMA_SEP
                    + FeedEntry.COLUMN_Bank_UserId
                    + INTEGER
                    + " )";
    /**
     * String to create Transaction table.
     */
    public static final String CREATE_TRANS =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_Transaction
            + " (" + FeedEntry._ID + " INTEGER PRIMARY KEY, "
                    + FeedEntry.COLUMN_Trans_Date
                    + TEXT_TYPE + COMMA_SEP
                    + FeedEntry.COLUMN_Trans_Bank_Account
                    + INTEGER + COMMA_SEP
                    + FeedEntry.COLUMN_Trans_Bank_Balance
                    + INTEGER + COMMA_SEP
                    + FeedEntry.COLUMN_Trans_Amount
                    + INTEGER + COMMA_SEP
                    + FeedEntry.COLUMN_Trans_Comment
                    + TEXT_TYPE + " )";
                    // check if date is Integer or String

    /** Delete User table SQL String.
     */
    private static final String DELETE_USER =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_User;
    /** Delete Bank table SQL String.
     */
    private static final String DELETE_BANK =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_Bank;
    /** Delete Transaction SQL string.
     */
    private static final String DELETE_TRANS =
            "DROP TABLE IF EXISTS "
            + FeedEntry.TABLE_NAME_Transaction;
    /** The database version.
     */
    public static final int DATABASE_VERSION = 1;

    /** The name of database name.
     */
    public static final String DATABASE_NAME = "Users.database";

    /** The constructor of DatabaseHandler.
     * @param context parameter for super constructor call
     */
    public DatabaseHandler(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * onCreate method for Database Handler.
     * @param database The SQLiteDatabase instance
     */
    public final void onCreate(final SQLiteDatabase database) {
        database.execSQL(CREATE_USER);
        database.execSQL(CREATE_BANK);
        database.execSQL(CREATE_TRANS);
    }

    /**
     * onUpgrade method for Database Handler.
     * @param database The SQLiteDatabase instance
     * @param oldVersion The old version
     * @param newVersion The new version
     */
    public final void onUpgrade(final SQLiteDatabase database,
            final int oldVersion, final int newVersion) {
        database.execSQL(DELETE_USER);
        database.execSQL(DELETE_BANK);
        database.execSQL(DELETE_TRANS);
        onCreate(database);
    }

    /**
     * onDowngrade method for Database Handler.
     * @param database The SQLiteDatabase instance
     * @param oldVersion of the database
     * @param newVersion of the database
     */
    public final void onDowngrade(final SQLiteDatabase database,
            final int oldVersion, final int newVersion) {
        onUpgrade(database, oldVersion, newVersion);
    }

    // check if I have to delete this
    /**
     * Add a user to database.
     * @param name String name of the user
     * @param password String password of the user
     * @param emailAddress String email of the user
     */
    public final void addUser(final String name,
            final String password, final String emailAddress) {
        Log.d("addUser : ", name);

        final SQLiteDatabase database = this.getWritableDatabase();
        Log.d("pass", password);
        final ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_User_NAME, name);
        values.put(FeedEntry.COLUMN_User_PASSWORD, password);
        values.put(FeedEntry.COLUMN_User_MAIL, emailAddress);

        database.insert(FeedEntry.TABLE_NAME_User,
                null,
                values);

        database.close();
    }

    /**
     * Add BankAccount to database.
     * @param acc BankAccount instance
     * @param user The User for the BankAccount
     * @return return the row id of the User
     */
    public final long addBank(final BankAccount acc, final User user) {

        final int userId = getUserIdRow(user);
        return addBank(acc, userId);
    }

    /**
     * Add BankAccount to database with user's id.
     * @param acc BankAccount instance
     * @param userId The User id for the BankAccount
     * @return return the row id of the added BankAccout
     */
    public final long addBank(final BankAccount acc, final int userId) {

        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_Bank_Number,
                acc.getAccountNumber());
        values.put(FeedEntry.COLUMN_Bank_Verify,
                acc.getVerificationCode());
        values.put(FeedEntry.COLUMN_Bank_BillAddress,
                acc.getBillAddress());
        values.put(FeedEntry.COLUMN_Bank_holderName,
                acc.getHolderName());
        values.put(FeedEntry.COLUMN_Bank_Balance, acc.getBalance());
        values.put(FeedEntry.COLUMN_Bank_UserId, userId);
        final long rowId = database.insert(FeedEntry.TABLE_NAME_Bank, null, values);

        return rowId;
    }

    /**
     * make transaction and save it into database.
     * @param trans The Transaction instance contains transaction info
     * @param bankId The bankId for the transaction
     * @return return the row id of added transaction
     */
    public final long addTransaction(final Transaction trans,
            final int bankId) {
        final SQLiteDatabase database = this.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_Trans_Date, trans.getDate());
        values.put(FeedEntry.COLUMN_Trans_Bank_Account, bankId);
        values.put(FeedEntry.COLUMN_Trans_Bank_Balance,
                trans.getBalance());
        values.put(FeedEntry.COLUMN_Trans_Amount, trans.getAmount());
        values.put(FeedEntry.COLUMN_Trans_Comment, trans.getComment());

        final long rowId = database.insert(FeedEntry.TABLE_NAME_Transaction,
                null, values);
        updateBankBalance(trans.getBalance(), bankId);
        return rowId;
    }

    /**
     * Method to get all the withdrawal of a bank.
     * @param bankId The bankId that's going to withdrawal
     * @return Return the list of withdrawal of the bank
     */
    public final List<Transaction> getWithdrawals(final int bankId) {
        final SQLiteDatabase database = this.getReadableDatabase();


        final String query = SELECT
                + FeedEntry.TABLE_NAME_Transaction + WHERE
                 + FeedEntry.COLUMN_Trans_Bank_Account + EQUAL
                + bankId;
        final Cursor cur = database.rawQuery(query, null);


        final List<Transaction> list = new ArrayList<Transaction>();
        Transaction trans = new Transaction();
        if (cur.moveToFirst()) {
            do {
                if (cur.getDouble(2 + 2) < 0) {
                    trans.setBalance(cur.getDouble(2 + 1));
                    trans.setDate(cur.getInt(1));
                    trans.setComment(cur.getString(2 + 2 + 1));
                    trans.setBankAccount(cur.getInt(2));
                    trans.setAmount(cur.getInt(2 + 2));

                    list.add(trans);
                    Log.d("getTransactionList",
                        String.valueOf(cur.getDouble(1)));
                }
            } while(cur.moveToNext());
        }

        return list;
    }

    /**
     * update Bank Account's balance when transaction is made.
     * @param bal The new balance of the bank
     * @param bankId The bankId that's going to be updated
     */
    public final void updateBankBalance(final double bal,
            final int bankId) {
        final SQLiteDatabase database = this.getReadableDatabase();

        final ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_Bank_Balance, bal);
        final String query = "UPDATE " + FeedEntry.TABLE_NAME_Bank
                + " SET " + FeedEntry.COLUMN_Bank_Balance
                + EQUAL + bal
                + WHERE
                + FeedEntry.COLUMN_Bank_Number + EQUAL + bankId;

        Log.d("query", query);
        database.execSQL(query);
        Log.d("workded this far", "4");
    }
    /**
     * Get a user's row id.
     * @param user the user going to search
     * @return the user's row id
     */
    public final int getUserIdRow(final User user) {
        if (user == null) {
            throw new
            IllegalArgumentException("User input is null!");
        }
        final SQLiteDatabase database = this.getReadableDatabase();
        int key = 0;
        final Cursor cur = database.query(FeedEntry.TABLE_NAME_User,
                null,
                FeedEntry.COLUMN_User_NAME + " = ?",
                new String[] {user.getName()},
                null,
                null,
                null);
        // check if it's null or Primary Key
        if (cur != null && cur.moveToFirst()) {
            cur.moveToFirst();
            key = cur.getInt(0);
        }
        return key;

    }

    /**
     * add user to the table.
     * @param user the user that's going to be saved
     */
    public final void addUser(final User user) {
        Log.d("addUser2", user.toString());
        final SQLiteDatabase database = this.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_User_NAME, user.getName());
        values.put(FeedEntry.COLUMN_User_PASSWORD, user.getPSW());
        values.put(FeedEntry.COLUMN_User_MAIL, user.getEmail());

        database.insert(FeedEntry.TABLE_NAME_User,
                null,
                values);
        Log.d("getUserInfo/ " + user.getName()
                + " : ", user.toString());
        database.close();

    }

    /**
     * Get user's info from table.
     * @param userName the user's name that's going to search
     * @return the User instance from database
     */
    public final User getUserInfo(final String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("the data is null!");
        }
        final SQLiteDatabase database = this.getReadableDatabase();

        final Cursor cur = database.query(FeedEntry.TABLE_NAME_User,
                FeedEntry.COLUMNS_User,
                FeedEntry.COLUMN_User_NAME + " = ?",
                new String[] {userName},
                null,
                null,
                null);
        final User user = new User();

        if (cur != null && cur.moveToFirst()) {
                //cur.moveToFirst();
                do {
                    user.setName(cur.getString(0));
                    user.setPassword(cur.getString(1));
                    user.setEmail(cur.getString(2));

                } while (cur.moveToNext());
                
            }

        if (user.getName() == null) {
            return null;
        }
        Log.d("getting user's info: ", user.toString());

        return user;

    }

    /**
     * The all the user's list from the table.
     * @return the user's list
     */
    public final List<User> getUserList() {
        final List<User> list = new ArrayList<User>();

        final String query = SELECT + FeedEntry.TABLE_NAME_User;

        final SQLiteDatabase database = this.getReadableDatabase();
        final Cursor cur = database.rawQuery(query, null);

        User user = null;
        if (cur.moveToFirst()) {
            do {
                user = new User();
                user.setName(cur.getString(1));
                user.setPassword(cur.getString(2));
                user.setEmail(cur.getString(2 + 1));
                list.add(user);
            } while(cur.moveToNext());
        }
        Log.d("lists: " , list.toString());

        return list;

    }

    /**
     * get the User's Bank Account list.
     * @param userId the user's id for the bank list
     * @return the Banklist
     */
    public final List<Integer> getBankListByUser(final int userId) {
        final SQLiteDatabase database = this.getReadableDatabase();

        final String query = SELECT
        + FeedEntry.TABLE_NAME_Bank + WHERE
        + FeedEntry.COLUMN_Bank_UserId + EQUAL + userId;
        final Cursor cur = (Cursor) database.rawQuery(query, null);

        final List<Integer> list = new ArrayList<Integer>();
        if (cur.moveToFirst()) {
            do {
                list.add(cur.getInt(1));

            } while(cur.moveToNext());
        }
        return list;
    }

    /**
     * Get the all the transaction in given period of time.
     * @param startDate startDate
     * @param endDate endDate
     * @return The list of all the Transaction
     */
    public final List<Transaction> getTransactionsByDate(
            final int startDate, final int endDate) {
        final SQLiteDatabase database = this.getReadableDatabase();

        final String query = SELECT + FeedEntry.TABLE_NAME_Transaction;
        final Cursor cur = database.rawQuery(query, null);

        final List<Transaction> list = new ArrayList<Transaction>();
        final Transaction newTrans = new Transaction();
        if (cur.moveToFirst()) {
            do {
                if (cur.getInt(1) >= startDate && cur.getInt(1) <= endDate) {
                    newTrans.setDate(cur.getInt(1));
                    newTrans.setBankAccount(cur.getInt(2));
                    newTrans.setBalance(cur.getDouble(2 + 1));
                    newTrans.setAmount(cur.getDouble(2 + 2));
                    list.add(newTrans);
                    Log.d("getTransactionList",
                        String.valueOf(cur.getDouble(1)));
                }
            } while(cur.moveToNext());
        }

        return list;
    }


    /**
     * Return all the bankList from the table.
     * @return the bankList
     */
    public final List<BankAccount> getBankList() {
        final SQLiteDatabase database = this.getReadableDatabase();
        final List<BankAccount> list = new ArrayList<BankAccount>();
        final String query = SELECT + FeedEntry.TABLE_NAME_Bank;
        final Cursor cur = database.rawQuery(query, null);
        BankAccount bank = null;
        if (cur.moveToFirst()) {
            do {
                Log.d("bank Int(0):",
                        String.valueOf(cur.getInt(1)));
                Log.d("bank Int(1):",
                        String.valueOf(cur.getInt(2)));
                Log.d("bank Int(2):",
                        cur.getString(2 + 1));
                Log.d("bank Int(3):", cur.getString(2 + 2));
                bank = new BankAccount(cur.getInt(1),
                        cur.getInt(2), cur.getString(2 + 1)
                        ,cur.getString(2 + 2), "");
                list.add(bank);
            } while(cur.moveToNext());
        }
        Log.d("bankList : ", list.toString());

        return list;
    }

    /**
     * Get the given bank's balance.
     * @param bankId The bank's row Id
     * @return the balance of the bank
     */
    public final double getBankBalance(final int bankId) {
        final SQLiteDatabase database = this.getReadableDatabase();
        double result = 0;
        final String query = SELECT
        + FeedEntry.TABLE_NAME_Bank
                + WHERE + FeedEntry.COLUMN_Bank_Number
                + EQUAL + bankId;
        final Cursor cur = database.rawQuery(query, null);
        if (cur.moveToFirst()) {
            do {
                result = cur.getDouble(2 + 2 + 1);
            } while(cur.moveToNext());
        }
        return result;
    }

    /**
     * check duplicate userName by String.
     * @param aName the String of the user's name
     * @return true if there's duplicate userName
     */
    public final boolean checkDuplicateName(final String aName) {
        final User user1 = getUserInfo(aName);
        boolean done = false;
        if (user1 == null) {
            done = false;
        } else {
            done = user1.getName().equals(aName);
        }
        return done;
    }

    /**
     * check the duplicate user my User's instance.
     * @param user the User instance that's going to be compare
     * @return true if there's duplicate User
     */
    public final boolean checkDuplicateUser(final User user) {
        if (user == null) {
            throw new IllegalArgumentException("the data is null!");
        }
        Log.d("Collecting this user's info : ", user.toString());
        Log.d("the user's name : ", user.getName());
        final User user1 = getUserInfo(user.getName());
        boolean done = false;
        if (user.equals(user1)) {
            Log.d("checkDuplicateUser : ", "true");
            done = true;
        }
        Log.d("checkDuplicateUser : ", "false");
        return done;
    }

    /**
     * Check if there's duplicate Bank Account.
     * @param bankNum The bank number that's going to compare
     * @return true if there's duplicate bankAccount number
     */
    public final boolean checkDuplicateBankAccount(
            final int bankNum) {
        final SQLiteDatabase database = this.getReadableDatabase();
        final String query = SELECT + FeedEntry.TABLE_NAME_Bank
                + WHERE + FeedEntry.COLUMN_Bank_Number
                + EQUAL + bankNum;
        final Cursor cur = database.rawQuery(query, null);
        boolean done = false;
        if (cur.moveToFirst()) {
            done = true;
        }
        return done;
    }

    /**
     * Remove all the entries in the table.
     */
    public final void removeAll() {
        final SQLiteDatabase database = this.getWritableDatabase();
        database.delete(FeedEntry.TABLE_NAME_User, null, null);
        database.delete(FeedEntry.TABLE_NAME_Bank, null, null);
        database.delete(FeedEntry.TABLE_NAME_Transaction, null, null);
    }

}
