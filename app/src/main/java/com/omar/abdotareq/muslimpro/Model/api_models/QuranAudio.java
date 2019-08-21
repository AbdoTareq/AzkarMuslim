package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

public class QuranAudio {

    @SerializedName("identifier")
    private String identifier;

    @SerializedName("language")
    private String language;

    @SerializedName("name")
    private String name;

    @SerializedName("englishName")
    private String englishName;

    @SerializedName("format")
    private String format;

    @SerializedName("type")
    private String type;

    @SerializedName("languageName")
    private String languageName;

    public QuranAudio(String identifier, String language, String name, String englishName, String format, String type) {
        this.identifier = identifier;
        this.language = language;
        this.name = name;
        this.englishName = englishName;
        this.format = format;
        this.type = type;
        setLanguageName();
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

    public String getLanguageName() {
        setLanguageName();
        return languageName;
    }

    private void setLanguageName() {
        switch (language) {
            case "ar":
                languageName = "Arabic";
                break;
            case "en":
                languageName = "English";
                break;
            case "fa":
                languageName = "Persian";
                break;
            case "ur":
                languageName = "Urdu";
                break;
            case "zh":
                languageName = "Chinese";
                break;
            case "fr":
                languageName = "French";
                break;
        }
    }
}
