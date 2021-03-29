package com.example.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.Random;

public class Przepisy {
    private String mTitle;
    private String mContent;
    private int mImage;

    MainActivity mMainActivity = new MainActivity();
    loadDatabase mLoadDatabase = new loadDatabase();



    private String[] sTitles= new String[10];
    private static String[] sContent= new String[10];
    private static int[] sImage={
            R.drawable.platki,
            R.drawable.ziemniak,
            R.drawable.platki,
            R.drawable.platki,
            R.drawable.ziemniak,
            R.drawable.ziemniak,
            R.drawable.platki,
            R.drawable.ziemniak,
            R.drawable.platki,
            R.drawable.ziemniak,
    };

    public Przepisy(int i){


            sTitles[i] = mMainActivity.getTytul(i);
            sContent[i] = mMainActivity.getOpis(i);


      mTitle =sTitles[i];
      mContent = sContent[i];
      mImage = sImage[i];

    }

    public String getTitle(){
        return  mTitle;
    }
    public String getContent(){
        return mContent;
    }
    public int getImage(){
        return mImage;
    }
}
