package com.example.petagram.vista.fragment;

import com.example.petagram.adapter.UserRecyclerViewAdapter;
import com.example.petagram.model.Animal;

import java.util.ArrayList;

public interface IRecyclerViewUser {

    void generarLinearLayoutVertical();

    void generarGridLayout();

    UserRecyclerViewAdapter crearAdaptador(ArrayList<Animal> animales);

    void inicializarAdaptadorRV(UserRecyclerViewAdapter adaptador);
}
