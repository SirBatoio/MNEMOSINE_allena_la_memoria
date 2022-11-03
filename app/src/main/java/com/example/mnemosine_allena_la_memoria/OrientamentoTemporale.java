package com.example.mnemosine_allena_la_memoria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class OrientamentoTemporale extends AppCompatActivity {

    private static final long TEMPO = 2000;
    private final ArrayList<Button> opzioni = new ArrayList<>();
    private final ArrayList<String> listaRisposte = new ArrayList<>();
    private final ArrayList<Domanda> domande = new ArrayList<>();
    private final ArrayList<Bitmap> galleria = new ArrayList<>();
    private final ArrayList<Bitmap> orologi = new ArrayList<>();
    private final int l = 10;
    private TextView domanda, testo;
    private ImageView esito, immagine;
    private Difficoltà diff;
    private String domandaStagione;
    private boolean volume = true;
    private int i = -1, tentativi = 2, pt_totalizzati = 0, pt_max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientamento_temporale);

        diff = Home.getDiff();

        // findViewById
        immagine = findViewById(R.id.immagine);
        esito = findViewById(R.id.esito0);
        domanda = findViewById(R.id.domanda);
        testo = findViewById(R.id.testo);

        // Aggiunge i pulsanti all'ArrayList opzioni
        opzioni.add(findViewById(R.id.R1));
        opzioni.add(findViewById(R.id.R2));
        opzioni.add(findViewById(R.id.R3));
        opzioni.add(findViewById(R.id.R0));
        // Non tutte le domande hanno 4 opzioni quindi un pulsante viene reso invisibile
        opzioni.get(3).setVisibility(View.INVISIBLE);
        opzioni.get(3).setClickable(false);

        // Rende l'ImageView invisibile ruotandola di 90 gradi sull'asse Y
        esito.setRotationY(90);

        riempiOrologi();
        riempiGalleria();

        riempiDomande();

        aumentaLivello();

        pt_max = l;
    }

    // Controlla che la rispota da parte dell'utente sia corretta
    public void Controllo(@NonNull View v) {
        Button premuto = findViewById(v.getId());
        MediaPlayer mp;

        if (premuto.getText().toString().equals(domande.get(i).getRispostaGiusta())) {
            /* Se il testo del pulsante è uguale alla risposta corretta alla domada:
            - rende riproducibile il file "giusto.mp3"
            - imposta l'immagine "giusto.png" come esito
            - aumenta i punti totalizzati
            - fa passare al livello successivo*/
            mp = MediaPlayer.create(this, R.raw.giusto);
            esito.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));
            pt_totalizzati++;
            aumentaLivello();

        } else {
            /* Se la risposta è sbagliata:
            - rende riproducibile il file "errore.mp3"
            - imposta l'immagine "sbaglio.png" come esito
            - modifica il colore del pulsante premuto per far notre che quella la risposta è errata
            - riduce il numero di tentativi
            - aumenta i punti massimi*/
            mp = MediaPlayer.create(this, R.raw.errore);
            esito.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sbaglio));
            premuto.setBackgroundColor(0xFFE91E63);
            tentativi--;
            pt_max++;
            if (tentativi == 1) {
                // Se ci sono ancora tentativi disponibili anima l'immagine
                animaImmagineEsito();
                // Toglierlo non dovrebbe cambiare niente ma meglio prevenire che curare
                listaRisposte.clear();
            }
        }
        // Se il pulsante del volume è su "ON" riproduce il file .mp3
        if (volume) {
            mp.start();
        }
        // Se non ci sono tentativi disponibili si passa al livello successivo
        if (tentativi == 0) {
            aumentaLivello();
        }
    }

    // Metodo che si occupa dell'aumento di livello
    @SuppressLint("SetTextI18n")
    public void aumentaLivello() {
        i++; // livello attuale aumentato
        tentativi = 2; // Ripristina a 2 i tentativi

        // Rende tutti i pulsanti blu nel caso in cui nel livello precedente fossero diventati rossi
        for (Button b : opzioni) {
            b.setBackgroundColor(0xFF6495ED);
        }

        animaImmagineEsito();

        // Svuota listaRisposte altrimenti sarebbero presenti anche le risposte del livello precedente
        listaRisposte.clear();

        // Se il livello è diverso del livello massimo prepara il livello successivo
        if (i != l) {
            switch (domande.get(i).getTesto()) {
                case "Stagione":
                    // Se la domanda è a tema stagioni
                    switch (domande.get(i).getRispostaGiusta()) {
                        // In base alla risposta esatta alla domanda prende l'immagine opportuna
                        case "Autunno":
                            immagine.setImageBitmap(galleria.get(0));
                            break;
                        case "Primavera":
                            immagine.setImageBitmap(galleria.get(1));
                            break;
                        case "Estate":
                            immagine.setImageBitmap(galleria.get(2));
                            break;
                        case "Inverno":
                            immagine.setImageBitmap(galleria.get(3));
                            break;
                    }

                    // Rende l'immagine visiblie
                    immagine.animate().alpha(1);
                    // Scrive la domanda nella TextView superiore
                    testo.setText(domandaStagione);
                    domanda.setText("");

                    // Aggiunge le stagioni come risposte possibili alla domanda
                    listaRisposte.add("Autunno");
                    listaRisposte.add("Primavera");
                    listaRisposte.add("Estate");
                    listaRisposte.add("Inverno");

                    // Avendo 4 risposte è necessatio rendere visibile il quarto pulsante
                    opzioni.get(3).setVisibility(View.VISIBLE);
                    opzioni.get(3).setClickable(true);
                    opzioni.get(3).setText(listaRisposte.get(3));
                    break;
                case "Ore":
                    // Se la domanda riguarda il riconoscimento dell'ora data l'immagine di un orologio
                    // Scrive la domanda nella TextView superiore
                    testo.setText("Che ore sono ?");
                    // Rende invisibile e non cliccabile il pulsante non necessario
                    opzioni.get(3).setVisibility(View.INVISIBLE);
                    opzioni.get(3).setClickable(false);
                    switch (diff) {
                        // In base alla difficoltà verranno selezionate immagini diverse
                        case FACILE:
                            // l'immagine viene selezionata in base alla risposta corretta alla domanda selezionata
                            switch (domande.get(i).getRispostaGiusta()) {
                                case "3:00":
                                    immagine.setImageBitmap(orologi.get(0));
                                    break;
                                case "6:00":
                                    immagine.setImageBitmap(orologi.get(1));
                                    break;
                                case "11:00":
                                    immagine.setImageBitmap(orologi.get(2));
                                    break;
                                case "7:15":
                                    immagine.setImageBitmap(orologi.get(3));
                                    break;
                            }
                            break;
                        case INTERMEDIO:
                            switch (domande.get(i).getRispostaGiusta()) {
                                case "12:30":
                                    immagine.setImageBitmap(orologi.get(0));
                                    break;
                                case "3:30":
                                    immagine.setImageBitmap(orologi.get(1));
                                    break;
                                case "8:20":
                                    immagine.setImageBitmap(orologi.get(2));
                                    break;
                                case "4:10":
                                    immagine.setImageBitmap(orologi.get(3));
                                    break;
                            }
                            break;
                        case AVANZATO:
                            switch (domande.get(i).getRispostaGiusta()) {
                                case "6:30":
                                    immagine.setImageBitmap(orologi.get(0));
                                    break;
                                case "13:35":
                                    immagine.setImageBitmap(orologi.get(1));
                                    break;
                                case "5:50":
                                    immagine.setImageBitmap(orologi.get(2));
                                    break;
                                case "1:55":
                                    immagine.setImageBitmap(orologi.get(3));
                                    break;
                            }
                            break;
                    }
                    // Rende l'immagine visibile
                    immagine.animate().alpha(1);

                    // Inserisce le risposte
                    listaRisposte.add(domande.get(i).getRispostaGiusta());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata1());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata2());
                    Collections.shuffle(listaRisposte);

                    opzioni.get(3).setVisibility(View.INVISIBLE);
                    opzioni.get(3).setClickable(false);
                    break;
                default:
                    // Se la domanda è generica
                    // L'immagine viene fatta sparire
                    immagine.animate().alpha(0);

                    if (domande.get(i).getTesto().startsWith("Q")) {
                        // Se la domanda inizia per "Q"
                        // srive la domanda nella TextView superiore
                        testo.setText(domande.get(i).getTesto());
                        domanda.setText("");
                    }
                    else
                    {
                        // srive la domanda nella TextView laterale
                        domanda.setText(domande.get(i).getTesto());
                        testo.setText("Quando si festeggia?");
                    }

                    // Inserisce le risposte
                    listaRisposte.add(domande.get(i).getRispostaGiusta());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata1());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata2());
                    Collections.shuffle(listaRisposte);

                    opzioni.get(3).setVisibility(View.INVISIBLE);
                    opzioni.get(3).setClickable(false);

            }
            // Inserisce le risposte nei pulsanti
            opzioni.get(0).setText(listaRisposte.get(0));
            opzioni.get(1).setText(listaRisposte.get(1));
            opzioni.get(2).setText(listaRisposte.get(2));
        } else {
            // Se la partita è terminata porta all'Activity "Risultati.java"
            Intent intent2 = new Intent(OrientamentoTemporale.this, Risultati.class);
            startActivity(intent2);
            // passa alla nuova Activity i punti totalizzati, i punti massimi e la classe
            Risultati.setPunti_totalizzati(pt_totalizzati);
            Risultati.setPunti_massimi(pt_max);
            Risultati.setCls(OrientamentoTemporale.class);
            finish();
        }
    }

    // Effettua la rotazione dell'immagine per mostrare l'esito della risposta all'utente
    public void animaImmagineEsito() {
        if (esito.getRotationY() >= 180 && esito.getRotationY() < 360) {
            esito.animate().rotationY(90).setDuration(TEMPO);
        } else {
            esito.animate().rotationY(270).setDuration(TEMPO);
        }
    }

    // Genera l'array di domande
    public void riempiDomande() {
        switch (diff) {
            case FACILE:
                // Domande facili
                domande.add(new Domanda("Quanti giorni ha una settimana?", "sette", "otto", "sei"));
                domande.add(new Domanda("Quanti giorni ha un anno?", "365", "30", "360"));
                domande.add(new Domanda("Quanti giorni ha novembre?", "30", "31", "28"));
                domande.add(new Domanda("Quanti giorni ha maggio?", "31", "30", "28"));
                domande.add(new Domanda("Quanti giorni ha febbraio?", "28", "30", "31"));
                domande.add(new Domanda("Qual è il mese successivo ad agosto?", "Settembre", "Luglio", "Marzo"));
                domande.add(new Domanda("Qual è il mese precedente a maggio?", "Aprile", "Giugno", "Ottobre"));
                domande.add(new Domanda("Natale", "25 Dicembre", "25 Aprile", "2 Giugno"));
                domande.add(new Domanda("Pasqua", "Marzo - Aprile", "Agosto - Settembre", "Novembre - Dicembre"));
                break;
            case INTERMEDIO:
                // Domande intermedie
                domande.add(new Domanda("Natale", "25 Dicembre", "25 Aprile", "2 Giugno"));
                domande.add(new Domanda("Epifania", "6 Gennaio", "6 Dicembre", "1 Maggio"));
                domande.add(new Domanda("Carnevale", "Febbraio", "Luglio", "Novembre"));
                domande.add(new Domanda("Pasqua", "Marzo - Aprile", "Agosto - Settembre", "Novembre - Dicembre"));
                domande.add(new Domanda("Ferragosto", "15 Agosto", "25 Agosto", "1 Ottobre"));
                domande.add(new Domanda("I morti", "2 Novembre", "1 Novembre", "4 Maggio"));
                domande.add(new Domanda("Festa della liberazione", "25 Aprile", "25 Dicembre", "28 Gennaio"));
                domande.add(new Domanda("Festa della repubblica", "2 Giugno", "1 Maggio", "2 Maggio"));
                domande.add(new Domanda("Festa dei lavoratori", "1 Maggio", "9 Settembre", "1 Novembre"));
                domande.add(new Domanda("S. Stefano", "26 Dicembre", "26 Marzo", "8 Dicembre"));
                domande.add(new Domanda("Immacolata concezione", "8 Dicembre", "6 Gennaio", "8 Febbraio"));
                domande.add(new Domanda("Tutti i santi", "1 Novembre", "1 Maggio", "2 Novembre"));
                break;
            case AVANZATO:
                // Domande avanzate
                domande.add(new Domanda("Epifania", "6 Gennaio", "6 Dicembre", "1 Maggio"));
                domande.add(new Domanda("Carnevale", "Febbraio", "Luglio", "Novembre"));
                domande.add(new Domanda("Ferragosto", "15 Agosto", "25 Agosto", "1 Ottobre"));
                domande.add(new Domanda("I morti", "2 Novembre", "1 Novembre", "4 Maggio"));
                domande.add(new Domanda("Festa della liberazione", "25 Aprile", "25 Dicembre", "28 Gennaio"));
                domande.add(new Domanda("Festa della repubblica", "2 Giugno", "1 Maggio", "2 Maggio"));
                domande.add(new Domanda("Festa dei lavoratori", "1 Maggio", "9 Settembre", "1 Novembre"));
                domande.add(new Domanda("S. Stefano", "26 Dicembre", "26 Marzo", "8 Dicembre"));
                domande.add(new Domanda("Immacolata concezione", "8 Dicembre", "6 Gennaio", "8 Febbraio"));
                domande.add(new Domanda("Tutti i santi", "1 Novembre", "1 Maggio", "2 Novembre"));
                break;
        }

        // Aggiunge le domande sulle stagioni
        domande.add(new Domanda("Stagione", "Autunno"));
        domande.add(new Domanda("Stagione", "Estate"));
        domande.add(new Domanda("Stagione", "Primavera"));
        domande.add(new Domanda("Stagione", "Inverno"));

        Collections.shuffle(domande);
    }

    // Aggiunge le immagini per le domande sulle stagioni
    public void riempiGalleria() {
        switch (diff) {
            case FACILE:
                domandaStagione = "Che stagione è ?";
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.autunno));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.primavera));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.estate));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.inverno));
                break;
            case INTERMEDIO:
                domandaStagione = "In che stagione si mangia ?";
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.melagrana));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.asparagi));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.anguria));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.carota));
                break;
            case AVANZATO:
                domandaStagione = "In che stagione si indossa ?";
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_autunnale));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_primaverile));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_estivo));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_invernale));
                break;
        }
    }

    // Aggiunge domande sugli orologi e inserisce le immagini all'interno di un'ArrayList
    public void riempiOrologi() {
        switch (diff) {
            case FACILE:
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.tre));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.sei));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.undici));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.sette15));
                domande.add(new Domanda("Ore", "3:00", "12:15", "5:30"));
                domande.add(new Domanda("Ore", "6:00", "12:30", "7:13"));
                domande.add(new Domanda("Ore", "11:00", "11:55", "9:45"));
                domande.add(new Domanda("Ore", "7:15", "3:45", "7:03"));
                break;
            case INTERMEDIO:
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.dodici30));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.tre30));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.otto20));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.quattro10));
                domande.add(new Domanda("Ore", "12:30", "1:30", "6:00"));
                domande.add(new Domanda("Ore", "3:30", "6:15", "4:30"));
                domande.add(new Domanda("Ore", "8:20", "4:40", "8:04"));
                domande.add(new Domanda("Ore", "4:10", "2:20", "4:02"));
                break;
            case AVANZATO:
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.sei30));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.uno35));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.cinque50));
                orologi.add(BitmapFactory.decodeResource(getResources(), R.drawable.uno55));
                domande.add(new Domanda("Ore", "6:30", "7:30", "6:00"));
                domande.add(new Domanda("Ore", "13:35", "7:05", "2:35"));
                domande.add(new Domanda("Ore", "5:50", "11:25", "6:50"));
                domande.add(new Domanda("Ore", "1:55", "2:55", "11:05"));
                break;
        }
    }

    // Cambia il volume da  "ON" a "OFF" e vice versa
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

    // Torna all'Activity precedente
    public void indietro(View v) {
        Intent intent = new Intent(OrientamentoTemporale.this, SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    // Torna all'Activity "Home.java"
    public void home(View v) {
        Intent intent = new Intent(OrientamentoTemporale.this, Home.class);
        startActivity(intent);
        finish();
    }

}
