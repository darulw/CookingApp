package com.example.cookingapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.cookingapp.model.Recipe;

import java.util.UUID;

public class RecipeCursorWrapper extends CursorWrapper {

    public RecipeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //donwload information from database and set their values to current
    public Recipe getRecipe() {
        String UUIDString = getString(getColumnIndex("UUID"));
        String Title = getString(getColumnIndex("title"));
        String imgSrc = getString(getColumnIndex("imageSrc"));

        Recipe recipe = new Recipe(UUID.fromString(UUIDString));

        recipe.setTitle(Title);
        recipe.setImage(imgSrc);
        return recipe;
    }
}
