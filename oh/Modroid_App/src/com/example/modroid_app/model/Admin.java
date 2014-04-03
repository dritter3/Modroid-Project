package com.example.modroid_app.model;

/**
 * 
 * @author TEAM 45:
 * @version 1.0
 */
public final class Admin extends User {

    public static class AdminAccount extends User {

        public static final User USER = new AdminAccount();

        /**
         * This is the default constructor of the admin account.
         */
        public AdminAccount() {
            super("admin", "pass123", "");
        }

        public boolean resetAccount(String name) {

            return false;
        }
    }

}
