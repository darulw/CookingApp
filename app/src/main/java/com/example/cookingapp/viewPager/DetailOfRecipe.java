package com.example.cookingapp.viewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cookingapp.R;
import com.example.cookingapp.model.Recipe;
import com.example.cookingapp.model.RecipeSingleton;

import java.io.File;
import java.util.UUID;

public class DetailOfRecipe extends Fragment {

    private static final String ARG_RECIPE_ID ="recipe_id";
    private Recipe mRecipe;
    private TextView mTitle;
    private ImageView mRecipeImage;

    /*------------------------------------------------*/
    public static DetailOfRecipe newInstance(UUID recipeID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECIPE_ID, recipeID);
        DetailOfRecipe fragment = new DetailOfRecipe();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID recipeID = (UUID) getArguments().getSerializable(ARG_RECIPE_ID);
        mRecipe = RecipeSingleton.get(getActivity()).getRecipe(recipeID);

    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recipe_detailed,container,false);
        mTitle = (TextView) v.findViewById(R.id.opis);
        mRecipeImage=(ImageView)v.findViewById(R.id.image_detailed);


        mTitle.setText(mRecipe.getTitle());
        final String DCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        final String DIRECTORY = DCIM + "/myRecipes";
        File m = new File(DIRECTORY, mRecipe.getImage());
        Bitmap bitmap = BitmapFactory.decodeFile(m.getAbsolutePath());
        mRecipeImage.setImageBitmap(bitmap);

        return v;
    }
    /*------------------------------------------------*/
}
