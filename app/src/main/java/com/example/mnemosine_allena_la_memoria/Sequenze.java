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
import java.util.Random;

public class Sequenze extends AppCompatActivity {
    private static final long TEMPO = 2000;
    private final ArrayList<ImageView> sequenza = new ArrayList();
    private final ArrayList<ImageView> risposte = new ArrayList();
    private final ArrayList<Immagine> immagini_seq1 = new ArrayList();
    private final ArrayList<Immagine> immagini_seq2 = new ArrayList();
    private final ArrayList<Immagine> immagini_seq3 = new ArrayList();
    private final ArrayList<Immagine> immagini_seq4 = new ArrayList();
    private final ArrayList<Immagine> immagini_seq5 = new ArrayList();
    private final ArrayList<Immagine> immagini_seq6 = new ArrayList();
    private final ArrayList<Immagine> immagini_seq7 = new ArrayList();
    private final ArrayList<Immagine> immagini_risp = new ArrayList();
    private final ArrayList<ArrayList<Immagine>> domande = new ArrayList();
    private final Random random = new Random();
    private float supp;
    private ImageView immagine2;
    private Bitmap cerchio_bianco, cerchio_nero, cerchio_mezzo, quadrato_bianco, quadrato_nero, quadrato_mezzo, triangolo_bianco, triangolo_nero, freccia_bianco, freccia_nero;
    private MediaPlayer mp;
    private boolean volume = true;
    private int pt_max = 5, livello = 0, pt_totalizzati = 0;

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
        immagine2 = findViewById(R.id.immagine2);

        cerchio_bianco = BitmapFactory.decodeResource(getResources(), R.drawable.cerchio_b);
        cerchio_nero = BitmapFactory.decodeResource(getResources(), R.drawable.cerchio_n);
        cerchio_mezzo = BitmapFactory.decodeResource(getResources(), R.drawable.cerchio_m);
        quadrato_bianco = BitmapFactory.decodeResource(getResources(), R.drawable.quadrato_b);
        quadrato_nero = BitmapFactory.decodeResource(getResources(), R.drawable.quadrato_n);
        quadrato_mezzo = BitmapFactory.decodeResource(getResources(), R.drawable.quadrato_m);
        triangolo_bianco = BitmapFactory.decodeResource(getResources(), R.drawable.triangolo_b);
        triangolo_nero = BitmapFactory.decodeResource(getResources(), R.drawable.triangolo_n);
        freccia_bianco = BitmapFactory.decodeResource(getResources(), R.drawable.freccia_b);
        freccia_nero = BitmapFactory.decodeResource(getResources(), R.drawable.freccia_n);

        generaDomande();

        restart();

    }

    public void generaDomande() {

        immagini_seq1.add(new Immagine(cerchio_bianco, 0));
        immagini_seq1.add(new Immagine(cerchio_nero, 0));
        immagini_seq1.add(new Immagine(quadrato_bianco, 0));
        immagini_seq1.add(new Immagine(quadrato_nero, 0));
        immagini_risp.add(new Immagine(cerchio_nero, 0));
        immagini_risp.add(new Immagine(quadrato_mezzo, 0));
        immagini_risp.add(new Immagine(quadrato_nero, 0));
        aggiungi(immagini_seq1);
        immagini_seq2.add(new Immagine(freccia_nero, 0 + supp));
        immagini_seq2.add(new Immagine(freccia_nero, 180 + supp));
        immagini_seq2.add(new Immagine(freccia_nero, 90 + supp));
        immagini_seq2.add(new Immagine(freccia_nero, 270 + supp));
        immagini_risp.add(new Immagine(freccia_nero, 90 + supp));
        immagini_risp.add(new Immagine(freccia_bianco, 270 + supp));
        immagini_risp.add(new Immagine(freccia_nero, 270 + supp));
        aggiungi(immagini_seq2);
        immagini_seq3.add(new Immagine(freccia_nero, 180 + supp));
        immagini_seq3.add(new Immagine(freccia_nero, 0 + supp));
        immagini_seq3.add(new Immagine(freccia_nero, 180 + supp));
        immagini_seq3.add(new Immagine(freccia_nero, 0 + supp));
        immagini_risp.add(new Immagine(freccia_bianco, 0 + supp));
        immagini_risp.add(new Immagine(freccia_bianco, 180 + supp));
        immagini_risp.add(new Immagine(freccia_nero, 0 + supp));
        aggiungi(immagini_seq3);
        immagini_seq4.add(new Immagine(cerchio_mezzo, 90 + supp));
        immagini_seq4.add(new Immagine(cerchio_mezzo, 270 + supp));
        immagini_seq4.add(new Immagine(quadrato_mezzo, 90 + supp));
        immagini_seq4.add(new Immagine(quadrato_mezzo, 270 + supp));
        immagini_risp.add(new Immagine(quadrato_nero, 0));
        immagini_risp.add(new Immagine(cerchio_mezzo, 90 + supp));
        immagini_risp.add(new Immagine(quadrato_mezzo, 270 + supp));
        aggiungi(immagini_seq4);
        immagini_seq5.add(new Immagine(cerchio_bianco, 0 + supp));
        immagini_seq5.add(new Immagine(cerchio_mezzo, 90 + supp));
        immagini_seq5.add(new Immagine(cerchio_nero, 0));
        immagini_seq5.add(new Immagine(cerchio_mezzo, 270 + supp));
        immagini_risp.add(new Immagine(cerchio_nero, 0));
        immagini_risp.add(new Immagine(cerchio_bianco, 0));
        immagini_risp.add(new Immagine(cerchio_mezzo, 270 + supp));
        aggiungi(immagini_seq5);
        immagini_seq6.add(new Immagine(freccia_bianco, 0 + supp));
        immagini_seq6.add(new Immagine(triangolo_nero, 0 + supp));
        immagini_seq6.add(new Immagine(freccia_nero, 0 + supp));
        immagini_seq6.add(new Immagine(triangolo_bianco, 0 + supp));
        immagini_risp.add(new Immagine(triangolo_nero, 90 + supp));
        immagini_risp.add(new Immagine(freccia_bianco, 0 + supp));
        immagini_risp.add(new Immagine(triangolo_bianco, 0 + supp));
        aggiungi(immagini_seq6);
        immagini_seq7.add(new Immagine(cerchio_nero, 0));
        immagini_seq7.add(new Immagine(freccia_nero, 90 + supp));
        immagini_seq7.add(new Immagine(freccia_bianco, 270 + supp));
        immagini_seq7.add(new Immagine(cerchio_bianco, 0));
        immagini_risp.add(new Immagine(cerchio_nero, 0));
        immagini_risp.add(new Immagine(freccia_nero, 90));
        immagini_risp.add(new Immagine(cerchio_bianco, 0));
        aggiungi(immagini_seq7);
        Collections.shuffle(domande);
    }

    public void aggiungi(ArrayList<Immagine> immagini_seq) {
        Collections.shuffle(immagini_risp);
        immagini_seq.addAll(immagini_risp);
        domande.add(immagini_seq);
        immagini_risp.clear();
        int rand = random.nextInt(4);
        supp = rand * 90;
    }

    public void controllo(View v) {
        immagini_risp.clear();
        immagini_risp.add(domande.get(livello).get(4));
        immagini_risp.add(domande.get(livello).get(5));
        immagini_risp.add(domande.get(livello).get(6));
        switch (v.getId()) {
            case R.id.risp_1:
                if (domande.get(livello).get(3).getImmagine() == immagini_risp.get(0).getImmagine() && domande.get(livello).get(3).getRotazione() == immagini_risp.get(0).getRotazione()) {
                    Log.d("Giusto", "=Vero");
                    giusto();
                } else {
                    Log.d("Giusto", "=Falso ");
                    errore();
                }
                break;
            case R.id.risp_2:
                if (domande.get(livello).get(3).getImmagine() == immagini_risp.get(1).getImmagine() && domande.get(livello).get(3).getRotazione() == immagini_risp.get(1).getRotazione()) {
                    Log.d("Giusto", "=Vero");
                    giusto();
                } else {
                    Log.d("Giusto", "=Falso ");
                    errore();
                }
                break;
            case R.id.risp_3:
                if (domande.get(livello).get(3).getImmagine() == immagini_risp.get(2).getImmagine() && domande.get(livello).get(3).getRotazione() == immagini_risp.get(2).getRotazione()) {
                    Log.d("Giusto", "=Vero");
                    giusto();
                } else {
                    Log.d("Giusto", "=Falso ");
                    errore();
                }
                break;
        }

    }

    public void giusto() {
        mp = MediaPlayer.create(this, R.raw.giusto);
        immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));
        if (volume) {
            mp.start();
        }
        animaImmagineEsito();
        livello++;
        pt_totalizzati++;
        restart();
    }

    public void errore() {
        mp = MediaPlayer.create(this, R.raw.errore);
        immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sbaglio));
        if (volume) {
            mp.start();
        }
        animaImmagineEsito();
        pt_max++;
        restart();
    }

    public void restart() {

        int l = 5;
        if (livello == l) {
            Intent intent = new Intent(Sequenze.this, Risultati.class);
            startActivity(intent);
            Risultati.setPunti_massimi(pt_max);
            Risultati.setPunti_totalizzati(pt_totalizzati);
            Risultati.setCls(Sequenze.class);
            finish();
        }
        int j = 0;

        for (int i = 0; i < sequenza.size(); i++) {
            sequenza.get(i).setImageBitmap(domande.get(livello).get(j).getImmagine());
            sequenza.get(i).setRotation(domande.get(livello).get(j).getRotazione());
            j++;
        }
        j++;
        for (int i = 0; i < risposte.size(); i++) {
            risposte.get(i).setImageBitmap(domande.get(livello).get(j).getImmagine());
            risposte.get(i).setRotation(domande.get(livello).get(j).getRotazione());
            j++;
        }

    }

    public void cambiaVolume(@NonNull View v) {
        Button b = findViewById(v.getId());
        if (volume) {
            volume = false;
            b.setText("VOLUME OFF");
            b.setBackgroundColor(0xFF9E9E9E);
        } else {
            volume = true;
            b.setText("VOLUME ON");
            b.setBackgroundColor(0xFFFF9800);
        }
    }

    public void animaImmagineEsito() {
        if (immagine2.getRotationY() >= 180 && immagine2.getRotationY() < 360) {
            immagine2.animate().rotationY(90).setDuration(TEMPO);
        } else {
            immagine2.animate().rotationY(270).setDuration(TEMPO);
        }
    }

    public void indietro(View v) {
        Intent intent = new Intent(Sequenze.this, SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    public void home(View v) {
        Intent intent = new Intent(Sequenze.this, Home.class);
        startActivity(intent);
        finish();
    }
}