package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cookingapp.database.RecipeCursorWrapper;
import com.example.cookingapp.database.recipeDB;

public class MainActivity extends AppCompatActivity {

    private Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        it = new Intent(MainActivity.this,RecipeListActivity.class);
    }

    public void sample_dinner(View view) {

        it.putExtra("key",1);
        startActivity(it);
    }

    public void myOwnRecipes(View view) {
        it.putExtra("key",2);
        startActivity(it);
    }

    public void temp(View view) {
        Toast.makeText(this, "Tymczasowo niedostępne", Toast.LENGTH_SHORT).show();
    }
}