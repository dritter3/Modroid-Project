package com.example.modroid_app;

public final class Admin extends User {


	
	public static class AdminAccount extends User{
		
		protected static final User user = new AdminAccount();
		
		public AdminAccount() {
			super("admin","pass123", "");
		}

		public boolean resetAccount(String name) {
			
			return false;
		}
	}
	


}
