package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

public class Animazioni extends AppCompatActivity {

    private static ImageView esitoGiusto, esitoSbagliato;
    private static MediaPlayer playGiusto, playSbagliato;
    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animazioni);

        esitoGiusto=findViewById(R.id.esitoG);
        esitoGiusto.setRotationY(90);
        esitoSbagliato=findViewById(R.id.esitoS);
        esitoSbagliato.setRotationY(90);

        playGiusto=MediaPlayer.create(this, R.raw.giusto);
        playSbagliato=MediaPlayer.create(this, R.raw.errore);
        activity=this;
    }

    public static void spin(boolean giusto, boolean volume)
    {
        if(giusto)
        {
            esitoGiusto.animate().rotationY(-270).setDuration(1000);
            if (volume)
            {
                playGiusto.start();
            }
        }
        else
        {
            esitoSbagliato.animate().rotationY(-270).setDuration(1000);
            if (volume)
            {
                playSbagliato.start();
            }
        }
    }

    public static void stop()
    {
        activity.finish();
    }

}