package com.example.cookingapp.myNewRecipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cookingapp.R;
import com.example.cookingapp.model.Recipe;
import com.example.cookingapp.model.RecipeSingleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

public class NewRecipeActivity extends AppCompatActivity {

    /*------------------------------------------------*/

    private EditText ed;
    private ImageView mImageView;
    private Button mButton;
    private Button saveButton;
    private Recipe mRecipe;
    private String fileName;


    private static final int REQUEST_PHOTO = 1;
    final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    /*------------------------------------------------*/

    /*------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        mRecipe = new Recipe();
        ed = (EditText) findViewById(R.id.recipe_description);
        mButton = (Button) findViewById(R.id.take_photo);
        saveButton = (Button) findViewById(R.id.save);
        mImageView = (ImageView) findViewById(R.id.recipe_photo);

    }
    /*------------------------------------------------*/

    /*------------------------------------------------*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK) {
            //get image
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);

            //save image into file
            BitmapDrawable draw = (BitmapDrawable) mImageView.getDrawable();
            Bitmap bitmap = draw.getBitmap();

            //setting the appropriate path
            FileOutputStream outStream = null;
            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/myRecipes");
            dir.mkdir();
                fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(dir, fileName);
                try {
                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }




        }
    }


    /*------------------------------------------------*/


    /*------------------------------------------------*/
    public void zapis(View view) {

        if (ed.getText().toString().isEmpty() || mImageView.getDrawable() == null) {
            Toast.makeText(this, "Nie dodales zdjeca lub nazwy", Toast.LENGTH_SHORT).show();
        } else {
            Recipe recipe = new Recipe();
            recipe.setTitle(ed.getText().toString());
            recipe.setImage(fileName);
            RecipeSingleton.get(getApplicationContext()).addRecipe(recipe);
            finish();
        }
    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    public void takePhoto(View view) {
        //start implicit intent
        try {
            startActivityForResult(captureImage, REQUEST_PHOTO);
        } catch (ActivityNotFoundException e) {
        }
        mImageView = (ImageView) findViewById(R.id.recipe_photo);

    }
    /*------------------------------------------------*/


    /*------------------------------------------------*/
    public static Intent newIntentt(Context packageContext, UUID recipeId) {
        Intent it = new Intent(packageContext, NewRecipeActivity.class);
        return it;
    }
    /*------------------------------------------------*/
}