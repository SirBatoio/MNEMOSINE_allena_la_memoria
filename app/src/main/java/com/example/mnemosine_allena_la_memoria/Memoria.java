package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Memoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);
        TextView diff = findViewById(R.id.diff);
        Difficoltà d = MainActivity.getDiff();
        diff.setText(String.valueOf(d).toLowerCase());
    }
}