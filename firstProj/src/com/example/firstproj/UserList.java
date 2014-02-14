package com.example.firstproj;
import java.util.ArrayList;

public class UserList {

	private ArrayList<User> list;
	
	public UserList() {
		list = new ArrayList<User>();
		list.add(new AdminAccount());
	}
	
	public boolean verifyAccount(String name, String psw) {
		System.out.println("looking for user account");
		return list.contains(new UserAccount(name, psw));
	}
	
	private boolean findUserName(String name){
		for(User u: list) {
			if (u.getName().equals(name)){
				return true;
			}
		}
		return false;
	}

}