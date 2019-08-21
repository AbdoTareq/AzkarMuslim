package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimpleQuran {

    @SerializedName("simpleSurahs")
    List<SimpleSurah> simpleSurahs;

    public SimpleQuran(List<SimpleSurah> simpleSurahs) {
        this.simpleSurahs = simpleSurahs;
    }

    public List<SimpleSurah> getSimpleSurahs() {
        return simpleSurahs;
    }
}
