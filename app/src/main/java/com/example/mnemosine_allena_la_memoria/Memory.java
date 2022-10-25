package com.example.mnemosine_allena_la_memoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Memory extends AppCompatActivity {

    private boolean volume=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
    }

    public void cambiaVolume(@NonNull View v)
    {
        Button b = findViewById(v.getId());
        if(volume)
        {
            volume=false;
            b.setText("VOLUME OFF");
            b.setBackgroundColor(0xFF9E9E9E);
        }
        else
        {
            volume=true;
            b.setText("VOLUME ON");
            b.setBackgroundColor(0xFFFF9800);
        }
    }

    public void indietro(View v)
    {
        Intent intent = new Intent(Memory.this,SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    public void home(View v)
    {
        Intent intent = new Intent(Memory.this,Home.class);
        startActivity(intent);
        finish();
    }
}