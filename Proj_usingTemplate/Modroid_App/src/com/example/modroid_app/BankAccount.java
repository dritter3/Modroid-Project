package com.example.modroid_app;

public class BankAccount {

	protected int accountNumber;
	protected int verificationCode;
	protected String billAddress;
	protected String holderName;
	protected String alias;
	
	public BankAccount() {
		
	}
	
	public BankAccount(int accountNumber, int verificationCode, String billAddress, String holderName, String alias) {
		this.accountNumber = accountNumber;
		this.verificationCode = verificationCode;
		this.billAddress = billAddress;
		this.holderName = holderName;
		this.alias = (alias.equals("") ? "NULL" : alias);
	}
	
	public String getAlias(){
		return alias;
	}
	
	public int getAccountNumber(){
		return accountNumber;
	}

}
