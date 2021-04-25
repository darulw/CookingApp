package com.example.cookingapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class recipeDB extends SQLiteOpenHelper {

    //Creating database
    public recipeDB(Context context) {
        super(context, "Recipes", null, 1);
    }

    //Creating two tables one with my own recipes and second where recipes are added by me
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table sampleDinner(" +
                "id integer primary key autoincrement," +
                "UUID text," +
                "title text," +
                "imageSrc text," +
                "permission integer);"
        );
        sqLiteDatabase.execSQL("create table myRecipes(" +
                "id integer primary key autoincrement," +
                "UUID text," +
                "title text," +
                "imageSrc text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
