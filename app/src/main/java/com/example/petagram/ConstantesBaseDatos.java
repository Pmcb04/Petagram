package com.example.petagram;

/**
 * Created by anahisalgado on 04/05/16.
 */
public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "animales";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_ANIMAL          = "animal";
    public static final String TABLE_ANIMAL_ID        = "id";
    public static final String TABLE_ANIMAL_NOMBRE    = "nombre";
    public static final String TABLE_ANIMAL_IMAGE     = "image";
    public static final String TABLE_ANIMAL_LIKE     = "like";

    public static final String TABLE_LIKES_ANIMAL = "animal_likes";
    public static final String TABLE_LIKES_ANIMAL_ID = "id";
    public static final String TABLE_LIKES_ANIMAL_ID_ANIMAL = "id_animal";
    public static final String TABLE_LIKES_ANIMAL_NUMERO_LIKES = "numero_likes";
}
