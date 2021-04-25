package com.example.cookingapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cookingapp.R;
import com.example.cookingapp.database.RecipeCursorWrapper;
import com.example.cookingapp.database.recipeDB;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipeSingleton {

    private static RecipeSingleton sRecipeSingleton;
    private Context mContext;
    private SQLiteDatabase db;
    private List<Recipe> mRecipes;

    private static int[] sampleImages = {
            R.drawable.star,
            R.drawable.cookies,
            R.drawable.hamb
    };
    private String[] sampleDescriptrion = {
            "To jest pierwszy opis",
            "To jest drugi opis",
            "To bedzie juz nasz ostatni opis"
    };

    /*------------------------------------------------*/
    public static RecipeSingleton get(Context context) {
        if (sRecipeSingleton == null) {
            sRecipeSingleton = new RecipeSingleton(context);
        }
        return sRecipeSingleton;
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    private RecipeSingleton(Context context) {
        mContext = context.getApplicationContext();
        db = new recipeDB(mContext).getWritableDatabase();

        //fill te sample recipes array
        mRecipes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle(sampleDescriptrion[i]);
            recipe.setSampleImage(sampleImages[i]);
            mRecipes.add(recipe);
        }
    }

    //get the list of sample recipes
    public List<Recipe> getSampleRecipes() {
        return mRecipes;
    }

    /*------------------------------------------------*/



    /*------------------------------------------------*/
    // in this method we process the data from cursor and fill the recipes list
    public List<Recipe> getRecipeList() {
        List<Recipe> recipes = new ArrayList<>();
        String name = "myRecipes";
        RecipeCursorWrapper cursor = k(null, null, name);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                recipes.add(cursor.getRecipe());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return recipes;
    }
    /*------------------------------------------------*/



    /*------------------------------------------------*/
    //this method download one element of our database and return them of course if method finds it
    public Recipe getRecipe(UUID id) {
        RecipeCursorWrapper cursor = k("UUID" + "=?",
                new String[]{id.toString()}, "myRecipes");
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRecipe();
        } finally {
            cursor.close();
        }

    }
    /*------------------------------------------------*/



    /*------------------------------------------------*/
    //in this method we save current recipes into ContentValues objects
    private static ContentValues getContentValues(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put("UUID", recipe.getID().toString());
        values.put("title", recipe.getTitle());
        values.put("imageSrc", recipe.getImage());
        return values;
    }

    public void addRecipe(Recipe r) {
        ContentValues values = getContentValues(r);
        db.insertOrThrow("myRecipes", null, values);
    }

    //cursor to download information from revelant tables
    private RecipeCursorWrapper k(String whereClause, String[] whereArgs, String tableName) {
        Cursor cursor = db.query(tableName, null, whereClause, whereArgs, null, null, null);
        return new RecipeCursorWrapper(cursor);
    }
    /*------------------------------------------------*/

}
