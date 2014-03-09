package com.example.modroid_app;

import java.util.Date;

public class Transaction {

	protected Date date;
	//protected double amount;
	protected double balance;
	protected int tranID;
	protected String tranType;
	
	public Transaction(double balance, int transID){
		this.balance = balance;
		this.tranID = transID;
	}
	public Transaction(double balance) {
		this.balance = balance;
	}
	public Transaction(Date date, double balance, int tranID) {
		this.date = date;
		this.balance = balance;
		this.tranID = tranID;
		//tranType = ((balance > 0) ? "DEP" : "WIT");
	}
	/*
	public void setAmount(double amount){
		this.amount = amount;
		tranType = ((amount > 0) ? "DEP" : "WIT");
	}*/
	
	public void makeTrans(double amount) {
		balance = balance + amount;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	public double getbalance(){
		return balance;
	}
	public int getID(){
		return tranID;
	}
	public String getType(){
		return tranType;
	}

	public boolean equals(Transaction other){
		if (other == null){
			return false;
		} else if(other.getID() == tranID){
			return true;
		}
		return false;
	}

}
