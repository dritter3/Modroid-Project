package com.example.modroid_app.model;

import java.util.Date;

public class Transaction {

	private int date;
	private double balance;
	private double amount;
	private String comment;
	private int bankAccount;

	public Transaction() {
		this(0,0,null);
	}
	
	public Transaction(double balance) {	//but This shouldn't happen
		this(balance, 0, null);
	}
	public Transaction(double balance, int date) {
		this(balance, date, null);

	}
	
	public Transaction(double balance, int date, String comment) {
		this.date = date;
		this.balance = balance;
		amount = 0;
		this.comment = comment;
	}
	
	public void makeTrans(double amount) {
		this.amount = amount;
		balance = balance + amount;
	}
	
	public void setDate(int date){
		this.date = date;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	//This shouls be only used in getBankListByUser() method
	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getDate(){
		return date;
	}
	public double getBalance(){
		return balance;
	}
	public double getAmount() {
		return amount;
	}
	

	public String getComment() {
		return comment;
	}
	public boolean equals(Transaction other){
		if (other == null){
			return false;
		} 
		return true; // check if it has to be true
	}

}
