package com.example.mnemosine_allena_la_memoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class Memory extends AppCompatActivity {

    private boolean volume=true;
    private Difficoltà d;
    private ImageView img_1, img_2, img_3, img_4, img_5, img_6;
    private ArrayList<Immagini> galleria = new ArrayList<>();
    private Bitmap banana,carro,elicottero,tigre,estate,inverno,volpe,melograno,leone,carota;
    private Bitmap moto,pantera,lupo,gallina,mela,pera,primavera,quad,barca,camper,camion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        banana = (BitmapFactory.decodeResource(getResources(), R.drawable.banana));
        carro=  (BitmapFactory.decodeResource(getResources(), R.drawable.carro));
        elicottero=  (BitmapFactory.decodeResource(getResources(), R.drawable.elicottero));
        tigre=  (BitmapFactory.decodeResource(getResources(), R.drawable.tigre));
        estate=  (BitmapFactory.decodeResource(getResources(), R.drawable.estate));
        inverno=  (BitmapFactory.decodeResource(getResources(), R.drawable.inverno));
        volpe=  (BitmapFactory.decodeResource(getResources(), R.drawable.volpe));
        melograno=  (BitmapFactory.decodeResource(getResources(), R.drawable.melagrana));
        leone=  (BitmapFactory.decodeResource(getResources(), R.drawable.leone));
        carota=  (BitmapFactory.decodeResource(getResources(), R.drawable.carota));
        moto=  (BitmapFactory.decodeResource(getResources(), R.drawable.moto));
        pantera=  (BitmapFactory.decodeResource(getResources(), R.drawable.pantera));
        lupo=  (BitmapFactory.decodeResource(getResources(), R.drawable.lupo));
        gallina=  (BitmapFactory.decodeResource(getResources(), R.drawable.gallina));
        mela=  (BitmapFactory.decodeResource(getResources(), R.drawable.mela));
        pera=  (BitmapFactory.decodeResource(getResources(), R.drawable.pera));
        primavera=  (BitmapFactory.decodeResource(getResources(), R.drawable.primavera));
        quad=  (BitmapFactory.decodeResource(getResources(), R.drawable.quad));
        barca=  (BitmapFactory.decodeResource(getResources(), R.drawable.barca));
        camper=  (BitmapFactory.decodeResource(getResources(), R.drawable.camper));
        camion=  (BitmapFactory.decodeResource(getResources(), R.drawable.camion));

        img_1=findViewById(R.id.img1);
        img_2=findViewById(R.id.img2);
        img_3=findViewById(R.id.img3);
        img_4=findViewById(R.id.img4);
        img_5=findViewById(R.id.img5);
        img_6=findViewById(R.id.img6);
        img_1.setClickable(false);
        img_2.setClickable(false);
        img_3.setClickable(false);
        img_4.setClickable(false);
        img_5.setClickable(false);
        img_6.setClickable(false);

        d = Home.getDiff();
        switch (d)
        {
            case FACILE:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.INVISIBLE);
                img_5.setVisibility(View.INVISIBLE);
                img_6.setVisibility(View.INVISIBLE);
                break;
            case INTERMEDIO:
            case AVANZATO:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.VISIBLE);
                img_5.setVisibility(View.INVISIBLE);
                img_6.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void avanti(View v)
    {
        img_1.setClickable(true);
        img_2.setClickable(true);
        img_3.setClickable(true);
        img_4.setClickable(true);

        switch (d)
        {
            case FACILE:
                img_4.setVisibility(View.VISIBLE);
                break;
            case INTERMEDIO:
                img_5.setVisibility(View.VISIBLE);
                img_5.setClickable(true);
                break;
            case AVANZATO:
                img_5.setVisibility(View.VISIBLE);
                img_6.setVisibility(View.VISIBLE);
                img_5.setClickable(true);
                img_6.setClickable(true);
                break;
        }
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