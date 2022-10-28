package com.example.mnemosine_allena_la_memoria;

import android.graphics.Bitmap;

public class Immagine {
    private Bitmap immagine;
    private String descrizione;

    public Bitmap getImmagine() {
        return immagine;
    }

    public void setImmagine(Bitmap immagine) {
        this.immagine = immagine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Immagine(Bitmap immagine, String descrizione) {
        this.immagine = immagine;
        this.descrizione = descrizione;
    }
}
