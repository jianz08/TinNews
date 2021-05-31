package com.laioffer.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.laioffer.tinnews.database.TinNewsDatabase;


public class TinNewsApplication extends Application {
    private TinNewsDatabase database;
    @Override
    public void onCreate() {
        //app启动，如果不存在，就创建database
        super.onCreate();
        database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
        //Log.d("database", "database");
    }
    public TinNewsDatabase getDatabase() {
        return database;
    }
}
