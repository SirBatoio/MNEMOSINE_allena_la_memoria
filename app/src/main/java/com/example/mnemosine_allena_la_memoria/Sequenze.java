package com.example.mnemosine_allena_la_memoria;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
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

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



public class Sequenze extends AppCompatActivity {
    private ImageView seq_1, seq_2, seq_3,risp_1,risp_2,risp_3;
    private Bitmap melagrana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequenze);

        seq_1=findViewById(R.id.seq_1);
        seq_2=findViewById(R.id.seq_2);
        seq_3=findViewById(R.id.seq_3);
        risp_1=findViewById(R.id.risp_1);
        risp_2=findViewById(R.id.risp_2);
        risp_3=findViewById(R.id.risp_3);

        melagrana = (BitmapFactory.decodeResource(getResources(), R.drawable.melagrana));


        seq_1.setImageBitmap(melagrana);
        seq_1.setRotation(180);
    }
}