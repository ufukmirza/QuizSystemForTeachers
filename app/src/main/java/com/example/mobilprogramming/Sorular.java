package com.example.mobilprogramming;

import java.io.Serializable;

public class Sorular implements Serializable {

    private String soru;
    private String cevap1;
    private String cevap2;
    private String cevap3;
    private String cevap4;
    private String dogru;
    private String zorluk;



    public Sorular(String soru, String cevap1, String cevap2, String cevap3, String cevap4, String dogru,String zorluk) {

        this.soru = soru;
        this.cevap1 = cevap1;
        this.cevap2 = cevap2;
        this.cevap3 = cevap3;
        this.cevap4 = cevap4;
        this.dogru = dogru;
        this.zorluk=zorluk;
    }


    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap1() {
        return cevap1;
    }

    public void setCevap1(String cevap1) {
        this.cevap1 = cevap1;
    }
    public String getCevap2() {
        return cevap2;
    }

    public void setCevap2(String cevap2) {
        this.cevap2 = cevap2;
    }
    public String getCevap3() {
        return cevap3;
    }

    public void setCevap3(String cevap3) {
        this.cevap3 = cevap3;
    }
    public String getCevap4() {
        return cevap4;
    }

    public void setCevap4(String cevap4) {
        this.cevap4 = cevap4;
    }

    public String getDogru() {
        return dogru;
    }

    public void setDogru(String dogru) {
        this.dogru = dogru;
    }

    public String getZorluk() {
        return zorluk;
    }

    public void setZorluk(String zorluk) {
        this.zorluk = zorluk;
    }


}
