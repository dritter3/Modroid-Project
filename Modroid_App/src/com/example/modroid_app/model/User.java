package com.example.modroid_app.model;
/**
 * This class represents a UserAccount structure.
 * 
 * @author Team 45
 * @version 1.0
 */
public class User {

    /** this the account name of the user account. */
    private String name;
    /** this the psw of the user account. */
    private String userPSW;
    /** this the associated email of the user account. */
    private String eMail;

    /**
     * The the default constructor for User. Sets everything to null.
     */
    public User() {
        this(null, null, null);
    }

    /**
     * The constructor for BankAccount takes in three ints. It sets up the
     * private instance data for later.
     * @param userName user name of the account
     * @param uPSW psw of the account
     * @param uEmail associated email of the user account
     */
    public User(final String userName, final String uPSW, final String uEmail) {
        name = userName;
        this.userPSW = uPSW;
        this.eMail = uEmail;
    }

    /**
     * This returns associated email of the account.
     *@return return associated email of the account
     */
    public String getEmail() {
        return eMail;
    }
    
    /**
     * This sets the associated email of the account.
     *@param uEmail the associated email of the account
     */
    public void setEmail(final String uEmail) {
        this.eMail = uEmail;
    }
    
    /**
     * This returns the account name of the account.
     *@return return account name of the account
     */
    public String getName() {
        return name;
    }
    
    /**
     * This sets the name of the account.
     *@param uName the name of the account
     */
    public void setName(final String uName) {
        this.name = uName;
    }
    
    /**
     * This returns the PSW of the account.
     *@return return PSW of the account
     */
    public String getUserPSW() {
        return userPSW;
    }
    
    /**
     * This sets the PSW of the account.
     *@param pass PSW of the account
     */
    public void setUserPSW(final String pass) {
        userPSW = pass;
    }
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof User && name.equals(((User) obj).getName()) && userPSW.equals(((User) obj).getUserPSW())) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String result = name + " " + userPSW + " " + eMail;
        return result;
    }

}
