package com.example.modroid_app.model;

/**
 * This class represents a transaction structure.
 * 
 * @author Team 45
 * @version 1.0
 */
public class Transaction {

    /** this the date of the transaction. */
    private int date;
    /** this the balance of the bank account where the transaction take place. */
    private double balance;
    /** this the amount of the transaction. */
    private double amount;
    /** this the comment/type of the transaction. */
    private String comment;
    /** this the bank account of where the transaction takes place. */
    private int bankAccount;

    /**
     * The the default constructor for transaction.
     */
    public Transaction() {
        this(0, 0, null);
    }
    
    /**
     * The constructor for BankAccount takes in one ints. It sets up the
     * private instance data for later.
     * @param newBalance the balance of the account where the transaction takes place
     */
    public Transaction(final double newBalance) { // but This shouldn't happen
        this(newBalance, 0, null);
    }

    /**
     * The constructor for BankAccount takes in two ints. It sets up the
     * private instance data for later.
     * @param newBalance the balance of the account where the transaction takes place
     * @param newDate when the transaction happens
     */
    public Transaction(final double newBalance, final int newDate) {
        this(newBalance, newDate, null);
    }

    /**
     * The constructor for BankAccount takes in three ints. It sets up the
     * private instance data for later.
     * @param newBalance the balance of the account where the transaction takes place
     * @param newDate when the transaction happens
     * @param newComment the type of the transaction
     */
    public Transaction(final double newBalance, final int newDate, final String newComment) {
        this.date = newDate;
        this.balance = newBalance;
        amount = 0;
        this.comment = newComment;
    }

    /**
    * This allow user to make the transaction the amount of the transaction.
    *@param newAmount the amount of the transaction
    */
    public void makeTrans(final double newAmount) {
        this.amount = newAmount;
        balance = balance + amount;
    }

    /**
     * This takes in the amount of the transaction.
     *@param tranDate the date of the tansaction
     */
    public void setDate(final int tranDate) {
        this.date = tranDate;
    }

    /**
     * This takes in the amount of the transaction.
     *@param newAmount the amount of the transaction
     */
    public void setAmount(final double newAmount) {
        this.amount = newAmount;
    }
    
    /**
     * This takes in the new balance of the bank account.
     *@param newBalance the balance of the bank account where
     * the transaction takes place
     */
    public void setBalance(final double newBalance) {
        this.balance = newBalance;
    }

    /**
     * This sets the  bank account of where the transaction takes place.
     * This should be only used in getBankListByUser() method
     *@param newBAccount the bank account where the transaction takes place
     */
    public void setBankAccount(final int newBAccount) {
        this.bankAccount = newBAccount;
    }
    
    /**
     * This takes in the comment of the transaction.
     *@param newComment the comment/type of the transaction
     */
    public void setComment(final String newComment) {
        this.comment = newComment;
    }
    
    /**
     * This returns date of the transaction.
     *@return return the date of the transaction
     */
    public int getDate() {
        return date;
    }

    /**
     * This returns balance of the transaction.
     *@return return the balance of the bankaccount where the transaction takes place
     */
    public double getBalance() {
        return balance;
    }

    /**
     * This returns amount of the transaction.
     *@return return the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This returns bank account of where the transaction takes place.
     *@return return the bank account of where the transaction takes place
     */
    public int getBankAccount() {
        return bankAccount;
    }

    /**
     * This returns comment of the transaction.
     *@return return the comment of the transaction
     */
    public String getComment() {
        return comment;
    }

    public String toString() {
        String result = "bankAccount : " + bankAccount + " balance : "
                + balance + " amount : " + amount + " date : " + date
                + " comment " + comment + "\n";
        return result;
    }

}
