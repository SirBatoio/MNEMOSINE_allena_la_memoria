package com.example.mnemosine_allena_la_memoria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Sequenze extends AppCompatActivity {
    private ImageView immagine2;
    private Bitmap cerchio_bianco, cerchio_nero, cerchio_mezzo, quadrato_bianco, quadrato_nero, quadrato_mezzo, triangolo_bianco, triangolo_nero, triangolo_mezzo, freccia_bianco, freccia_nero;
    private ArrayList<ImageView> sequenza = new ArrayList(), risposte = new ArrayList();
    private ArrayList<Immagine> immagini_seq1 = new ArrayList(), immagini_seq2 = new ArrayList(), immagini_seq3 = new ArrayList(), immagini_seq4 = new ArrayList(), immagini_seq5 = new ArrayList(), immagini_seq6 = new ArrayList(), immagini_seq7 = new ArrayList(), immagini_seq8 = new ArrayList(), immagini_seq9 = new ArrayList(), immagini_seq10 = new ArrayList(), immagini_seq11 = new ArrayList(), immagini_seq12 = new ArrayList(), immagini_seq13 = new ArrayList(), immagini_seq14 = new ArrayList(), immagini_risp = new ArrayList();
    private ArrayList<ArrayList<Immagine>> domande = new ArrayList();
    private MediaPlayer mp;
    private static final long TEMPO = 2000;
    private boolean volume=true;
    private int pt_max, livello =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequenze);

        sequenza.add(findViewById(R.id.seq_1));
        sequenza.add(findViewById(R.id.seq_2));
        sequenza.add(findViewById(R.id.seq_3));
        risposte.add(findViewById(R.id.risp_1));
        risposte.add(findViewById(R.id.risp_2));
        risposte.add(findViewById(R.id.risp_3));
        immagine2=findViewById(R.id.immagine2);

        cerchio_bianco=BitmapFactory.decodeResource(getResources(), R.drawable.cerchio_b);
        cerchio_nero=BitmapFactory.decodeResource(getResources(), R.drawable.cerchio_n);
        cerchio_mezzo=BitmapFactory.decodeResource(getResources(), R.drawable.cerchio_m);
        quadrato_bianco=BitmapFactory.decodeResource(getResources(), R.drawable.quadrato_b);
        quadrato_nero=BitmapFactory.decodeResource(getResources(), R.drawable.quadrato_n);
        quadrato_mezzo=BitmapFactory.decodeResource(getResources(), R.drawable.quadrato_m);
        triangolo_bianco=BitmapFactory.decodeResource(getResources(), R.drawable.triangolo_b);
        triangolo_nero=BitmapFactory.decodeResource(getResources(), R.drawable.triangolo_n);
        triangolo_mezzo=BitmapFactory.decodeResource(getResources(), R.drawable.triangolo_m);
        freccia_bianco=BitmapFactory.decodeResource(getResources(), R.drawable.freccia_b);
        freccia_nero=BitmapFactory.decodeResource(getResources(), R.drawable.freccia_n);

        generaDomande();

       restart();

    }

    public void generaDomande()
    {
        immagini_seq1.add(new Immagine(cerchio_bianco, 0));
        immagini_seq1.add(new Immagine(cerchio_nero, 0));
        immagini_seq1.add(new Immagine(quadrato_bianco, 0));
        immagini_seq1.add(new Immagine(quadrato_nero, 0));
        immagini_risp.add(new Immagine(cerchio_nero, 0));
        immagini_risp.add(new Immagine(quadrato_mezzo, 0));
        immagini_risp.add(new Immagine(quadrato_nero, 0));
        aggiungi(immagini_seq1);
        immagini_seq2.add(new Immagine(freccia_nero, 0));
        immagini_seq2.add(new Immagine(freccia_nero, 180));
        immagini_seq2.add(new Immagine(freccia_nero, 90));
        immagini_seq2.add(new Immagine(freccia_nero, 270));
        immagini_risp.add(new Immagine(freccia_nero, 90));
        immagini_risp.add(new Immagine(freccia_bianco, 270));
        immagini_risp.add(new Immagine(freccia_nero, 270));
        aggiungi(immagini_seq2);
    }


    public void aggiungi(ArrayList<Immagine> immagini_seq)
    {
        Collections.shuffle(immagini_risp);
        immagini_seq.addAll(immagini_risp);
        domande.add(immagini_seq);
    }

    public void controllo(View v)
    {
        immagini_risp.clear();
        immagini_risp.add(domande.get(livello).get(4));
        immagini_risp.add(domande.get(livello).get(5));
        immagini_risp.add(domande.get(livello).get(6));
        switch (v.getId())
        {
            case R.id.risp_1:
                if(domande.get(livello).get(3).getImmagine()==immagini_risp.get(0).getImmagine()&&domande.get(livello).get(3).getRotazione()==immagini_risp.get(0).getRotazione())
                {
                    Log.d("Giusto", "=Vero");
                    giusto();
                }
                else
                {
                    Log.d("Giusto", "=Falso ");
                    errore();
                }
                break;
            case R.id.risp_2:
                if(domande.get(livello).get(3).getImmagine()==immagini_risp.get(1).getImmagine()&&domande.get(livello).get(3).getRotazione()==immagini_risp.get(1).getRotazione())
                {
                    Log.d("Giusto", "=Vero");
                    giusto();
                }
                else
                {
                    Log.d("Giusto", "=Falso ");
                    errore();
                }
                break;
            case R.id.risp_3:
                if(domande.get(livello).get(3).getImmagine()==immagini_risp.get(2).getImmagine()&&domande.get(livello).get(3).getRotazione()==immagini_risp.get(2).getRotazione())
                {
                    Log.d("Giusto", "=Vero");
                    giusto();
                }
                else
                {
                    Log.d("Giusto", "=Falso ");
                    errore();
                }
                break;
        }

    }

    public void giusto(){
        mp = MediaPlayer.create(this,R.raw.giusto);
        immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.giusto));
        if(volume){ mp.start();}
        animaImmagineEsito();
        livello++;
        restart();
    }

    public void errore(){
        mp = MediaPlayer.create(this,R.raw.errore);
        immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sbaglio));
        if(volume){ mp.start();}
        animaImmagineEsito();
        pt_max++;
        restart();
    }

    public void restart(){
        int j=0;

        for(int i=0; i<sequenza.size(); i++)
        {
            sequenza.get(i).setImageBitmap(domande.get(livello).get(j).getImmagine());
            sequenza.get(i).setRotation(domande.get(livello).get(j).getRotazione());
            j++;
        }
        j++;
        for(int i=0; i<risposte.size(); i++)
        {
            risposte.get(i).setImageBitmap(domande.get(livello).get(j).getImmagine());
            risposte.get(i).setRotation(domande.get(livello).get(j).getRotazione());
            j++;
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

    public void animaImmagineEsito() {
        if(immagine2.getRotationY()>=180&&immagine2.getRotationY()<360)
        {
            immagine2.animate().rotationY(90).setDuration(TEMPO);
        }
        else
        {
            immagine2.animate().rotationY(270).setDuration(TEMPO);
        }
    }

    public void indietro(View v)
    {
        Intent intent = new Intent(Sequenze.this,SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    public void home(View v)
    {
        Intent intent = new Intent(Sequenze.this,Home.class);
        startActivity(intent);
        finish();
    }
}