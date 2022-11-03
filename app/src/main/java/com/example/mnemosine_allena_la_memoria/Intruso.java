package com.example.mnemosine_allena_la_memoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collections;

public class Intruso extends AppCompatActivity {
    private Difficoltà diff;
    private ArrayList<Categoria> galleria = new ArrayList<>();
    private ArrayList<Categoria> tut = new ArrayList<>();
    private ArrayList<Categoria> animal = new ArrayList<>();
    private ArrayList<Categoria> transp = new ArrayList<>();
    private ArrayList<Categoria> fruit = new ArrayList<>();
    private ArrayList<Categoria> vestiti = new ArrayList<>();
    private ArrayList<Categoria> temp = new ArrayList<>();
    private ArrayList<Categoria> ruote=new ArrayList<>();
    private ArrayList<Categoria> noruote=new ArrayList<>();
    private ArrayList<Categoria> veggie=new ArrayList<>();
    private ArrayList<Categoria> mammiferi=new ArrayList<>();
    private ArrayList<Categoria> ovipari=new ArrayList<>();
    private ArrayList<Categoria> felini=new ArrayList<>();
    private ArrayList<Categoria> canidi=new ArrayList<>();
    private ArrayList<Categoria> biruote=new ArrayList<>();
    private ArrayList<Categoria> quarute=new ArrayList<>();
    private boolean volume=true;
    private ImageView Scelta_1;
    private ImageView Scelta_2;
    private ImageView Scelta_3;
    private ImageView Scelta_4;
    private ImageView immagine2;
    private static final long TEMPO = 2000;
    private MediaPlayer mp;
    private int l,i=0;
    private scelte s=scelte.animali;
    private sceltemed sm=sceltemed.mammiferi;
    private sceltadff sd=sceltadff.canidi;
    private int pt_totalizzati=0, pt_max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intruso);
        diff=Home.getDiff();
        Scelta_1 = findViewById(R.id.Scelta_1);
        Scelta_2 = findViewById(R.id.scelta_2);
        Scelta_3 = findViewById(R.id.Scelta_3);
        Scelta_4 = findViewById(R.id.scelta_4);
        immagine2=findViewById(R.id.immagine2);
        immagine2.animate().rotationY(90);

        riempiGallerie();

    }

    public void riempiGallerie()
    {
        Categoria mela = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.mela),scelte.frutta,sceltemed.altro, sceltadff.altro);
        Categoria pera = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.pera),scelte.frutta,sceltemed.altro, sceltadff.altro);
        Categoria banana = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.banana),scelte.frutta,sceltemed.altro, sceltadff.altro);
        Categoria patata = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.patata),scelte.altro,sceltemed.ortaggi,sceltadff.altro);
        Categoria rapa = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.turnip),scelte.altro,sceltemed.ortaggi, sceltadff.altro);
        Categoria carota = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.carota),scelte.altro,sceltemed.ortaggi, sceltadff.altro);
        Categoria bici = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.bici),scelte.mezzitras,sceltemed.ruote,sceltadff.biruote);
        Categoria auto = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.auto),scelte.mezzitras,sceltemed.ruote,sceltadff.quaruote);
        Categoria barca = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.barca),scelte.mezzitras,sceltemed.noruote,sceltadff.altro);
        Categoria cane = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.cane),scelte.animali,sceltemed.mammiferi,sceltadff.canidi);
        Categoria gatto = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.gatto),scelte.animali,sceltemed.mammiferi,sceltadff.felini);
        Categoria elefante = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.elefante),scelte.animali,sceltemed.mammiferi,sceltadff.altro);
        Categoria vestaut = new Categoria(BitmapFactory.decodeResource(getResources(),R.drawable.vestito_autunnale),scelte.vestiti,sceltemed.altro,sceltadff.altro);
        Categoria vestinv = new Categoria(BitmapFactory.decodeResource(getResources(),R.drawable.vestito_invernale),scelte.vestiti,sceltemed.altro,sceltadff.altro);
        Categoria vestest = new Categoria(BitmapFactory.decodeResource(getResources(),R.drawable.vestito_estivo),scelte.vestiti,sceltemed.altro,sceltadff.altro);
        Categoria vestprim = new Categoria(BitmapFactory.decodeResource(getResources(),R.drawable.vestito_primaverile),scelte.vestiti,sceltemed.altro,sceltadff.altro);
        Categoria moto = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.moto),scelte.mezzitras,sceltemed.ruote,sceltadff.biruote);
        Categoria camion = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.camion),scelte.mezzitras,sceltemed.ruote,sceltadff.altro);
        Categoria carro = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.carro),scelte.mezzitras,sceltemed.noruote,sceltadff.altro);
        Categoria elicottero = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.elicottero),scelte.mezzitras,sceltemed.noruote,sceltadff.altro);
        Categoria struzzo = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.struzzo),scelte.animali,sceltemed.ovipari,sceltadff.altro);
        Categoria airone = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.airone),scelte.animali,sceltemed.ovipari,sceltadff.altro);
        Categoria serpente = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.serpente),scelte.animali,sceltemed.ovipari,sceltadff.altro);
        Categoria gallina = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.gallina),scelte.animali,sceltemed.ovipari,sceltadff.altro);
        Categoria leone = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.leone),scelte.animali,sceltemed.mammiferi,sceltadff.felini);
        Categoria pantera = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.pantera),scelte.animali,sceltemed.mammiferi,sceltadff.felini);
        Categoria tigre = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.tigre),scelte.animali,sceltemed.mammiferi,sceltadff.felini);
        Categoria lupo = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.lupo),scelte.animali,sceltemed.mammiferi,sceltadff.canidi);
        Categoria volpe = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.volpe),scelte.animali,sceltemed.mammiferi,sceltadff.canidi);
        Categoria monopattino = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.monopattino),scelte.mezzitras,sceltemed.ruote,sceltadff.biruote);
        Categoria quad = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.quad),scelte.mezzitras,sceltemed.ruote,sceltadff.quaruote);
        Categoria camper = new Categoria(BitmapFactory.decodeResource(getResources(), R.drawable.camper),scelte.mezzitras,sceltemed.ruote,sceltadff.quaruote);
        Categoria melagrana=new Categoria(BitmapFactory.decodeResource(getResources(),R.drawable.melagrana),scelte.frutta,sceltemed.altro,sceltadff.altro);

        fruit.add(mela); fruit.add(pera); fruit.add(banana);fruit.add(melagrana);
        vestiti.add(vestest); vestiti.add(vestaut); vestiti.add(vestinv);vestiti.add(vestprim);
        transp.add(bici); transp.add(auto); transp.add(barca);transp.add(moto);transp.add(camion);transp.add(elicottero);transp.add(carro);transp.add(monopattino);transp.add(quad);transp.add(camper);
        animal.add(cane); animal.add(gatto); animal.add(elefante);animal.add(struzzo);animal.add(airone);animal.add(serpente);animal.add(gallina);animal.add(leone);animal.add(pantera);animal.add(tigre);animal.add(lupo);animal.add(volpe);
        ruote.add(moto);ruote.add(bici);ruote.add(auto);ruote.add(camion);ruote.add(monopattino);ruote.add(quad);ruote.add(camper);
        noruote.add(barca);noruote.add(carro);noruote.add(elicottero);
        veggie.add(carota);veggie.add(patata);veggie.add(rapa);
        mammiferi.add(elefante);mammiferi.add(cane);mammiferi.add(gatto);mammiferi.add(leone);mammiferi.add(tigre);mammiferi.add(lupo);mammiferi.add(volpe);
        ovipari.add(struzzo);ovipari.add(airone);ovipari.add(serpente);ovipari.add(gallina);
        felini.add(gatto);felini.add(leone);felini.add(pantera);felini.add(tigre);
        canidi.add(cane);canidi.add(lupo);canidi.add(volpe);
        biruote.add(bici);biruote.add(moto);biruote.add(monopattino);
        quarute.add(quad);quarute.add(auto);quarute.add(camper);
        galleryredo(s);
    }

   public void indietro(View v)
    {
        Intent intent = new Intent(Intruso.this,SelectEsercizi.class);
        startActivity(intent);
        finish();
    }

    public void home(View v)
    {
        Intent intent = new Intent(Intruso.this,Home.class);
        startActivity(intent);
        finish();
    }

    private void galleryredo(scelte s){

        tut.clear();
        galleria.clear();
        temp.clear();
        switch (diff) {
            case FACILE:
                switch (s) {
                    case animali:
                        tut.addAll(fruit);
                        tut.addAll(transp);
                        tut.addAll(vestiti);
                        temp.addAll(animal);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case frutta:
                        tut.addAll(animal);
                        tut.addAll(transp);
                        tut.addAll(vestiti);
                        temp.addAll(fruit);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case mezzitras:
                        tut.addAll(animal);
                        tut.addAll(fruit);
                        tut.addAll(vestiti);
                        temp.addAll(transp);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case vestiti:
                        tut.addAll(animal);
                        tut.addAll(transp);
                        tut.addAll(fruit);
                        temp.addAll(vestiti);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                }
                break;

            case INTERMEDIO:
                switch (sm) {
                    case mammiferi:
                        tut.addAll(ovipari);
                        temp.addAll(mammiferi);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case ovipari:
                        tut.addAll(mammiferi);
                        temp.addAll(ovipari);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case ruote:
                        tut.addAll(noruote);
                        temp.addAll(ruote);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case noruote:
                        tut.addAll(ruote);
                        temp.addAll(noruote);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case ortaggi:
                        tut.addAll(fruit);
                        temp.addAll(veggie);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                }
                break;

            case AVANZATO:
                switch (sd) {
                    case canidi:
                        tut.addAll(felini);
                        temp.addAll(canidi);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case felini:
                        tut.addAll(canidi);
                        temp.addAll(felini);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case biruote:
                        tut.addAll(quarute);
                        temp.addAll(biruote);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                    case quaruote:
                        tut.addAll(biruote);
                        temp.addAll(quarute);
                        Collections.shuffle(temp);
                        Collections.shuffle(tut);
                        galleria.add(temp.get(2));
                        galleria.add(temp.get(1));
                        galleria.add(temp.get(0));
                        break;
                }
                break;
        }
        galleria.add(tut.get(0));
        Collections.shuffle(galleria);
        Scelta_1.setImageBitmap(galleria.get(0).getImg());
        Scelta_2.setImageBitmap(galleria.get(1).getImg());
        Scelta_3.setImageBitmap(galleria.get(2).getImg());
        Scelta_4.setImageBitmap(galleria.get(3).getImg());
    }

    public void Controllo(View v)
    {
        boolean as=false;
        switch (diff) {
            case FACILE:
                switch (v.getId()) {
                    case R.id.Scelta_1: {
                        if (galleria.get(0).getPossibilita() != s) {
                            as = true;
                        }

                        break;
                    }
                    case R.id.scelta_2: {
                        if (galleria.get(1).getPossibilita() != s) {
                            as = true;
                        }
                        break;
                    }
                    case R.id.Scelta_3: {
                        if (galleria.get(2).getPossibilita() != s) {
                            as = true;
                        }
                        break;
                    }
                    case R.id.scelta_4: {
                        if (galleria.get(3).getPossibilita() != s) {
                            as = true;
                        }
                        break;
                    }
                }
                break;
            case INTERMEDIO:
                switch (v.getId()) {
                    case R.id.Scelta_1: {
                        if (galleria.get(0).getProbabilita() != sm) {
                            as = true;
                        }

                        break;
                    }
                    case R.id.scelta_2: {
                        if (galleria.get(1).getProbabilita() != sm) {
                            as = true;
                        }
                        break;
                    }
                    case R.id.Scelta_3: {
                        if (galleria.get(2).getProbabilita() != sm) {
                            as = true;
                        }
                        break;
                    }
                    case R.id.scelta_4: {
                        if (galleria.get(3).getProbabilita() != sm) {
                            as = true;
                        }
                        break;
                    }
                }

                break;
            case AVANZATO:
                switch (v.getId()) {
                    case R.id.Scelta_1: {
                        if (galleria.get(0).getFato() != sd) {
                            as = true;
                        }

                        break;
                    }
                    case R.id.scelta_2: {
                        if (galleria.get(1).getFato() != sd) {
                            as = true;
                        }
                        break;
                    }
                    case R.id.Scelta_3: {
                        if (galleria.get(2).getFato() != sd) {
                            as = true;
                        }
                        break;
                    }
                    case R.id.scelta_4: {
                        if (galleria.get(3).getFato() != sd) {
                            as = true;
                        }

                        break;
                    }
                }
                break;
        }
        if (as){
            mp = MediaPlayer.create(this,R.raw.giusto);
            immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.giusto));
            levelup();
            pt_totalizzati++;
        }else {mp = MediaPlayer.create(this,R.raw.errore);
            immagine2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sbaglio));
        pt_max++;
        }
        if(volume){ mp.start();}
        animaImmagineEsito();
    }

    public void levelup(){
        i++;
        switch (diff) {

            case FACILE:
                if (i==6){
                    Intent intent = new Intent(Intruso.this, Risultati.class);
                    startActivity(intent);
                    Risultati.setPunti_totalizzati(pt_totalizzati);
                    Risultati.setPunti_massimi(pt_max);
                    Risultati.setCls(Intruso.class);
                    finish();
                }
                switch (s) {
                    case animali:
                        s = scelte.mezzitras;
                        break;
                    case mezzitras:
                        s = scelte.frutta;
                        break;
                    case vestiti:
                        s = scelte.animali;
                        break;
                    case frutta:
                        s = scelte.vestiti;
                        break;
                }
                break;
            case INTERMEDIO:
                if (i==8){
                    Intent intent = new Intent(Intruso.this, Risultati.class);
                    startActivity(intent);
                    Risultati.setPunti_totalizzati(pt_totalizzati);
                    Risultati.setPunti_massimi(pt_max);
                    Risultati.setCls(Intruso.class);
                    finish();
                }
                switch (sm) {
                    case mammiferi:
                        sm = sceltemed.ortaggi;
                        break;
                    case ortaggi:
                        sm = sceltemed.noruote;
                        break;
                    case ovipari:
                        sm = sceltemed.ruote;
                        break;
                    case noruote:
                        sm = sceltemed.ovipari;
                        break;
                    case ruote:
                        sm=sceltemed.mammiferi;
                        break;
                }
                break;
            case AVANZATO:
                if (i==6){
                    Intent intent = new Intent(Intruso.this, Risultati.class);
                    startActivity(intent);
                    Risultati.setPunti_totalizzati(pt_totalizzati);
                    Risultati.setPunti_massimi(pt_max);
                    Risultati.setCls(Intruso.class);
                    finish();
                }
                switch (sd) {
                    case biruote:
                        sd = sceltadff.canidi;
                        break;
                    case canidi:
                        sd = sceltadff.quaruote;
                        break;
                    case quaruote:
                        sd = sceltadff.felini;
                        break;
                    case felini:
                        sd = sceltadff.biruote;
                        break;
                }
                break;
        }
        galleryredo(s);
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
}
