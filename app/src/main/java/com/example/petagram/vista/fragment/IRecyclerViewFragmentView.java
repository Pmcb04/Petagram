package com.example.petagram.vista.fragment;

import com.example.petagram.adapter.HomeRecyclerViewAdapter;
import com.example.petagram.model.Animal;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 21/04/16.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public HomeRecyclerViewAdapter crearAdaptador(ArrayList<Animal> contactos);

    public void inicializarAdaptadorRV(HomeRecyclerViewAdapter adaptador);
}
