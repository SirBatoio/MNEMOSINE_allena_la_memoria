package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Logica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logica);
        TextView diff = findViewById(R.id.diffLo);
        Difficoltà d = MainActivity.getDiff();
        diff.setText(String.valueOf(d).toLowerCase());
    }
}