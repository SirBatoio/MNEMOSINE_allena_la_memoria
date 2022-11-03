package com.example.mnemosine_allena_la_memoria;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Associazioni extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    // Create a string for the TextView and Button label
    private static final String TEXTVIEW_TAG = "DRAG TEXT";
    private static final long TEMPO = 2000;
    private TextView parola_1, parola_2, parola_3, parola_4, parola_5, parola_6, parola_7, parola_8, parola_9;
    private ImageView immagine_1, immagine_2, immagine_3, giudizio;
    private boolean volume = true;
    private final ArrayList<Immagine> galleria = new ArrayList<>();
    private final ArrayList<String> gioco = new ArrayList<>();
    private int i = 2, y = 0, t = 0, tentativi = 2, pt_totalizzati, pt_max;
    private boolean verdetto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associazioni);

        //find views by id
        parola_1 = findViewById(R.id.nome_1);
        parola_2 = findViewById(R.id.nome_2);
        parola_3 = findViewById(R.id.nome_3);
        parola_4 = findViewById(R.id.nome_4);
        parola_5 = findViewById(R.id.nome_5);
        parola_6 = findViewById(R.id.nome_6);
        parola_7 = findViewById(R.id.nome_7);
        parola_8 = findViewById(R.id.nome_8);
        parola_9 = findViewById(R.id.nome_9);
        giudizio = findViewById(R.id.imageView4);
        immagine_1 = findViewById(R.id.fig_1);
        immagine_2 = findViewById(R.id.fig_2);
        immagine_3 = findViewById(R.id.fig_3);

        // Sets the tag
        parola_1.setTag(TEXTVIEW_TAG);
        parola_2.setTag(TEXTVIEW_TAG);
        parola_3.setTag(TEXTVIEW_TAG);
        parola_4.setTag(TEXTVIEW_TAG);
        parola_5.setTag(TEXTVIEW_TAG);
        parola_6.setTag(TEXTVIEW_TAG);
        parola_7.setTag(TEXTVIEW_TAG);
        parola_8.setTag(TEXTVIEW_TAG);
        parola_9.setTag(TEXTVIEW_TAG);

        implementEvents();

        aggiungiImmagini();

        // Mescola galleria e aggiunge i primi tre elementi a gioco
        Collections.shuffle(galleria);
        gioco.add(galleria.get(0).getDescrizione());
        gioco.add(galleria.get(1).getDescrizione());
        gioco.add(galleria.get(2).getDescrizione());
        Collections.shuffle(gioco);

        // Imposta immagini e testi del primo round
        immagine_1.setImageBitmap(galleria.get(0).getImmagine());
        immagine_2.setImageBitmap(galleria.get(1).getImmagine());
        immagine_3.setImageBitmap(galleria.get(2).getImmagine());
        parola_1.setText(gioco.get(0));
        parola_2.setText(gioco.get(1));
        parola_3.setText(gioco.get(2));
    }

    //Implement long click and drag listener
    private void implementEvents() {

        parola_1.setOnLongClickListener(this);
        parola_2.setOnLongClickListener(this);
        parola_3.setOnLongClickListener(this);

        findViewById(R.id.top_left_layout).setOnDragListener(this);
        findViewById(R.id.top_center_layout).setOnDragListener(this);
        findViewById(R.id.top_right_layout).setOnDragListener(this);
    }

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        // Defines a variable to store the action type for the incoming event
        int action = dragEvent.getAction();

        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                // if you want to apply color when drag started to your view you can uncomment below lines
                // to give any color tint to the View to indicate that it can accept
                // data.
                //view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                // view.invalidate();
                // returns true to indicate that the View can accept the dragged data.
                return dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);

            // Returns false. During the current drag and drop operation, this View will
            // not receive events again until ACTION_DRAG_ENDED is sent.

            case DragEvent.ACTION_DRAG_ENTERED:

                // Applies a MAGENTA or any color tint to the View,
                // Return true; the return value is ignored.

                view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.

                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                view.getBackground().clearColorFilter();

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

                // Gets the item containing the dragged data
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();

                // Displays a message containing the dragged data.
                //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                // Turns off any color tints
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                //get dragged view
                View v = (View) dragEvent.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();
                owner.removeView(v); //remove the dragged view
                LinearLayout container = (LinearLayout) view; //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                container.addView(v); //Add the dragged view
                v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                controlloRisposte(v, container);

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // invoke getResult(), and displays what happened.
                /*if (dragEvent.getResult())
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();*/

                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View view) {

        // Create a new ClipData.Item from the tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data //data to be dragged
                , shadowBuilder //drag shadow
                , view //local data about the drag and drop operation
                , 0 //flags (not currently used, set to 0)
        );

        //Set view visibility to INVISIBLE as we are going to drag the view
        //view.setVisibility(View.INVISIBLE);

        return true;
    }

    // Dichiara e aggiunge le immagini a galleria
    public void aggiungiImmagini() {
        // Dichiarazione immagini
        Immagine primavera = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.primavera), "Primavera");
        Immagine estate = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.estate), "Estate");
        Immagine inverno = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.inverno), "Inverno");
        Immagine autunno = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.autunno), "Autunno");
        Immagine melagrana = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.melagrana), "Melagrana");
        Immagine patata = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.patata), "Patata");
        Immagine pantera = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.pantera), "Pantera");
        Immagine anguria = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.anguria), "Anguria");
        Immagine orologio = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.sette15), "Orologio");
        Immagine carota = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.carota), "Carota");
        Immagine bussola = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.bussola), "Bussola");
        Immagine camion = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.camion), "Camion");
        Immagine lupo = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.lupo), "Lupo");
        Immagine volpe = new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.volpe), "Volpe");

        // Aggiunta a galleria
        galleria.add(primavera);
        galleria.add(estate);
        galleria.add(inverno);
        galleria.add(autunno);
        galleria.add(melagrana);
        galleria.add(patata);
        galleria.add(pantera);
        galleria.add(anguria);
        galleria.add(orologio);
        galleria.add(carota);
        galleria.add(bussola);
        galleria.add(camion);
        galleria.add(lupo);
        galleria.add(volpe);
    }

    // Controlla la correttezza della risposta data dall'utrente
    @SuppressLint("NonConstantResourceId")
    public void controlloRisposte(View v, LinearLayout container) {
        TextView tv = findViewById(v.getId());
        switch (container.getId()) {
            case R.id.top_left_layout:
                // Confronto del testo della TextView con la descrizione dell'immagine su cui è stato trascinato il testo
                if (tv.getText() == galleria.get(i - 2).getDescrizione()) {
                    // Rende la TextView invisibile
                    tv.setVisibility(View.GONE);
                    // Modifica l'immagine visualizzata con "giusto.png"
                    immagine_1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));
                    // Il layout non accetta ulteriori "drop" da parte dell'utente
                    container.setOnDragListener(null);
                    // Aumenta le risposte esatte e i punti totalizzati
                    y++;
                    pt_totalizzati++;
                    verdetto = true;
                } else {
                    // Diminuisce le risposte esatte e aumenta i punti massimi
                    tentativi--;
                    pt_max++;
                    verdetto = false;
                }
                break;
            case R.id.top_center_layout:
                if (tv.getText() == galleria.get(i - 1).getDescrizione()) {
                    tv.setVisibility(View.GONE);
                    immagine_2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));
                    container.setOnDragListener(null);
                    y++;
                    pt_totalizzati++;
                    verdetto = true;
                } else {
                    tentativi--;
                    pt_max++;
                    verdetto = false;
                }
                break;
            case R.id.top_right_layout:
                if (tv.getText() == galleria.get(i).getDescrizione()) {
                    tv.setVisibility(View.GONE);
                    immagine_3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));
                    container.setOnDragListener(null);
                    y++;
                    pt_totalizzati++;
                    verdetto = true;
                } else {
                    tentativi--;
                    pt_max++;
                    verdetto = false;
                }
                break;
        }
        pt_max++;
        MediaPlayer mp;
        if (verdetto) {
            mp = MediaPlayer.create(this, R.raw.giusto);
            giudizio.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.giusto));
        } else {
            mp = MediaPlayer.create(this, R.raw.errore);
            giudizio.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sbaglio));
        }
        if (volume) {
            mp.start();
        }
        animaImmagineEsito();
        if (y == 3 || tentativi == 0) {
            restart();
            tentativi = 2;
            y = 0;
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
        Intent intent = new Intent(Associazioni.this, SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    // Torna all'Activity "Home.java"
    public void home(View v) {
        Intent intent = new Intent(Associazioni.this, Home.class);
        startActivity(intent);
        finish();
    }

    // Modifica le immagini e i testi per il round successivo
    public void restart() {
        // Svuota gioco
        gioco.clear();

        // Implementa nuovamente la possibilità dei Layout di accettare "drop" dall'utente
        findViewById(R.id.top_left_layout).setOnDragListener(this);
        findViewById(R.id.top_center_layout).setOnDragListener(this);
        findViewById(R.id.top_right_layout).setOnDragListener(this);

        // Secondo round
        if (t == 0) {
            // Rende invisibili le TextView del round precedente qualora ce ne fossero alcune rimaste visibili
            parola_1.setVisibility(View.GONE);
            parola_2.setVisibility(View.GONE);
            parola_3.setVisibility(View.GONE);
            // Implementa long click listener alle nuove TextView
            parola_4.setOnLongClickListener(this);
            parola_5.setOnLongClickListener(this);
            parola_6.setOnLongClickListener(this);

            //aggiunge i prossimi membri di galleria a gioco
            i++;
            gioco.add(galleria.get(i).getDescrizione());
            immagine_1.setImageBitmap(galleria.get(i).getImmagine());

            i++;
            gioco.add(galleria.get(i).getDescrizione());
            immagine_2.setImageBitmap(galleria.get(i).getImmagine());

            i++;
            gioco.add(galleria.get(i).getDescrizione());
            immagine_3.setImageBitmap(galleria.get(i).getImmagine());

            Collections.shuffle(gioco);

            // Imposta il testo delle nuove TextView
            parola_4.setText(gioco.get(0));
            parola_5.setText(gioco.get(1));
            parola_6.setText(gioco.get(2));

        }

        // Terzo round
        if (t == 1) {
            parola_4.setVisibility(View.GONE);
            parola_5.setVisibility(View.GONE);
            parola_6.setVisibility(View.GONE);
            parola_7.setOnLongClickListener(this);
            parola_8.setOnLongClickListener(this);
            parola_9.setOnLongClickListener(this);
            i++;
            gioco.add(galleria.get(i).getDescrizione());
            immagine_1.setImageBitmap(galleria.get(i).getImmagine());

            i++;
            gioco.add(galleria.get(i).getDescrizione());
            immagine_2.setImageBitmap(galleria.get(i).getImmagine());

            i++;
            gioco.add(galleria.get(i).getDescrizione());
            immagine_3.setImageBitmap(galleria.get(i).getImmagine());

            Collections.shuffle(gioco);

            parola_7.setText(gioco.get(0));
            parola_8.setText(gioco.get(1));
            parola_9.setText(gioco.get(2));

        }
        if (t == 2) {
            Intent intent = new Intent(Associazioni.this, Risultati.class);
            startActivity(intent);
            Risultati.setPunti_totalizzati(pt_totalizzati);
            Risultati.setPunti_massimi(pt_max);
            Risultati.setCls(Associazioni.class);
            finish();

        }

        t++;
    }

    public void animaImmagineEsito() {
        if (giudizio.getRotationY() >= 180 && giudizio.getRotationY() < 360) {
            giudizio.animate().rotationY(90).setDuration(TEMPO);
        } else {
            giudizio.animate().rotationY(270).setDuration(TEMPO);
        }
    }
}