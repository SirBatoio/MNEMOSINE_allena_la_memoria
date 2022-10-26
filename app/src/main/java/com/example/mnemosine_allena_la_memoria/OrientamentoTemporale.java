package com.example.mnemosine_allena_la_memoria;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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

    private TextView domanda, testo;
    private ImageView /*esito,*/ immagine;
    //private MediaPlayer mp;
    private Difficoltà diff;
    private ArrayList<Button> opzioni = new ArrayList<>();
    private ArrayList<String> listaRisposte = new ArrayList<>();
    private ArrayList<Domanda> domande = new ArrayList<>();
    private ArrayList<Bitmap> galleria = new ArrayList<>(), orologi = new ArrayList<>();
    private String domandaStagione;
    private boolean volume=true;
    //private static final long TEMPO = 2000;
    private int i=-1, l=10, tentativi=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientamento_temporale);

        diff=Home.getDiff();

        immagine = findViewById(R.id.immagine);
        /*esito = findViewById(R.id.esito0);
        esito.animate().rotationY(90);*/

        domanda = findViewById(R.id.domanda);
        testo = findViewById(R.id.testo);

        opzioni.add(findViewById(R.id.R1));
        opzioni.add(findViewById(R.id.R2));
        opzioni.add(findViewById(R.id.R3));
        opzioni.add(findViewById(R.id.R0));
        opzioni.get(3).setVisibility(View.INVISIBLE);
        opzioni.get(3).setClickable(false);

        riempiOrologi();
        riempiGalleria();

        riempiDomande();

        aumentaLivello();
    }

    public void Controllo(@NonNull View v) {
        Button premuto = findViewById(v.getId());

        if (premuto.getText().toString().equals(domande.get(i).getRispostaGiusta()))
        {
            /*mp = MediaPlayer.create(this,R.raw.giusto);
            esito.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));*/
            aumentaLivello();
        }
        else
        {
            /*mp = MediaPlayer.create(this,R.raw.errore);
            esito.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sbaglio));*/
            premuto.setBackgroundColor(0xFFE91E63);
            tentativi--;
            if(tentativi==1)
            {
                //animaImmagineEsito();
                Intent intent= new Intent(OrientamentoTemporale.this, Animazioni.class);
                startActivity(intent);
                Animazioni.spin(false, volume);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Animazioni.stop();
                    //
                listaRisposte.clear();
            }
        }
        /*if(volume)
        {
            mp.start();
        }*/
        if(tentativi==0)
        {aumentaLivello();}
    }

    public void aumentaLivello() {
        i++;
        tentativi=2;

        for (Button b:opzioni) {
            b.setBackgroundColor(0xFF6495ED);
        }

        //animaImmagineEsito();
        Intent intent= new Intent(OrientamentoTemporale.this, Animazioni.class);
        startActivity(intent);
        try {
            sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Animazioni.spin(true, volume);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Animazioni.stop();
        //
        listaRisposte.clear();

        if(i!=l) {
            switch (domande.get(i).getTesto())
            {
                case "Stagione":
                    switch (domande.get(i).getRispostaGiusta())
                    {
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
                    immagine.animate().alpha(1);
                    testo.setText(domandaStagione);
                    domanda.setText("");

                    listaRisposte.add("Autunno");
                    listaRisposte.add("Primavera");
                    listaRisposte.add("Estate");
                    listaRisposte.add("Inverno");

                    opzioni.get(3).setVisibility(View.VISIBLE);
                    opzioni.get(3).setClickable(true);
                    opzioni.get(3).setText(listaRisposte.get(3));
                    break;
                case "Ore":
                    testo.setText("Che ore sono ?");
                    opzioni.get(3).setVisibility(View.INVISIBLE);
                    opzioni.get(3).setClickable(false);
                    switch (diff)
                    {
                        case FACILE:
                            switch (domande.get(i).getRispostaGiusta())
                            {
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
                            switch (domande.get(i).getRispostaGiusta())
                            {
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
                            switch (domande.get(i).getRispostaGiusta())
                            {
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
                    immagine.animate().alpha(1);
                    listaRisposte.add(domande.get(i).getRispostaGiusta());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata1());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata2());
                    Collections.shuffle(listaRisposte);

                    opzioni.get(3).setVisibility(View.INVISIBLE);
                    opzioni.get(3).setClickable(false);
                    break;
                default:
                    immagine.animate().alpha(0).setDuration(0);

                    domanda.setText(domande.get(i).getTesto());
                    testo.setText("Quando si festeggia?");

                    if(domande.get(i).getTesto().startsWith("Q"))
                    {
                        testo.setText(domande.get(i).getTesto());
                        domanda.setText("");
                    }

                    listaRisposte.add(domande.get(i).getRispostaGiusta());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata1());
                    listaRisposte.add(domande.get(i).getRispostaSbagliata2());
                    Collections.shuffle(listaRisposte);

                    opzioni.get(3).setVisibility(View.INVISIBLE);
                    opzioni.get(3).setClickable(false);

            }
            opzioni.get(0).setText(listaRisposte.get(0));
            opzioni.get(1).setText(listaRisposte.get(1));
            opzioni.get(2).setText(listaRisposte.get(2));
        }
        else
        {
            Intent intent2 = new Intent(OrientamentoTemporale.this, Home.class);
            startActivity(intent2);
            finish();
        }
    }

/*    public void animaImmagineEsito() {
        if(esito.getRotationY()>=180&&esito.getRotationY()<360)
        {
            esito.animate().rotationY(90).setDuration(TEMPO);
        }
        else
        {
            esito.animate().rotationY(270).setDuration(TEMPO);
        }
    }*/

    public void riempiDomande() {
        switch (diff)
        {
            case FACILE:
                domande.add(new Domanda("Quanti giorni ha una settimana?","sette","otto","sei"));
                domande.add(new Domanda("Quanti giorni ha un anno?","365","30","360"));
                domande.add(new Domanda("Quanti giorni ha novembre?","30","31","28"));
                domande.add(new Domanda("Quanti giorni ha maggio?","31","30","28"));
                domande.add(new Domanda("Quanti giorni ha febbraio?","28","30","31"));
                domande.add(new Domanda("Qual è il mese successivo ad agosto?","Settembre","Luglio","Marzo"));
                domande.add(new Domanda("Qual è il mese precedente a maggio?","Aprile","Giugno","Ottobre"));
                domande.add(new Domanda("Natale", "25 Dicembre", "25 Aprile", "2 Giugno"));
                domande.add(new Domanda("Pasqua", "Marzo - Aprile", "Agosto - Settembre", "Novembre - Dicembre"));
                break;
            case INTERMEDIO:
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

        domande.add(new Domanda("Stagione", "Autunno"));
        domande.add(new Domanda("Stagione", "Estate"));
        domande.add(new Domanda("Stagione", "Primavera"));
        domande.add(new Domanda("Stagione", "Inverno"));

        Collections.shuffle(domande);
    }

    public void riempiGalleria() {
        switch (diff)
        {
            case FACILE:
                domandaStagione="Che stagione è ?";
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.autunno));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.primavera));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.estate));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.inverno));
                break;
            case INTERMEDIO:
                domandaStagione="In che stagione si mangia ?";
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.melagrana));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.asparagi));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.anguria));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.carota));
                break;
            case AVANZATO:
                domandaStagione="In che stagione si indossa ?";
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_autunnale));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_primaverile));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_estivo));
                galleria.add(BitmapFactory.decodeResource(getResources(), R.drawable.vestito_invernale));
                break;
        }
    }

    public void riempiOrologi() {
        switch (diff)
        {
            case FACILE:
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.tre));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.sei));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.undici));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.sette15));
                domande.add(new Domanda("Ore","3:00","12:15","5:30"));
                domande.add(new Domanda("Ore","6:00","12:30","7:13"));
                domande.add(new Domanda("Ore","11:00","11:55","9:45"));
                domande.add(new Domanda("Ore","7:15","3:45","7:03"));
                break;
            case INTERMEDIO:
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.dodici30));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.tre30));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.otto20));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.quattro10));
                domande.add(new Domanda("Ore","12:30","1:30","6:00"));
                domande.add(new Domanda("Ore","3:30","6:15","4:30"));
                domande.add(new Domanda("Ore","8:20","4:40","8:04"));
                domande.add(new Domanda("Ore","4:10","2:20","4:02"));
                break;
            case AVANZATO:
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.sei30));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.uno35));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.cinque50));
                orologi.add(BitmapFactory.decodeResource(getResources(),R.drawable.uno55));
                domande.add(new Domanda("Ore","6:30","7:30","6:00"));
                domande.add(new Domanda("Ore","13:35","7:05","2:35"));
                domande.add(new Domanda("Ore","5:50","11:25","6:50"));
                domande.add(new Domanda("Ore","1:55","2:55","11:05"));
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
        Intent intent = new Intent(OrientamentoTemporale.this,SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    public void home(View v)
    {
        Intent intent = new Intent(OrientamentoTemporale.this,Home.class);
        startActivity(intent);
        finish();
    }

}
