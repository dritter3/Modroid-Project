package com.example.modroid_app;

public class AdminAccount extends User {

	public AdminAccount() {
		super("admin","pass123", null);
	}
	
	public boolean resetAccount(String name) {
		
		return false;
	}

}
