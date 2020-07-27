package com.example.petagram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.petagram.Animal;


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
        String queryCrearTablaAnimal = "CREATE TABLE " + ConstantesBaseDatos.TABLE_ANIMAL + " ("
                + ConstantesBaseDatos.TABLE_ANIMAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLE_ANIMAL_IMAGE + " INTEGER, "
                + ConstantesBaseDatos.TABLE_ANIMAL_NOMBRE + " TEXT"
                + ")";

        String queryCrearTablaLikesAnimal = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL + "(" +
                ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID_ANIMAL + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_ANIMAL + "(" + ConstantesBaseDatos.TABLE_ANIMAL_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaAnimal);
        db.execSQL(queryCrearTablaLikesAnimal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_ANIMAL);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL);
        onCreate(db);
    }

    public ArrayList<Animal> obtenerTodosLosAnimales() {
        ArrayList<Animal> animales = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_ANIMAL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Animal animal = new Animal();
            animal.setId(registros.getInt(0));
            animal.setNameDog(registros.getString(1));
            animal.setImageDog(Integer.parseInt(registros.getString(2)));
            animal.setLike(Boolean.parseBoolean(registros.getString(3)));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMERO_LIKES + ") as likes " +
                                " FROM " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_ANIMAL_ID + "=" + animal.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                animal.setLike(registrosLikes.getInt(0) != 0);
                animal.setRateDog(registrosLikes.getInt(0));
            }else {
                animal.setLike(false);
                animal.setRateDog(0);
            }

            animales.add(animal);

        }

        db.close();

        return animales;
    }

    public void insertarAnimal(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_ANIMAL,null, contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_ANIMAL, null, contentValues);
        db.close();
    }


    public int obtenerLikesAnimal(Animal animal){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_ANIMAL_NUMERO_LIKES+")" +
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