package com.omar.abdotareq.muslimpro.model.api_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfQuranAudios {

    @SerializedName("data")
    List<QuranAudio> data;

    public ListOfQuranAudios(List<QuranAudio> data) {
        this.data = data;
    }

    public List<QuranAudio> getData() {
        return data;
    }
}
