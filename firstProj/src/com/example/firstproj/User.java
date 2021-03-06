package com.example.firstproj;

public abstract class User {

	protected String userName;
	protected String userPSW;
	protected int userID;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String userPSW){
		this.userName = userName;
		this.userPSW = userPSW;
	}
	
	public void setName(String userName){
		this.userName = userName;
	}
	
	public void setPSW(String userPSW){
		this.userPSW = userPSW;
		
	}
	
	public String getName(){
		return userName;
	}
	public String getPSW(){
		return userPSW;
	}

	public boolean equals(Object toCompare){
		if (toCompare == null) {
			return false;
		}
		if(toCompare instanceof User){
			if(((User)toCompare).getName().equals(userName)
					&& ((User)toCompare).getPSW().equals(userPSW)){
				return true;				
			}
		}
		return false;
	}

}
