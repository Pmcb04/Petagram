package com.example.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petagram.model.Animal;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 04/05/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaAnimal = "CREATE TABLE " + ConstantesBaseDatos.TABLE_ANIMALS + "(" +
                ConstantesBaseDatos.TABLE_ANIMALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_ANIMALS_NAME + " TEXT NOT NULL, " +
                ConstantesBaseDatos.TABLE_ANIMALS_IMAGE + " INTEGER NOT NULL, " +
                ConstantesBaseDatos.TABLE_ANIMALS_LIKE + " INTEGER DEFAULT 0 NOT NULL" +
                ")";


        String queryCrearTablaLikesAnimal = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL + "(" +
                ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMBER_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_ANIMALS + "(" + ConstantesBaseDatos.TABLE_ANIMALS_ID + ")" +
                ")";


        db.execSQL(queryCrearTablaAnimal);
        db.execSQL(queryCrearTablaLikesAnimal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_ANIMALS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL);
        onCreate(db);
    }

    public ArrayList<Animal> obtenerTodosLosAnimales() {
        ArrayList<Animal> animales = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_ANIMALS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Animal contactoActual = new Animal();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setImageDog(registros.getInt(1));
            contactoActual.setNameDog(registros.getString(2));
            contactoActual.setLike(registros.getInt(3) != 0);


            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMBER_LIKES+") as likes " +
                                " FROM " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL + "=" + contactoActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                contactoActual.setRateDog(registrosLikes.getInt(0));
            }else {
                contactoActual.setRateDog(0);
            }

            animales.add(contactoActual);

        }

        db.close();

        return animales;
    }

    public void insertarAnimal(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_ANIMALS,null, contentValues);
        db.close();
    }

    public void insertarLikeAnimal(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_ANIMAL, null, contentValues);
        db.close();
    }


    public int obtenerLikesAnimal(Animal animal){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMBER_LIKES+")" +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL + "="+animal.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}