package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimpleSurah {

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

    @SerializedName("minAya")
    private int minAya;

    @SerializedName("maxAya")
    private int maxAya;

    public SimpleSurah(int number, String name, String englishName, String englishNameTranslation, String revelationType, int minAya, int maxAya) {
        this.number = number;
        this.name = name;
        this.englishName = englishName;
        this.englishNameTranslation = englishNameTranslation;
        this.revelationType = revelationType;
        this.minAya = minAya;
        this.maxAya = maxAya;
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

    public int getMinAya() {
        return minAya;
    }

    public int getMaxAya() {
        return maxAya;
    }
}
