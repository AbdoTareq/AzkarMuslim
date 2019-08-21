package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Surah {

    @SerializedName("number")
    private int number;

    @SerializedName("name")
    private String name;

    @SerializedName("englishName")
    private String englishName;

    @SerializedName("englishNameTranslation")
    private String englishNameTranslation;

    @SerializedName("revelationType")
    private String revelationType;

    @SerializedName("numberOfAyahs")
    private int numberOfAyahs;

    @SerializedName("ayahs")
    private List<Aya> ayahs;

    public Surah(int number, String name, String englishName, String englishNameTranslation, String revelationType, int numberOfAyahs, List<Aya> ayahs) {
        this.number = number;
        this.name = name;
        this.englishName = englishName;
        this.englishNameTranslation = englishNameTranslation;
        this.revelationType = revelationType;
        this.numberOfAyahs = numberOfAyahs;
        this.ayahs = ayahs;
    }

    public Surah(int number, String name, String englishName, String englishNameTranslation, String revelationType, List<Aya> ayahs) {
        this.number = number;
        this.name = name;
        this.englishName = englishName;
        this.englishNameTranslation = englishNameTranslation;
        this.revelationType = revelationType;
        this.ayahs = ayahs;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    public String getRevelationType() {
        return revelationType;
    }

    public int getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public List<Aya> getAyahs() {
        return ayahs;
    }
}
