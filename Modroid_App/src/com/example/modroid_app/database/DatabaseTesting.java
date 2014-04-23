package com.example.modroid_app.database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;

public class DatabaseTesting extends Activity {
    
    private DatabaseHandler db;

    public DatabaseTesting() {
    }

    @Before
    public void setUp() throws Exception {
        //Context ct = new Context();
        db = new DatabaseHandler(this);
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
