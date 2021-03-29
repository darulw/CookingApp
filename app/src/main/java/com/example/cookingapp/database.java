package com.example.cookingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public database(Context context){
        super(context, "test.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table przepisy(" +
                "Recipename text primary key not null," +
                "opis text not null," +
                "image blob);");
    }
    public void KasowanieBazy(Context context){
        context.deleteDatabase("test.db");
    }
    public void dodajPrzepis(String recipename,String opis){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("Recipename",recipename);
        wartosci.put("opis",opis);
        db.insertOrThrow("przepisy",null,wartosci);
    }

    public Cursor wyswietlDB(){
        String[] kolumny ={"Recipename","opis"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor k = db.query("przepisy",kolumny,null,null,null,null,null);
        return k;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
