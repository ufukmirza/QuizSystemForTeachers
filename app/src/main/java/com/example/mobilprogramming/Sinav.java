package com.example.mobilprogramming;

public class Sinav {
   private int sinavSuresi;
   private int soruPuani;
   private int zorlukDuzeyi;

    public Sinav(int sinavSuresi, int soruPuani, int zorlukDuzeyi) {
        this.sinavSuresi = sinavSuresi;
        this.soruPuani = soruPuani;
        this.zorlukDuzeyi = zorlukDuzeyi;
    }

    public int getSinavSuresi() {
        return sinavSuresi;
    }

    public void setSinavSuresi(int sinavSuresi) {
        this.sinavSuresi = sinavSuresi;
    }

    public int getSoruPuani() {
        return soruPuani;
    }

    public void setSoruPuani(int soruPuani) {
        this.soruPuani = soruPuani;
    }

    public int getZorlukDuzeyi() {
        return zorlukDuzeyi;
    }

    public void setZorlukDuzeyi(int zorlukDuzeyi) {
        this.zorlukDuzeyi = zorlukDuzeyi;
    }
}
