package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewRecipe extends AppCompatActivity {

    private EditText mAddTyt;
    private EditText mAddOpis;
    private static String[] tytulAdd = new String[10];
    private static String[] opisAdd = new String[10];

    private Button mButton;
    MainActivity mMainActivity = new MainActivity();

    database db2 = new database(this);
    private static int tempValue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        mAddTyt = (EditText) findViewById(R.id.AddTyt);
        mAddOpis = (EditText) findViewById(R.id.editTextTextPersonName2);
        mButton = (Button) findViewById(R.id.Add);

        
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), mAddOpis.getText().toString(), Toast.LENGTH_LONG).show();
                String two = (mAddOpis.getText().toString());
                String one = (mAddTyt.getText().toString());

                db2.dodajPrzepis(one, two);

                Intent it = new Intent(NewRecipe.this,MainActivity.class);

                startActivity(it);




            }



        });


    }
    public int getTempValue(){
        return tempValue;
    }
}