package com.example.mnemosine_allena_la_memoria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectEsercizi extends AppCompatActivity {

    private Campo c;
    private Difficoltà d;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_esercizi);

        c = Home.getCampo();
        TextView campo_selezionato = findViewById(R.id.campo);
        campo_selezionato.setText(String.valueOf(c).toLowerCase() + " : ");

        d = Home.getDiff();
        TextView difficolta = findViewById(R.id.diff);
        difficolta.setText(String.valueOf(d).toLowerCase());
        ImageView immagine = findViewById(R.id.img_campo);
        Button esercizio_1 = findViewById(R.id.es1);
        Button esercizio_2 = findViewById(R.id.es2);
        Button esercizio_3 = findViewById(R.id.es3);

        switch (c) {
            case LOGICA:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bussola));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setText("Associazioni <SOLO AVANZATO>");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case MEMORIA:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.memoria));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setText("Memory");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case ATTENZIONE:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bussola));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setText("Trova l'intruso");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case LINGUAGGIO:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lingua));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setText("Associazioni <SOLO INTERMEDIO>");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case ORIENTAMENTO:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bussola));
                esercizio_1.setVisibility(View.INVISIBLE);
                esercizio_1.setClickable(false);
                esercizio_2.setText("Spaziale");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setText("Temporale");
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void Esercizi(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.es1:
                switch (c) {
                    case LINGUAGGIO:
                        intent = new Intent(SelectEsercizi.this, Associazioni.class);
                        startActivity(intent);
                        break;
                    case ATTENZIONE:
                        intent = new Intent(SelectEsercizi.this, Intruso.class);
                        startActivity(intent);
                        break;
                    case MEMORIA:
                        intent = new Intent(SelectEsercizi.this, Memory.class);
                        startActivity(intent);
                        break;
                    case LOGICA:
                        intent = new Intent(SelectEsercizi.this, Sequenze.class);
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.es2:
                switch (c) {
                    case ORIENTAMENTO:
                        break;
                    case LINGUAGGIO:
                        break;
                    case ATTENZIONE:
                        break;
                    case MEMORIA:
                        break;
                    case LOGICA:
                        break;
                }
                break;
            case R.id.es3:
                switch (c) {
                    case ORIENTAMENTO:
                        intent = new Intent(SelectEsercizi.this, OrientamentoTemporale.class);
                        startActivity(intent);
                        break;
                    case LINGUAGGIO:
                        break;
                    case ATTENZIONE:
                        break;
                    case MEMORIA:
                        break;
                    case LOGICA:
                        break;
                }
                break;
        }

    }

    public void indietro(View v) {
        Intent intent = new Intent(SelectEsercizi.this, Home.class);
        startActivity(intent);
        finish();
    }

}