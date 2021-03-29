package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;
    private TextView mTextView2;
    private Button mTextView3;
    private static String[] tytul = new String[10];
    private static String[] opis = new String[10];
    private String test;
    private String[] temp = new String[10];

    private static int inkement = 0;
    private static int tempInk=0;
    public static int refreshCount=0;

    database db = new database(this);


    @Override
    public void onResume(){
        super.onResume();
       if(refreshCount==0) {
            refreshCount=1;
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.test);
        mTextView2 = (TextView) findViewById(R.id.test2);
        mTextView3 = (Button) findViewById(R.id.button2);

        //db.KasowanieBazy(this);

        // db.dodajPrzepis("kurak", "fajny");
        // db.dodajPrzepis("ziemniak", "fajnybardzo");

        inkement=0;
        mTextView.setText(tytul[1]);
        mTextView2.setText(String.valueOf(inkement));


    }


    public String getTytul(int i) {
        return this.tytul[i];
    }

    public String getOpis(int i) {
        return this.opis[i];
    }

    public int getInkement() {
        return inkement;
    }

    public void dziala(View view) {

        Cursor k;
        k = db.wyswietlDB();

        for(int i=0;i<1;i++) {
            if (tempInk == 0) {
                while (k.moveToNext()) {

                    tytul[inkement] = k.getString(0);
                    opis[inkement] = k.getString(1);
                    //temp[inkement]=opis[inkement];

                    inkement++;
                    if (k.isLast()) {
                        tempInk = 0;
                        //inkement=0;
                        i = 1;
                        refreshCount=0;
                    }
                }

            } else if (tempInk == 1) {

                inkement = 0;
                tempInk = 0;
                i = 0;
            }
        }
        Intent intent = new Intent(MainActivity.this, loadDatabase.class);



        startActivity(intent);

    }

   public void newxd(View view) {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
