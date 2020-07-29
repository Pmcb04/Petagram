package com.example.petagram.restApi.deserializador;


import com.example.petagram.model.Animal;
import com.example.petagram.restApi.JsonKeys;
import com.example.petagram.restApi.model.AnimalResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactoDeserializador implements JsonDeserializer<AnimalResponse> {
    @Override
    public AnimalResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        AnimalResponse animalResponse = gson.fromJson(json, AnimalResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        animalResponse.setContactos(deserializarContactoDeJson(contactoResponseData));
        return animalResponse;
    }

    private ArrayList<Animal> deserializarContactoDeJson(JsonArray contactoResponseData){
        ArrayList<Animal> animales = new ArrayList<>();
        for (int i = 0; i < contactoResponseData.size() ; i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson            = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Animal animalActual = new Animal();
            animalActual.setId(Integer.parseInt(id));
            animalActual.setNameDog(nombreCompleto);
            animalActual.setImageDogURL(urlFoto);
            animalActual.setRateDog(likes);

            animales.add(animalActual);

        }

        return animales;
    }
}
