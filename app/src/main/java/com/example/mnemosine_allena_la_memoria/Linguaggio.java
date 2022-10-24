package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Linguaggio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguaggio);
        TextView diff = findViewById(R.id.diffLi);
        Difficoltà d = MainActivity.getDiff();
        diff.setText(String.valueOf(d).toLowerCase());
    }
}