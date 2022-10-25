package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class SelectEsercizi extends AppCompatActivity {

    private TextView campo_selezionato;
    private TextView difficolta;
    private ImageView immagine;
    private Button esercizio_1, esercizio_2, esercizio_3;
    private Difficoltà d;
    private Campo c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_esercizi);
        c = Home.getCampo();
        campo_selezionato=findViewById(R.id.campo);
        campo_selezionato.setText(String.valueOf(c).toLowerCase()+" : ");
        d = Home.getDiff();
        difficolta=findViewById(R.id.diff);
        difficolta.setText(String.valueOf(d).toLowerCase());
        immagine=findViewById(R.id.img_campo);
        esercizio_1=findViewById(R.id.es1);
        esercizio_2=findViewById(R.id.es2);
        esercizio_3=findViewById(R.id.es3);

        switch(c)
        {
            case LOGICA:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bussola));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setBackgroundColor(0xFFBDB6B6);
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case MEMORIA:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bussola));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setBackgroundColor(0xFFBDB6B6);
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case ATTENZIONE:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bussola));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setText("Trova l'intruso");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case LINGUAGGIO:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bussola));
                esercizio_1.setVisibility(View.VISIBLE);
                esercizio_1.setClickable(true);
                esercizio_1.setBackgroundColor(0xFFBDB6B6);
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setBackgroundColor(0xFFBDB6B6);
                break;
            case ORIENTAMENTO:
                immagine.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bussola));
                esercizio_1.setVisibility(View.INVISIBLE);
                esercizio_1.setClickable(false);
                esercizio_2.setText("Spaziale");
                esercizio_2.setBackgroundColor(0xFFBDB6B6);
                esercizio_3.setText("Temporale");
                break;
        }
    }

    public void Esercizi(View v)
    {
        Intent intent;
        switch(v.getId())
        {
            case R.id.es1:
                switch(c)
                {
                    case LINGUAGGIO:
                        break;
                    case ATTENZIONE:
                        intent = new Intent(SelectEsercizi.this, Intruso.class);
                        startActivity(intent);
                        break;
                    case MEMORIA:
                        break;
                    case LOGICA:
                        break;
                }
            case R.id.es2:
                switch(c)
                {
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
            case R.id.es3:
                switch(c)
                {
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
        }

    }

    public void indietro(View v)
    {
        Intent intent = new Intent(SelectEsercizi.this,Home.class);
        startActivity(intent);
        finish();
    }

}