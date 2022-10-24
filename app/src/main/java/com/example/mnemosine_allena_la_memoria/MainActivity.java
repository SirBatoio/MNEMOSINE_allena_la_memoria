package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static Difficoltà diff= Difficoltà.FACILE;
    private static Campo campo;
    private TextView testo;
    private ArrayList<Button> pulsanti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testo = findViewById(R.id.difficolta);
        testo.setText("FACILE");

        pulsanti.add(findViewById(R.id.facile));
        pulsanti.add(findViewById(R.id.intermedio));
        pulsanti.add(findViewById(R.id.avanzato));

        pulsanti.add(findViewById(R.id.orientamento));
        pulsanti.add(findViewById(R.id.attenzione));
        pulsanti.add(findViewById(R.id.memoria));
        pulsanti.add(findViewById(R.id.logica));
        pulsanti.add(findViewById(R.id.linguaggio));

        setCliccabilitàPulsanti(false);
    }

    public void impostaDifficolta(View v)
    {
        switch(v.getId())
        {
            case R.id.facile:
                diff = Difficoltà.FACILE;
                break;
            case R.id.intermedio:
                diff = Difficoltà.INTERMEDIO;
                break;
            case R.id.avanzato:
                diff = Difficoltà.AVANZATO;
                break;
        }
        testo.setText(String.valueOf(diff));
    }

    public void redirect(View v)
    {
        Intent intent;
        switch(v.getId())
        {
            case R.id.orientamento:
                campo = Campo.ORIENTAMENTO;
                break;
            case R.id.attenzione:
                campo = Campo.ATTENZIONE;
                break;
            case R.id.memoria:
                campo = Campo.MEMORIA;
                break;
            case R.id.linguaggio:
                campo = Campo.LINGUAGGIO;
                break;
            case R.id.logica:
                campo = Campo.LOGICA;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        intent = new Intent(MainActivity.this, SelectEsercizi.class);
        startActivity(intent);
    }

    public void setCliccabilitàPulsanti(boolean b)
    {
        for (Button pulsante: pulsanti) {
            pulsante.setClickable(b);
        }

        TextView txt = findViewById(R.id.textView6);
        TextView txt2 = findViewById(R.id.difficolta);
        int visibilità=0;
        if(b)
        {
            visibilità=1;
            for (Button pulsante: pulsanti) {
                pulsante.animate().alpha(visibilità).setDuration(1000);
            }
        }
        else
        {
            for (Button pulsante: pulsanti) {
                pulsante.animate().alpha(visibilità).setDuration(0);
            }
        }

        txt.animate().alpha(visibilità);
        txt2.animate().alpha(visibilità);
    }

    public void gioca(View v)
    {
        setCliccabilitàPulsanti(true);
        ImageView img = findViewById(R.id.sfondo_pagina_principale);
        img.animate().alpha(0).setDuration(1000);

        TextView txt = findViewById(R.id.titolo);
        txt.animate().alpha(0).setDuration(5000);
        TextView txt2 = findViewById(R.id.textView2);
        txt2.animate().alpha(0).setDuration(3000);

        Button button = findViewById(v.getId());
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);
    }

    public static Difficoltà getDiff()
    {
        return diff;
    }

    public static Campo getCampo()
    {
        return campo;
    }

}