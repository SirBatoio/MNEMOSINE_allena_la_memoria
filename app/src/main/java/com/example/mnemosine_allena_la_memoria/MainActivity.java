package com.example.mnemosine_allena_la_memoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimerTask timerTask;
    double time=0.0;
    Timer timer;

    private ArrayList<Button> pulsanti = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    timer=new Timer();

    }

    public void gioca(View v)
    {
        Intent intent= new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        finish();
    }


    public void startTimer(){
        timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        Log.d(gettimertext());
                    }
                });

            }
        };
    timer.scheduleAtFixedRate
    }

    public String gettimertext(){
        int raunded=(int) Math.round(time);
        int seconds=((raunded%86400)%3600)%60;
        return formatTime (seconds);
    }

}