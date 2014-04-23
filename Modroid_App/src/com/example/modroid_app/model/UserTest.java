package com.example.modroid_app.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user1;
    private User user2;
    public UserTest() {
    }

    @Before
    public void setUp() throws Exception {
        user1 = new User();
        user2 = new User();
    }

    public void sameName() {
        user1.setName("name");
        user2.setName("name");
    }
    
    public void samePass() {
        user1.setUserPSW("password");
        user2.setUserPSW("password");
    }
    
    public void sameMail() {
        user1.setEmail("email");
        user2.setEmail("email");
    }
    
    public void diffName() {
        user1.setName("name1");
        user2.setName("name2");
    }
    
    public void diffPass() {
        user1.setUserPSW("pass1");
        user2.setUserPSW("pass2");
    }
    
    public void diffMail() {
        user1.setEmail("email1");
        user2.setEmail("email2");
    }
    
    
    @Test
    public void testEqual() {
        sameName();
        samePass();
        sameMail();
        assertTrue(user1.equals(user2));
    }

}
