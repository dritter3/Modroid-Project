package com.example.modroid_app.model;

import java.util.Date;

public class Transaction {

	private String date;
	private double balance;
	private double amount;
	private String comment;

	public Transaction(double balance) {	//but This shouldn't happen
		this(balance, null, null);
	}
	public Transaction(double balance, String date) {
		this(balance, date, null);

	}
	
	public Transaction(double balance, String date, String comment) {
		this.date = date;
		this.balance = balance;
		amount = 0;
		this.comment = comment;
	}
	
	public void makeTrans(double amount) {
		this.amount = amount;
		balance = balance + amount;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate(){
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
