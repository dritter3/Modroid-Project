package com.example.modroid_app;

import java.util.Date;

public class Transaction {

	protected Date date;
	protected double amount;
	protected int tranID;
	protected String tranType;
	
	
	public Transaction(Date date, double amount, int tranID) {
		this.date = date;
		this.amount = amount;
		this.tranID = tranID;
		tranType = ((amount > 0) ? "DEP" : "WIT");
	}
	
	public void setAmount(double amount){
		this.amount = amount;
		tranType = ((amount > 0) ? "DEP" : "WIT");
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	public double getAmount(){
		return amount;
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
