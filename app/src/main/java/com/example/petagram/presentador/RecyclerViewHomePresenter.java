package com.example.petagram.presentador;

import android.content.Context;

import com.example.petagram.model.Animal;
import com.example.petagram.model.ConstructorAnimales;
import com.example.petagram.vista.fragment.IRecyclerViewHome;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewHomePresenter implements IRecylerViewHomePresenter {

    private IRecyclerViewHome iRecyclerViewHome;
    private Context context;
    private ConstructorAnimales constructorAnimales;
    private ArrayList<Animal> animales;

    public RecyclerViewHomePresenter(IRecyclerViewHome iRecyclerViewHome, Context context) {
        this.iRecyclerViewHome = iRecyclerViewHome;
        this.context = context;
        obtenerAnimales();
    }


    @Override
    public void obtenerAnimales() {
        constructorAnimales = new ConstructorAnimales(context);
        animales = constructorAnimales.obtenerDatos();
        mostrarAnimalesRV();
    }

    @Override
    public void mostrarAnimalesRV() {
        iRecyclerViewHome.inicializarAdaptadorRV(iRecyclerViewHome.crearAdaptador(animales));
        iRecyclerViewHome.generarLinearLayoutVertical();
    }
}
