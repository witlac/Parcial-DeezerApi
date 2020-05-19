package com.example.parcial_api;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeezerApi {
    @GET("tracks")
    Call<SongResponse> getSong();

    @GET("search")
    Call<SongResponse> SearchSong( @Query("q") String query );

}
