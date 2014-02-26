package com.example.modroid_app;

import java.util.ArrayList;

public class UserAccount extends User {
	
	protected int userID;
	protected ArrayList<BankAccount> accounts;
	protected int size;
	
	public UserAccount(String userName, String userPSW){
		super(userName, userPSW);
		accounts = new ArrayList<BankAccount>();
		size = 0;
	}
	
	public boolean addAccount(int num, int code, String add, String n, String alias){
		if(isFull()||isExisted(num)) {
			return false;
		}
		BankAccount toAdd = new BankAccount(num,code,add,n,alias);
		accounts.add(toAdd);
		size++;
		return true;
	}
	
	private boolean isFull(){
		return size > 19;
	}
	private boolean isEmpty(){
		return accounts.isEmpty();
	}
	
	public ArrayList<BankAccount> getAccounts(){
		return accounts;
	}
	
	public boolean isExisted(int cardNumber){
		for(BankAccount ba: accounts){
			if(cardNumber == ba.getAccountNumber()){
				return true;
			}
		}
		return false;
	}

}
