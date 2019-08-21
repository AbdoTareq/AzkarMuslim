package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

public class Aya {


    @SerializedName("number")
    private int number;

    @SerializedName("text")
    private String text;

    @SerializedName("numberInSurah")
    private int numberInSurah;

    @SerializedName("juz")
    private int juz;

    @SerializedName("manzil")
    private int manzil;

    @SerializedName("page")
    private int page;

    @SerializedName("ruku")
    private int ruku;

    @SerializedName("hizbQuarter")
    private int hizbQuarter;

    @SerializedName("sajda")
    private boolean sajda;

    @SerializedName("surah")
    private Surah surah;

    public Aya(int number, String text, int numberInSurah, int juz, int manzil, int page, int ruku, int hizbQuarter, boolean sajda, Surah surah) {
        this.number = number;
        this.text = text;
        this.numberInSurah = numberInSurah;
        this.juz = juz;
        this.manzil = manzil;
        this.page = page;
        this.ruku = ruku;
        this.hizbQuarter = hizbQuarter;
        this.sajda = sajda;
        this.surah = surah;
    }

    public Aya(int number, String text, int numberInSurah, int juz, int manzil, int page, int ruku, int hizbQuarter, boolean sajda) {
        this.number = number;
        this.text = text;
        this.numberInSurah = numberInSurah;
        this.juz = juz;
        this.manzil = manzil;
        this.page = page;
        this.ruku = ruku;
        this.hizbQuarter = hizbQuarter;
        this.sajda = sajda;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumberInSurah() {
        return numberInSurah;
    }

    public void setNumberInSurah(int numberInSurah) {
        this.numberInSurah = numberInSurah;
    }

    public int getJuz() {
        return juz;
    }

    public void setJuz(int juz) {
        this.juz = juz;
    }

    public int getManzil() {
        return manzil;
    }

    public void setManzil(int manzil) {
        this.manzil = manzil;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRuku() {
        return ruku;
    }

    public void setRuku(int ruku) {
        this.ruku = ruku;
    }

    public int getHizbQuarter() {
        return hizbQuarter;
    }

    public void setHizbQuarter(int hizbQuarter) {
        this.hizbQuarter = hizbQuarter;
    }

    public boolean isSajda() {
        return sajda;
    }

    public void setSajda(boolean sajda) {
        this.sajda = sajda;
    }

    public Surah getSurah() {
        return surah;
    }

    public void setSurah(Surah surah) {
        this.surah = surah;
    }
}
