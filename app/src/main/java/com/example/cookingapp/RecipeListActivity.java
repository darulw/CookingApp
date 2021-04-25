package com.example.cookingapp;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.cookingapp.myNewRecipe.MyOwnRecipesFragment;
import com.example.cookingapp.sampleRecipes.RecipeListFragment;

public class RecipeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Intent it = getIntent();
        int value = it.getIntExtra("key",0);

        if(value==1){
        return new RecipeListFragment();
        }
        else
            return new MyOwnRecipesFragment();
    }
}
