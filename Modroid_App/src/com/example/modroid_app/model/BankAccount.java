package com.example.modroid_app.model;

import java.util.ArrayList;

public class BankAccount {

	protected int accountNumber;
	protected int verificationCode;
	protected String billAddress;
	protected String holderName;
	protected String alias;
	protected double balance;

	public BankAccount(int accountNumber, int verificationCode, String billAddress, String holderName, String alias) {
		this.accountNumber = accountNumber;
		this.verificationCode = verificationCode;
		this.billAddress = billAddress;
		this.holderName = holderName;
		this.alias = (alias.equals("") ? "NULL" : alias);
		balance = 0;
	}
	
	public String getAlias(){
		return alias;
	}
	
	public int getAccountNumber(){
		return accountNumber;
	}

	public String getHolderName() {
		return holderName;
	}
	
	public int getVerificationCode() {
		return verificationCode;
	}
	public String getBillAddress() {
		return billAddress;
	}
	
	public void setBalance(double bal) {
		balance = bal;
	}
	public double getBalance() {
		return balance;
	}
	
	public String toString(){
		return String.valueOf(accountNumber);
	}
	
	
}
