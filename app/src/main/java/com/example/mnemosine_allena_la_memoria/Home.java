package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private static Difficoltà diff= Difficoltà.FACILE;
    private static Campo campo;
    private TextView testo;
    private ArrayList<Button> pulsanti = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        intent = new Intent(Home.this, SelectEsercizi.class);
        startActivity(intent);
    }

    public static Difficoltà getDiff()
    {
        return diff;
    }
    public static void setDiff(Difficoltà d)
    {
        diff = d;
    }

    public static Campo getCampo()
    {
        return campo;
    }
}