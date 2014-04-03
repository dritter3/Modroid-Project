package com.example.modroid_app.model;

/**
 * 
 * @author Q
 *
 */
public final class Admin extends User {


	/**
	 * 
	 * @author Q
	 *
	 */
	public static class AdminAccount extends User{
		
		public static final User user = new AdminAccount();
		
		public AdminAccount() {
			super("admin","pass123", "");
		}

		public boolean resetAccount(String name) {
			
			return false;
		}
	}
	


}
