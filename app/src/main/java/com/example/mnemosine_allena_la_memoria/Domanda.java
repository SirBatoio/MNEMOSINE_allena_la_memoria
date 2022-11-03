package com.example.mnemosine_allena_la_memoria;

public class Domanda {
    private String testo, rispostaGiusta, rispostaSbagliata1, rispostaSbagliata2;

    public Domanda(String testo, String rispostaGiusta, String rispostaSbagliata1, String rispostaSbagliata2) {
        this.testo = testo;
        this.rispostaGiusta = rispostaGiusta;
        this.rispostaSbagliata1 = rispostaSbagliata1;
        this.rispostaSbagliata2 = rispostaSbagliata2;
    }

    public Domanda(String testo, String rispostaGiusta) {
        this.testo = testo;
        this.rispostaGiusta = rispostaGiusta;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getRispostaGiusta() {
        return rispostaGiusta;
    }

    public void setRispostaGiusta(String rispostaGiusta) {
        this.rispostaGiusta = rispostaGiusta;
    }

    public String getRispostaSbagliata1() {
        return rispostaSbagliata1;
    }

    public void setRispostaSbagliata1(String rispostaSbagliata1) {
        this.rispostaSbagliata1 = rispostaSbagliata1;
    }

    public String getRispostaSbagliata2() {
        return rispostaSbagliata2;
    }

    public void setRispostaSbagliata2(String rispostaSbagliata2) {
        this.rispostaSbagliata2 = rispostaSbagliata2;
    }

}
