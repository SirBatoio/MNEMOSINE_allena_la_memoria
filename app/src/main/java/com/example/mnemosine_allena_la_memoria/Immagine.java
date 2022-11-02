package com.example.mnemosine_allena_la_memoria;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Immagine {
    private Bitmap immagine;
    private String descrizione;
    private float rotazione;

    public void ruotaImageView(ImageView im)
    {
        im.setRotation(this.rotazione);
    }

    public float getRotazione() {
        return rotazione;
    }

    public void setRotazione(float rotazione) {
        this.rotazione = rotazione;
    }

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

    public Immagine(Bitmap immagine, float rotazione) {
        this.immagine = immagine;
        this.rotazione = rotazione;
    }

    public Immagine(Bitmap immagine, String descrizione) {
        this.immagine = immagine;
        this.descrizione = descrizione;
    }
}
