package com.example.modroid_app;
import java.util.*;

public class UserList {

	private static ArrayList<User> list = new ArrayList<User>();
	private static AdminAccount admin = new AdminAccount();
	
	public UserList() {
		if(list.isEmpty()) {
			list.add(admin);
		}
	}
	
/*	public static boolean contains(String name, String psw) {
		return list.contains(new UserAccount(name, psw));
	}
	*/
	public static boolean verifyAccount(String name, String psw) {
		return list.contains(new UserAccount(name, psw));
	}
	
	public static boolean addNewUser(User toAdd) {
		if(toAdd == null || findUserName(toAdd.getName())) {
			return false;
		}
		return list.add(toAdd);
	}
	
	public static void printAll(){
		for(User u: list) {
			u.print();
		}
	}
	
	public static boolean findUserName(String name){
		for(User u: list) {
			if (u.getName().equals(name)){
				return true;
			}
		}
		return false;
	}

}
