package com.example.cookingapp.viewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cookingapp.R;
import com.example.cookingapp.model.Recipe;
import com.example.cookingapp.model.RecipeSingleton;

import java.util.List;
import java.util.UUID;

public class RecipePagerActivity extends AppCompatActivity {

    /*------------------------------------------------*/
    private ViewPager2 mViewPager;
    private FragmentStateAdapter pagerAdapter;
    private List<Recipe> mRecipes;

    private static final String EXTRA_RECIPE_ID="com.example.android.cookingapp.recipe_id";
    /*------------------------------------------------*/

    /*------------------------------------------------*/
    public static Intent newIntent(Context packageContext, UUID recipeId){
        Intent it = new Intent(packageContext,RecipePagerActivity.class);
        it.putExtra(EXTRA_RECIPE_ID,recipeId);
        return it;
    }
    /*------------------------------------------------*/

    /*------------------------------------------------*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_pager);

        UUID recipeID = (UUID) getIntent().getSerializableExtra(EXTRA_RECIPE_ID);
        //-----------ViewPager2
        mViewPager = (ViewPager2) findViewById(R.id.view_pager);
        mRecipes = RecipeSingleton.get(this).getRecipeList();
        pagerAdapter = new slideAdapter(this);
        mViewPager.setAdapter(pagerAdapter);

        for(int i =0;i<mRecipes.size();i++){
            if(mRecipes.get(i).getID().equals(recipeID)){
                mViewPager.setCurrentItem(i,false);
                break;
            }
        }
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/

    //-----------ViewPager2
    private class slideAdapter extends FragmentStateAdapter {
        public slideAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Recipe recipe = mRecipes.get(position);
            return DetailOfRecipe.newInstance(recipe.getID());
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }
    }
    /*------------------------------------------------*/
}
