package com.example.modroid_app.model;

/**
 * This class represents a bank account structure.
 * 
 * @author Team 45
 * @version 1.0
 */
public class BankAccount {

	/** this the number of the account.*/
    private final int accountNumber;
    /** this the verification of the account.*/
    private final int verificationCode;
    /** this the billing address of the account.*/
    private final String billAddress;
    /** this the holder name of the account.*/
    private final String holderName;
    /** this the alias of the account.*/
    private final String alias;
    /** this the balance of the account.*/
    private double balance;

    /**
     * The constructor for BankAccount takes in five ints. It sets up the
     * private instance data for later.
     * @param accountNumber the account number of the account
     * @param verificationCode the verification code of the account
     * @param billAddress the billing address of the account
     * @param holderName the holder name of the account
     * @param alias the alias of the account
     */
    public BankAccount(final int accountNumber, final int verificationCode,
        final String billAddress, final String holderName, final String alias) {
        this.accountNumber = accountNumber;
        this.verificationCode = verificationCode;
        this.billAddress = billAddress;
        this.holderName = holderName;
        this.alias = ("".equals(alias) ? "NULL" : alias);
        balance = 0;
    }

    /**
    * 
    *@return the alias of the bank account
    */
    public String getAlias() {
        return alias;
    }

    /**
    *
    *@return the account number of the bank account
    */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
    *
    *@return the holder name of the bank account
    */
    public String getHolderName() {
        return holderName;
    }

    /**
    *
    *@return the verification code of the bank account
    */
    public int getVerificationCode() {
        return verificationCode;
    }

    /**
    *
    *@return the billing address of the bank account
    */
    public String getBillAddress() {
        return billAddress;
    }

    /**
    *
    *@param bal set the balance of this bank account
    */
    public void setBalance(final double bal) {
        balance = bal;
    }

    /**
    *
    *@return the balance of the bank account
    */
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.valueOf(accountNumber);
    }

}
