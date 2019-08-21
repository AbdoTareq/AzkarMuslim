package com.omar.abdotareq.muslimpro.activities.api_activities;

import com.omar.abdotareq.muslimpro.model.api_models.ListOfQuranAudios;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface QuranController {

    /**
     * A method to get all available quran audio editions
     */
    @GET("edition/format/audio")
    Call<ListOfQuranAudios> getAllAudio();

    /**
     * A method to get the passed aya stream (mp3)
     */
    @GET("/media/audio/ayah/{identifier}/{number}")
    @Streaming
    Call<ResponseBody> getAyaAudio(@Path("identifier") String identifier
            , @Path("number") int number);
}
