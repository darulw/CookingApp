package com.example.cookingapp.myNewRecipe;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.R;
import com.example.cookingapp.model.Recipe;
import com.example.cookingapp.viewPager.RecipePagerActivity;
import com.example.cookingapp.model.RecipeSingleton;

import java.io.File;
import java.util.List;

public class MyOwnRecipesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private static final int REQUESTCODE = 1;


    /*------------------------------------------------*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get permissions
        if (Recipe.getPermission() == 0) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTCODE);
            Recipe.setPermission(1);
        }
        setHasOptionsMenu(true);
    }
    /*------------------------------------------------*/

    /*------------------------------------------------*/
    //Update UI
    @Override
    public void onStart() {
        super.onStart();
        UpdateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateUI();
    }

    private void UpdateUI() {
        RecipeSingleton recipeSingleton = RecipeSingleton.get(getActivity());
        List<Recipe> recipes = recipeSingleton.getRecipeList();

        if (mAdapter == null) {
            mAdapter = new RecipeAdapter(recipes);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setRecipes(recipes);
            mAdapter.notifyDataSetChanged();
        }
    }


    /*------------------------------------------------*/


    /*------------------------------------------------*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recipe_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return view;
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    //add options to action bar
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_recipe_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.newRecipe:
                Recipe recipe = new Recipe();
                Intent intent = NewRecipeActivity.newIntentt(getActivity(), recipe.getID());

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    //------------RECYCLER VIEW VIEW HOLDER
    private class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView mImageView;
        private Recipe mRecipe;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.shortDesc);
            mImageView = (ImageView) itemView.findViewById(R.id.imageOFRecipe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = RecipePagerActivity.newIntent(getActivity(), mRecipe.getID());
                    startActivity(intent);
                }
            });
        }

        public void bind(Recipe recipe) {
            mRecipe = recipe;
            mTitle.setText(recipe.getTitle());

            //download image and set to current imageview
            final String DCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
            final String DIRECTORY = DCIM + "/myRecipes";
            File m = new File(DIRECTORY, recipe.getImage());
            Bitmap bitmap = BitmapFactory.decodeFile(m.getAbsolutePath());
            mImageView.setImageBitmap(bitmap);


        }
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    //------------RECYCLER VIEW ADAPTER
    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {
        private List<Recipe> mRecipes;

        public RecipeAdapter(List<Recipe> recipes) {

            mRecipes = recipes;
        }


        @NonNull
        @Override
        public MyOwnRecipesFragment.RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_item_look, parent, false);
            return new MyOwnRecipesFragment.RecipeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyOwnRecipesFragment.RecipeHolder holder, int position) {
            Recipe recipe = mRecipes.get(position);
            holder.bind(recipe);
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }

        public void setRecipes(List<Recipe> recipes) {
            mRecipes = recipes;
        }
    }
    /*------------------------------------------------*/
}
