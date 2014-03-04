package com.example.modroid_app;

public class User {

	private String userName;
	private String userPSW;
	private String eMail;
	
	public User() {
		this(null, null, null);
	}
	
	public User(String userName, String userPSW, String eMail){
		this.userName = userName;
		this.userPSW = userPSW;
		this.eMail = eMail;
	}
	
	public String getEmail() {
		return eMail;
	}
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	public String getName(){
		return userName;
	}
	public void setName(String name) {
		userName = name;
	}
	public String getPSW(){
		return userPSW;
	}
	public void setPassword(String pass) {
		userPSW = pass;
	}
// fix this part again too
	public boolean equals(Object o){
		if(o == null)
			return false;
		if(this == o) return true;
		
		if(o instanceof User){
			if(((User)o).getName().equals(userName)
					&& ((User)o).getPSW().equals(userPSW)
			&& ((User)o).getEmail().equals(eMail)){
				return true;				
			}
		}
		return false;
	}
	
	
	public void print(){
		System.out.println(userName+ "   " + userPSW);
	}
	
	public String toString() {
		String result = "Username : " + userName + " / Password : " +
	userPSW + " / Email : " + eMail;
		return result;
	}

}
