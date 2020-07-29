package com.example.petagram.restApi.adapter;

import com.example.petagram.restApi.ConstantesRestApi;
import com.example.petagram.restApi.EndpointsApi;
import com.example.petagram.restApi.deserializador.AnimalDeserializador;
import com.example.petagram.restApi.model.AnimalResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AnimalResponse.class, new AnimalDeserializador());
        return gsonBuilder.create();
    }
}
