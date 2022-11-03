package com.example.mnemosine_allena_la_memoria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Memory extends AppCompatActivity{

    private static final int LIV_MAX = 5;
    private boolean volume=true;
    private static final long TIME = 15;
    private Button b;
    private Difficoltà d;
    private ImageView img_1, img_2, img_3, img_4, img_5, img_6,immagine2;
    private final ArrayList<Bitmap> galleria = new ArrayList<>();
    private final ArrayList<Bitmap> gioco = new ArrayList<>();
    private Bitmap sbagliato;
    private Bitmap giusto;
    private MediaPlayer mp;
    private static final long TEMPO = 2000;
    private int i,l=1,x=0, pt_totalizzati=0, pt_max;
    private TextView text, tempoRimanente;
    TimerTask timerTask;
    double time=TIME;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        // findViewById
        img_1=findViewById(R.id.img1);
        img_2=findViewById(R.id.img2);
        img_3=findViewById(R.id.img3);
        img_4=findViewById(R.id.img4);
        img_5=findViewById(R.id.img5);
        img_6=findViewById(R.id.img6);
        immagine2=findViewById(R.id.immagine2);
        text=findViewById(R.id.textView);
        tempoRimanente=findViewById(R.id.tempo);
        b = findViewById(R.id.button);

        riempiGalleria();

        // Set pulsanti non cliccabili
        img_1.setClickable(false);
        img_2.setClickable(false);
        img_3.setClickable(false);
        img_4.setClickable(false);
        img_5.setClickable(false);
        img_6.setClickable(false);

        // Imposta le immagini nelle View
        img_1.setImageBitmap(galleria.get(0));
        img_2.setImageBitmap(galleria.get(1));
        img_3.setImageBitmap(galleria.get(2));
        img_4.setImageBitmap(galleria.get(3));

        gioco.add(galleria.get(0)); gioco.add(galleria.get(1));
        gioco.add(galleria.get(2)); gioco.add(galleria.get(3));

        d = Home.getDiff();
        switch (d)
        {
            // Imposta la visibilità delle ImageView, del pulsante e i punti massimi totalizzabili
            case FACILE:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.INVISIBLE);
                img_5.setVisibility(View.INVISIBLE);
                img_6.setVisibility(View.INVISIBLE);
                pt_max=3*LIV_MAX;
                break;
            case INTERMEDIO:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.VISIBLE);
                img_5.setImageBitmap(galleria.get(4));
                gioco.add(galleria.get(4));
                img_5.setVisibility(View.INVISIBLE);
                img_6.setVisibility(View.INVISIBLE);
                pt_max=4*LIV_MAX;
                b.setVisibility(View.INVISIBLE);
                b.setClickable(false);
                timer=new Timer();
                startTimer();
            break;
            case AVANZATO:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.VISIBLE);
                img_5.setImageBitmap(galleria.get(4));
                img_6.setImageBitmap(galleria.get(5));
                gioco.add(galleria.get(4));
                gioco.add(galleria.get(5));
                img_5.setVisibility(View.INVISIBLE);
                img_6.setVisibility(View.INVISIBLE);
                b.setVisibility(View.INVISIBLE);
                b.setClickable(false);
                pt_max=4*LIV_MAX;
                time=TIME*2/3;
                timer=new Timer();
                startTimer();
                break;
        }
        Collections.shuffle(gioco);
    }

    // Riempe l'ArrayList galleria
    public void riempiGalleria()
    {
        Bitmap banana = (BitmapFactory.decodeResource(getResources(), R.drawable.banana));
        Bitmap carro = (BitmapFactory.decodeResource(getResources(), R.drawable.carro));
        Bitmap elicottero = (BitmapFactory.decodeResource(getResources(), R.drawable.elicottero));
        Bitmap tigre = (BitmapFactory.decodeResource(getResources(), R.drawable.tigre));
        Bitmap estate = (BitmapFactory.decodeResource(getResources(), R.drawable.estate));
        Bitmap inverno = (BitmapFactory.decodeResource(getResources(), R.drawable.inverno));
        Bitmap volpe = (BitmapFactory.decodeResource(getResources(), R.drawable.volpe));
        Bitmap melograno = (BitmapFactory.decodeResource(getResources(), R.drawable.melagrana));
        Bitmap leone = (BitmapFactory.decodeResource(getResources(), R.drawable.leone));
        Bitmap carota = (BitmapFactory.decodeResource(getResources(), R.drawable.carota));
        Bitmap moto = (BitmapFactory.decodeResource(getResources(), R.drawable.moto));
        Bitmap pantera = (BitmapFactory.decodeResource(getResources(), R.drawable.pantera));
        Bitmap lupo = (BitmapFactory.decodeResource(getResources(), R.drawable.lupo));
        Bitmap gallina = (BitmapFactory.decodeResource(getResources(), R.drawable.gallina));
        Bitmap mela = (BitmapFactory.decodeResource(getResources(), R.drawable.mela));
        Bitmap pera = (BitmapFactory.decodeResource(getResources(), R.drawable.pera));
        Bitmap primavera = (BitmapFactory.decodeResource(getResources(), R.drawable.primavera));
        Bitmap quad = (BitmapFactory.decodeResource(getResources(), R.drawable.quad));
        Bitmap barca = (BitmapFactory.decodeResource(getResources(), R.drawable.barca));
        Bitmap camper = (BitmapFactory.decodeResource(getResources(), R.drawable.camper));
        Bitmap camion = (BitmapFactory.decodeResource(getResources(), R.drawable.camion));
        giusto= (BitmapFactory.decodeResource(getResources(),R.drawable.giusto));
        sbagliato= (BitmapFactory.decodeResource(getResources(),R.drawable.sbaglio));

        galleria.add(banana); galleria.add(carro); galleria.add(elicottero);galleria.add(tigre);galleria.add(estate);galleria.add(inverno);galleria.add(volpe);galleria.add(melograno);galleria.add(leone);galleria.add(carota);galleria.add(moto);galleria.add(pantera);galleria.add(lupo);galleria.add(gallina);galleria.add(mela);galleria.add(pera);galleria.add(primavera);galleria.add(quad);galleria.add(barca);galleria.add(camion);galleria.add(camper);
        Collections.shuffle(galleria);
    }

    // Passa dalla modalità di memorizzazione a quella di selezione delle immagini memorizzate
    @SuppressLint("SetTextI18n")
    public void avanti(View v)
    {
        b.setVisibility(View.INVISIBLE);
        b.setClickable(false);
        img_1.setClickable(true);
        img_2.setClickable(true);
        img_3.setClickable(true);
        img_4.setClickable(true);

        // Cambia le immagini
        img_1.setImageBitmap(gioco.get(0));
        img_2.setImageBitmap(gioco.get(1));
        img_3.setImageBitmap(gioco.get(2));
        img_4.setImageBitmap(gioco.get(3));

        // Cambia il testo
        text.setText("SELEZIONA LE IMMAGINI MOSTRATE IN PRECEDENZA");

        switch (d)
        {
            // rende visibili e cliccabili + ImageView
            case FACILE:
                img_4.setVisibility(View.VISIBLE);
                break;
            case INTERMEDIO:
                img_5.setVisibility(View.VISIBLE);
                img_5.setClickable(true);
                img_5.setImageBitmap(gioco.get(4));
                break;
            case AVANZATO:
                img_5.setVisibility(View.VISIBLE);
                img_6.setVisibility(View.VISIBLE);
                img_5.setClickable(true);
                img_6.setClickable(true);
                img_5.setImageBitmap(gioco.get(4));
                img_6.setImageBitmap(gioco.get(5));
                break;
        }
    }

    // Controlla la correttezza della risposta data dall'utrente
    @SuppressLint("NonConstantResourceId")
    public void  click(View v){
        switch (d) {
            case FACILE:
            switch (v.getId()) {
                case R.id.img1:
                    if (gioco.get(0)!= galleria.get(3)){
                        img_1.setClickable(false);
                        img_1.setImageBitmap(giusto);
                        i++;
                    }
                    else{
                        img_1.setImageBitmap(sbagliato);
                        errore();;
                    }
                    break;
                case R.id.img2:
                    if (gioco.get(1)!= galleria.get(3)){
                        img_2.setClickable(false);
                        img_2.setImageBitmap(giusto);
                        i++;}  else{
                        img_2.setImageBitmap(sbagliato);
                        errore();}
                    break;
                case R.id.img3:
                    if (gioco.get(2)!= galleria.get(3)){
                        img_3.setClickable(false);
                        img_3.setImageBitmap(giusto);
                        i++;}  else{
                        img_3.setImageBitmap(sbagliato);
                        errore();}
                    break;
                case R.id.img4:
                    if (gioco.get(3)!= galleria.get(3)){
                        img_4.setClickable(false);
                        img_4.setImageBitmap(giusto);
                        i++;}  else{
                        img_4.setImageBitmap(sbagliato);
                        errore();}
                    break;

            }
                if (i==3){

                    mp = MediaPlayer.create(this,R.raw.giusto);
                    immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.giusto));
                    if(volume){ mp.start();}
                    animaImmagineEsito();
                    restart();
                }
        break;

            case INTERMEDIO:
                switch (v.getId()) {

                    case R.id.img1:
                        if (gioco.get(0)!= galleria.get(4)){
                            img_1.setClickable(false);
                        img_1.setImageBitmap(giusto);
                            i++;}  else{
                            img_1.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img2:
                        if (gioco.get(1)!= galleria.get(4)){
                            img_2.setClickable(false);
                        img_2.setImageBitmap(giusto);
                            i++;}  else{
                            img_2.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img3:
                        if (gioco.get(2)!= galleria.get(4)){
                            img_3.setClickable(false);
                        img_3.setImageBitmap(giusto);
                            i++;}  else{
                            img_3.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img4:
                        if (gioco.get(3)!= galleria.get(4)){
                            img_4.setClickable(false);
                        img_4.setImageBitmap(giusto);
                            i++;}  else{
                            img_4.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img5:
                        if (gioco.get(4)!= galleria.get(4)){
                            img_5.setClickable(false);
                        img_5.setImageBitmap(giusto);
                            i++;}  else{
                            img_5.setImageBitmap(sbagliato);
                            errore();}
                        break;
                }
                if (i==4){
                    mp = MediaPlayer.create(this,R.raw.giusto);
                    immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.giusto));
                    if(volume){ mp.start();}
                    animaImmagineEsito();
                    restart();
                }

                break;

            case AVANZATO:
                switch (v.getId()) {

                    case R.id.img1:
                        if (gioco.get(0)!= galleria.get(5)&&gioco.get(0)!= galleria.get(4)){
                            img_1.setClickable(false);
                            img_1.setImageBitmap(giusto);
                            i++;}  else{
                            img_1.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img2:
                        if (gioco.get(1)!= galleria.get(5)&&gioco.get(1)!= galleria.get(4)){
                            img_2.setClickable(false);
                            img_2.setImageBitmap(giusto);
                            i++;}  else{
                            img_2.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img3:
                        if (gioco.get(2)!= galleria.get(5)&&gioco.get(2)!= galleria.get(4)){
                            img_3.setClickable(false);
                            img_3.setImageBitmap(giusto);
                            i++;}  else{
                            img_3.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img4:
                        if (gioco.get(3)!= galleria.get(5)&&gioco.get(3)!= galleria.get(4)){
                            img_4.setClickable(false);
                            img_4.setImageBitmap(giusto);
                            i++;}  else{
                            img_4.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img5:
                        if (gioco.get(4)!= galleria.get(5)&&gioco.get(4) != galleria.get(4)){
                            img_5.setClickable(false);
                            img_5.setImageBitmap(giusto);
                            i++;}  else{
                            img_5.setImageBitmap(sbagliato);
                            errore();}
                        break;
                    case R.id.img6:
                        if (gioco.get(5) != galleria.get(5)&&gioco.get(5) != galleria.get(4)){
                            img_6.setClickable(false);
                            img_6.setImageBitmap(giusto);
                            i++;}  else{
                            img_6.setImageBitmap(sbagliato);
                            errore();}
                        break;
                }
                if (i==4){

                    mp = MediaPlayer.create(this,R.raw.giusto);
                    immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.giusto));
                    if(volume){ mp.start();}
                    animaImmagineEsito();
                    restart();
                }
                break;
        }
    }

    // Quando sbagli
    public void errore(){
        // suono
        mp = MediaPlayer.create(this,R.raw.errore);
        // immagine
        immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sbaglio));
        // riproduci suono se volume "ON"
        if(volume){ mp.start();}
        animaImmagineEsito();
        x++; // aumenta errori
        pt_max++; // aumenta punti massimi
        if(d!=Difficoltà.AVANZATO||x==2){
            // se difficoltà diversa da AVANZATO o errori uguali a 2 si passa al livello successivo
            restart();
        }
    }

    // aumenta il livello
    @SuppressLint("SetTextI18n")
    public void restart(){

        // immagini non cliccabili
        img_1.setClickable(false);
        img_2.setClickable(false);
        img_3.setClickable(false);
        img_4.setClickable(false);
        img_5.setClickable(false);
        img_6.setClickable(false);

        pt_totalizzati+=i; // aumenta i punti totalizzati
        i=0; // set risposte giuste a 0
        x=0; // set errori a 0
        gioco.clear();
        l++; // aumenta il livello
        time = TIME;
        Collections.shuffle(galleria);

        // qui ci mette le immagini
        img_1.setImageBitmap(galleria.get(0));
        img_2.setImageBitmap(galleria.get(1));
        img_3.setImageBitmap(galleria.get(2));
        img_4.setImageBitmap(galleria.get(3));

        switch (d)
        {
            // Cosa si vede e cosa no nel livello
            case FACILE:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.INVISIBLE);
                img_5.setVisibility(View.INVISIBLE);
                img_6.setVisibility(View.INVISIBLE);
                b.setVisibility(View.VISIBLE);
                b.setClickable(true);
                break;
            case INTERMEDIO:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.VISIBLE);
                img_5.setImageBitmap(galleria.get(4));
                gioco.add(galleria.get(4));
                img_5.setVisibility(View.GONE);
                img_6.setVisibility(View.GONE);
                timer=new Timer();
                startTimer();
                break;
            case AVANZATO:
                img_1.setVisibility(View.VISIBLE);
                img_2.setVisibility(View.VISIBLE);
                img_3.setVisibility(View.VISIBLE);
                img_4.setVisibility(View.VISIBLE);
                img_5.setImageBitmap(galleria.get(4));
                img_6.setImageBitmap(galleria.get(5));
                gioco.add(galleria.get(4));
                gioco.add(galleria.get(5));
                img_5.setVisibility(View.GONE);
                img_6.setVisibility(View.GONE);
                time=TIME*2/3;
                timer=new Timer();
                startTimer();
                break;
        }
        gioco.add(galleria.get(0)); gioco.add(galleria.get(1));
        gioco.add(galleria.get(2)); gioco.add(galleria.get(3));
        Collections.shuffle(gioco);

        // cambia il testo
        text.setText("MEMORIZZA LE SEGUENTI IMMAGINI");

        if(l==LIV_MAX){
            // Se la partita è terminata porta all'Activity "Risultati.java"
            Intent intent= new Intent(Memory.this,Risultati.class);
            startActivity(intent);
            // passa alla nuova Activity i punti totalizzati, i punti massimi e la classe
            Risultati.setPunti_massimi(pt_max);
            Risultati.setPunti_totalizzati(pt_totalizzati);
            Risultati.setCls(Memory.class);
            finish();
        }
    }

    // Codice timer
    public void startTimer(){
        timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        tempoRimanente.setText(gettimertext());
                        Log.d("Timer", String.valueOf(time));
                        if(time==0.0)
                        {
                            avanti(b);
                            stopTimer();
                            tempoRimanente.setText("");
                        }
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);
    }

    // ferma il timer se c'è
    public void stopTimer(){
        if(d!= Difficoltà.FACILE)
        {
            timer.cancel();
        }
    }

    // restituisce una stringa con i secondi del timer
    public String gettimertext(){
        int raunded=(int) Math.round(time);
        int seconds=((raunded%86400)%3600)%60;
        return formatTime (seconds);
    }

    // restituisce una stringa contenente uno spazio e il numero di secondi
    public String formatTime(int seconds){
        return String.format("%02d",seconds);
    }

    // Cambia il volume da  "ON" a "OFF" e vice versa
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

    // Effettua la rotazione dell'immagine per mostrare l'esito della risposta all'utente
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

    // Torna all'Activity precedente
    public void indietro(View v)
    {
        Intent intent = new Intent(Memory.this,SelectEsercizi.class);
        startActivity(intent);
        stopTimer();
        finish();
    }

    // Torna all'Activity "Home.java"
    public void home(View v)
    {
        Intent intent = new Intent(Memory.this,Home.class);
        startActivity(intent);
        stopTimer();
        finish();
    }
}