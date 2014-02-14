package com.example.modroid_app;
import java.util.*;

public class UserList {

	private ArrayList<User> list;
	
	public UserList() {
		list = new ArrayList();
		list.add(new AdminAccount());
	}
	
	public boolean contains(String name, String psw) {
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
