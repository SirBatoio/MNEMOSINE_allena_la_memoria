package com.example.mnemosine_allena_la_memoria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Risultati extends AppCompatActivity {

    private static int punti_totalizzati, punti_massimi = 1;
    private static Class cls;

    public static void setPunti_totalizzati(int puntiTotalizzati) {
        punti_totalizzati = puntiTotalizzati;
    }

    public static void setPunti_massimi(int puntiMassimi) {
        punti_massimi = puntiMassimi;
    }

    public static void setCls(Class classe) {
        cls = classe;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultati);

        TextView testo = findViewById(R.id.testoRisultati);
        TextView punteggio = findViewById(R.id.punteggio);

        if ((float) punti_totalizzati / (float) punti_massimi >= 0.5f) {
            testo.setText("Bravo");
        } else {
            testo.setText("Andrà meglio la prossima volta");
        }

        punteggio.setText(punti_totalizzati + " / " + punti_massimi);
    }

    public void riprova(View v) {
        Intent intent = new Intent(Risultati.this, cls);
        startActivity(intent);
        finish();
    }

    public void home(View v) {
        Intent intent = new Intent(Risultati.this, Home.class);
        startActivity(intent);
        finish();
    }
}