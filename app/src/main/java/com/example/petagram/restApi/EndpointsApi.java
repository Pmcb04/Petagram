package com.example.petagram.restApi;

import com.example.petagram.restApi.model.AnimalResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<AnimalResponse> getRecentMedia();
}
