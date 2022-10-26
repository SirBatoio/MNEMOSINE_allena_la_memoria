package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Risultati extends AppCompatActivity {

    private TextView testo, punteggio;
    private static int punti_totalizzati, punti_massimi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultati);

        testo=findViewById(R.id.testoRisultati);
        punteggio=findViewById(R.id.punteggio);

        if((float)punti_totalizzati/(float)punti_massimi>=0.5f)
        {
            testo.setText("Bravo");
        }
        else
        {
            testo.setText("Andrà meglio la prossima volta");
        }

        punteggio.setText(punti_totalizzati+" / "+punti_massimi);

    }

    public static void setPunti_totalizzati(int puntiTotalizzati) {
        punti_totalizzati = puntiTotalizzati;
    }

    public static void setPunti_massimi(int puntiMassimi)
    {
        punti_massimi = puntiMassimi;
    }
}