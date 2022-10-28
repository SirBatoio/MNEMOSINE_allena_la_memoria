package com.example.mnemosine_allena_la_memoria;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Associazioni extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    private TextView parola_1,parola_2,parola_3;
    private ImageView immagine_1,immagine_2,immagine_3;
    private boolean volume=true;
    private Immagine primavera,estate,inverno,autunno,melagrana,patata,pantera,anguria,orologio,carota,bussola,camion,lupo,volpe;
    private ArrayList<Immagine> galleria;
    private ArrayList<String> gioco;


    // Create a string for the TextView and Button label
    private static final String TEXTVIEW_TAG = "DRAG TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associazioni);

        //find views by id
        parola_1 = findViewById(R.id.nome_1);
        parola_2 = findViewById(R.id.nome_2);
        parola_3 = findViewById(R.id.nome_3);

        immagine_1=findViewById(R.id.fig_1);
        immagine_2=findViewById(R.id.fig_2);
        immagine_3=findViewById(R.id.fig_3);

        // Sets the tag
        parola_1.setTag(TEXTVIEW_TAG);
        parola_2.setTag(TEXTVIEW_TAG);
        parola_3.setTag(TEXTVIEW_TAG);

        implementEvents();

        primavera=  new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.primavera),"Primavera");
        estate=  new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.estate), "Estate");
        inverno=  new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.inverno), "Inverno");
        autunno= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.autunno), "Autunno");
        melagrana= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.melagrana), "Melagrana");
        patata= new Immagine (BitmapFactory.decodeResource(getResources(), R.drawable.patata), "Patata");
        pantera= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.pantera), "Pantera");
        anguria= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.anguria), "Anguria");
        orologio= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.sette15), "Orologio");
        carota= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.carota), "Carota");
        bussola= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.bussola), "Bussola");
        camion=new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.camion), "Camion");
        lupo= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.lupo),"Lupo");
        volpe= new Immagine(BitmapFactory.decodeResource(getResources(), R.drawable.volpe),"Volpe");

        galleria=new ArrayList<>();
        gioco= new ArrayList<>();
        galleria.add(primavera); galleria.add(estate); galleria.add(inverno); galleria.add(autunno);
        galleria.add(melagrana); galleria.add(patata); galleria.add(pantera); galleria.add(anguria);
        galleria.add(orologio); galleria.add(carota); galleria.add(bussola); galleria.add(camion);
        galleria.add(lupo); galleria.add(volpe);

        Collections.shuffle(galleria);
        gioco.add(galleria.get(0).getDescrizione());
        gioco.add(galleria.get(1).getDescrizione());
        gioco.add(galleria.get(2).getDescrizione());

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
                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.
                    //view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                    // Invalidate the view to force a redraw in the new tint
                    // view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

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
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

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

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // invoke getResult(), and displays what happened.
                if (dragEvent.getResult())
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();


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
        Intent intent = new Intent(Associazioni.this,SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    public void home(View v)
    {
        Intent intent = new Intent(Associazioni.this,Home.class);
        startActivity(intent);
        finish();
    }

}