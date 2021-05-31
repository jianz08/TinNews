package com.laioffer.tinnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.laioffer.tinnews.model.Article;

//映射 configuration
//version 数据库版本
// app的更新，数据库结构有可能也会更新
//backward/forward compatibility; database version migration
//The following code defines an AppDatabase class to hold the database.
//AppDatabase defines the database configuration and serves as the app's main access point to the persisted data.
//exportSchema option is for dumping a database schema to file system. We do not need that.
@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase  extends RoomDatabase {
    public abstract ArticleDao articleDao();
}