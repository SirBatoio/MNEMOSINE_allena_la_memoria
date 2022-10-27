package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class Associazioni extends AppCompatActivity {

    private static final String IMAGEVIEW_TAG = "icon bitmap";
private Bitmap a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associazioni);
        ImageView imageView = new ImageView(this);
        a= (BitmapFactory.decodeResource(getResources(),R.drawable.sbaglio));
        imageView.setImageBitmap(a);
        imageView.setTag(IMAGEVIEW_TAG);
        imageView.setOnLongClickListener( v -> {

        
        }
        }
}