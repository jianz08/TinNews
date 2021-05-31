package com.laioffer.tinnews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.laioffer.tinnews.model.Article;

import java.util.List;
//数据库操作
@Dao
public interface ArticleDao {
    @Insert
    void saveArticle(Article article);

    @Query("SELECT * FROM article")
    //LiveData: 只要db数据有变化，就会update
    LiveData<List<Article>> getAllArticles();

    @Delete
    void deleteArticle(Article article);

}
