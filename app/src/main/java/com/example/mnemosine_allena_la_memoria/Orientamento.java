package com.example.mnemosine_allena_la_memoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Orientamento extends AppCompatActivity {

    private TextView difficolta;
    private Difficoltà d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientamento);
        d = MainActivity.getDiff();
        difficolta=findViewById(R.id.diffOr);
        difficolta.setText(String.valueOf(d).toLowerCase());
    }

    public void orientamentoTemporale(View v)
    {
        Intent intent = new Intent(Orientamento.this, OrientamentoTemporale.class);
        startActivity(intent);
    }

}