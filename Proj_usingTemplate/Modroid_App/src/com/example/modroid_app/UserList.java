package com.example.modroid_app;
import java.util.*;

public class UserList {

	private static ArrayList<User> list;
	
	public UserList() {
		list = new ArrayList();
		list.add(new AdminAccount());
	}
	
	
	public boolean contains(String name, String psw) {
		return list.contains(new UserAccount(name, psw));
	}
	
	public boolean addNewUser(String name, String PSW) {
		return list.add(new UserAccount(name, PSW));
	}
	
	public void printAll(){
		for(User u: list) {
			u.print();
		}
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
