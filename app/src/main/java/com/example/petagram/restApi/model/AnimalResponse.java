package com.example.petagram.restApi.model;

import com.example.petagram.model.Animal;

import java.util.ArrayList;

public class AnimalResponse {

    ArrayList<Animal> animales;

    public ArrayList<Animal> getAnimales() {
        return animales;
    }

    public void setContactos(ArrayList<Animal> animales) {
        this.animales = animales;
    }
}
