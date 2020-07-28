package com.example.petagram.model;

import android.content.ContentValues;
import android.content.Context;

import com.example.petagram.db.BaseDatos;
import com.example.petagram.db.ConstantesBaseDatos;

import java.util.ArrayList;


/**
 * Created by anahisalgado on 21/04/16.
 */
public class ConstructorAnimales{

    private static final int LIKE = 1;
    private Context context;
    public ConstructorAnimales(Context context) {
        this.context = context;
    }

    public ArrayList<Animal> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerTodosLosAnimales();
    }



    public void insertarAnimal(BaseDatos db, Animal animal){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_ANIMALS_NAME, animal.getNameDog());
        contentValues.put(ConstantesBaseDatos.TABLE_ANIMALS_IMAGE, animal.getImageDog());
        contentValues.put(ConstantesBaseDatos.TABLE_ANIMALS_LIKE, animal.isLike());

        db.insertarAnimal(contentValues);
    }

    public void darLikeAnimal(Animal animal){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL, animal.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMBER_LIKES, LIKE);
        db.insertarLikeAnimal(contentValues);
    }

    public int obtenerLikesAnimal(Animal animal){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesAnimal(animal);
    }


}
