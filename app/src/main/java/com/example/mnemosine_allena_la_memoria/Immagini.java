package com.example.mnemosine_allena_la_memoria;

import android.graphics.Bitmap;

public class Immagini {
    private Bitmap img;
    private scelte possibilita;
    private sceltemed probabilita;
    private sceltadff fato;

    public sceltemed getProbabilita() { return probabilita; }
    public sceltadff getFato() { return fato; }
    public void setProbabilita(sceltemed probabilita) { this.probabilita = probabilita; }
    public void setFato(sceltadff fato) { this.fato = fato; }
    public Bitmap getImg() {
        return img;
    }
    public void setImg(Bitmap img) {
        this.img = img;
    }
    public scelte getPossibilita() {
        return possibilita;
    }
    public void setPossibilita(scelte possibilita) {
        this.possibilita = possibilita;
    }

    public Immagini(Bitmap img, scelte possibilita, sceltemed probabilita, sceltadff fato) {
        this.img = img;
        this.possibilita = possibilita;
        this.probabilita=probabilita;
        this.fato=fato;
    }
}
