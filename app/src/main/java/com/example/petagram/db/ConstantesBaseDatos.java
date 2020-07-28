package com.example.petagram.db;

/**
 * Created by anahisalgado on 04/05/16.
 */
public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "contactos";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_ANIMALS          = "animal";
    public static final String TABLE_ANIMALS_ID        = "id";
    public static final String TABLE_ANIMALS_NAME    = "nombre";
    public static final String TABLE_ANIMALS_IMAGE  = "image";
    public static final String TABLE_ANIMALS_LIKE     = "like";

    public static final String TABLE_LIKES_ANIMAL = "animal_likes";
    public static final String TABLE_LIKES_ANIMAL_ID = "id";
    public static final String TABLE_LIKES_ANIMAL_ID_ANIMAL = "id_animal";
    public static final String TABLE_LIKES_ANIMAL_NUMBER_LIKES = "numero_likes";
}
