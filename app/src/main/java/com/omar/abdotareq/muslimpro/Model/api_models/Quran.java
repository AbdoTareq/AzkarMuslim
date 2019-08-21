package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quran {


    @SerializedName("surahs")
    private List<Surah> surahs ;

    @SerializedName("identifier")
    private String identifier ;

    @SerializedName("language")
    private String language ;

    @SerializedName("name")
    private String name ;

    @SerializedName("englishName")
    private String englishName ;

    @SerializedName("format")
    private String format ;

    @SerializedName("type")
    private String type ;

    public Quran(List<Surah> surahs, String identifier, String language, String name, String englishName, String format, String type) {
        this.surahs = surahs;
        this.identifier = identifier;
        this.language = language;
        this.name = name;
        this.englishName = englishName;
        this.format = format;
        this.type = type;
    }

    public List<Surah> getSurahs() {
        return surahs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getFormat() {
        return format;
    }

    public String getType() {
        return type;
    }
}
