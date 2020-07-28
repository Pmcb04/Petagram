package com.example.petagram.presentador;

import android.content.Context;

import com.example.petagram.model.Animal;
import com.example.petagram.model.ConstructorAnimales;
import com.example.petagram.vista.fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorAnimales constructorAnimales;
    private ArrayList<Animal> contactos;

    public  RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerAnimales();
    }


    @Override
    public void obtenerAnimales() {
        constructorAnimales = new ConstructorAnimales(context);
        contactos = constructorAnimales.obtenerDatos();
        mostrarAnimalesRV();
    }

    @Override
    public void mostrarAnimalesRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
