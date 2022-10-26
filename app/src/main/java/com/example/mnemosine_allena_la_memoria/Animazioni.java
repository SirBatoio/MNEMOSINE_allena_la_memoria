package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

public class Animazioni extends AppCompatActivity {

    private static ImageView esito;
    private static Bitmap immagineGiusto, immagineSbagliato;
    private static MediaPlayer playGiusto, playSbagliato;
    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animazioni);

        esito=findViewById(R.id.esito);
        esito.setRotationY(90);

        immagineGiusto=BitmapFactory.decodeResource(getResources(), R.drawable.giusto);
        immagineSbagliato=BitmapFactory.decodeResource(getResources(), R.drawable.sbaglio);

        playGiusto=MediaPlayer.create(this, R.raw.giusto);
        playSbagliato=MediaPlayer.create(this, R.raw.errore);
        activity=this;
    }

    public static void spin(boolean giusto, boolean volume)
    {
        if(giusto)
        {
            esito.setImageBitmap(immagineGiusto);
            if (volume)
            {
                playGiusto.start();
            }
        }
        else
        {
            esito.setImageBitmap(immagineSbagliato);
            if (volume)
            {
                playSbagliato.start();
            }
        }

        esito.animate().rotationY(-270).setDuration(1000);
    }

    public static void stop()
    {
        activity.finish();
    }

}