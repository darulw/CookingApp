package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class loadDatabase extends AppCompatActivity {


    private String mTyt;
    private String mOpis;
    private Cursor k;
    private String[] mTitle;
    private String[] mContent;
    private int i=0;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_database2);
        mButton = (Button) findViewById(R.id.AddRecipe);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.przepisy);
        mRecyclerView.setHasFixedSize(true);
        MainActivity mainActivity = new MainActivity();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Przepisy> przepisy = new ArrayList<>();
        for (int i = 0; i <mainActivity.getInkement(); ++i)
            przepisy.add(new Przepisy(i));

        mRecyclerView.setAdapter(new MyAdapter(this, przepisy, mRecyclerView));


    }

   public void newRecipe(View view) {
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent x = new Intent(loadDatabase.this, NewRecipe.class);
                    startActivity(x);
                }
            });

        }

}
