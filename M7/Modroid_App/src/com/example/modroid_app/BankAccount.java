package com.example.modroid_app;

import java.util.ArrayList;

public class BankAccount {

	protected int accountNumber;
	protected int verificationCode;
	protected String billAddress;
	protected String holderName;
	protected String alias;
	protected ArrayList<Transaction> transactions;
	protected double balance;

	public BankAccount(int accountNumber, int verificationCode, String billAddress, String holderName, String alias) {
		this.accountNumber = accountNumber;
		this.verificationCode = verificationCode;
		this.billAddress = billAddress;
		this.holderName = holderName;
		this.alias = (alias.equals("") ? "NULL" : alias);
		transactions = new ArrayList<Transaction>();
		balance = 0;
	}
	
	public String getAlias(){
		return alias;
	}
	
	public Transaction getLastTransaction() {
		return transactions.get(transactions.size()-1);
	}
	
	public boolean isEmpty() {
		return transactions.isEmpty();
	}
	
	public int getAccountNumber(){
		return accountNumber;
	}
	
	public void addTransaction(Transaction t){
		transactions.add(t);
		balance += t.getbalance();
	}
	
	public void deleteTransaction(Transaction t){
		transactions.remove(t);
		balance -= t.getbalance();
	}
	
	public Transaction getTransaction(Transaction t){
		return ( transactions.contains(t) ? transactions.get(transactions.indexOf(t)): null);
	}
	
	public String toString(){
		return Integer.toString(accountNumber);
	}
	
	
}
