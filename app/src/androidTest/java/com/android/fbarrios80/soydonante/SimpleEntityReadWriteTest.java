package com.android.fbarrios80.soydonante;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.fbarrios80.soydonante.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by fbarrios80 on 09-11-17.
 */

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {

    private UserDao userDao;
    private LocalDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, LocalDatabase.class).build();
        userDao = db.userModel();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setDisplayName("Francisco Barrios");
        user.setLocalUid("1234ERTY");
        user.setProfileImage("");
        user.setBloodType("HR4+");
        user.setGender("MALE");
        user.setUserEmail("asdf@sdf.com");
        userDao.insert(user);

    }
}
