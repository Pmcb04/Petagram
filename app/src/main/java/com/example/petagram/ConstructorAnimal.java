
package com.example.petagram;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.example.petagram.Animal;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by anahisalgado on 21/04/16.
 */
public class ConstructorAnimal{

    private static final int LIKE = 1;
    private Context context;
    public ConstructorAnimal(Context context) {
        this.context = context;
    }

    public ArrayList<Animal> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerTodosLosAnimales();
    }


    public void insertarAnimal(BaseDatos db, Animal animal){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_ANIMAL_IMAGE, animal.getImageDog());
        contentValues.put(ConstantesBaseDatos.TABLE_ANIMAL_NOMBRE, animal.getNameDog());
       // contentValues.put(ConstantesBaseDatos.TABLE_ANIMAL_LIKE, animal.isLike());

        db.insertarAnimal(contentValues);
    }

    public void darLikeAnimal(Animal animal){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL, animal.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }

    public int obtenerLikesContacto(Animal animal){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesAnimal(animal);
    }


}
