package com.example.mnemosine_allena_la_memoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Attenzione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attenzione);
        TextView diff = findViewById(R.id.diffAt);
        Difficoltà d = MainActivity.getDiff();
        diff.setText(String.valueOf(d).toLowerCase());
    }


    public void tasto(View v) {

        Intent intent = new Intent(Attenzione.this, Intruso.class);
        startActivity(intent);
    }
}