package com.example.petagram.vista.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapter.HomeRecyclerViewAdapter;
import com.example.petagram.model.Animal;
import com.example.petagram.presentador.IRecylerViewFragmentPresenter;
import com.example.petagram.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 20/04/16.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {
    private ArrayList<Animal> contactos;
    private RecyclerView recyclerViewHome;
    private IRecylerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewHome = (RecyclerView) v.findViewById(R.id.animals);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }



    /*
    public void inicializarDatos(){
        contactos = new ArrayList<>();
        contactos.add(new Contacto(R.drawable.candy_icon, "Anahi Salgado", "77779999", "anahi@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.yammi_banana_icon, "Pedro Sanchez", "88882222", "pedro@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.shock_rave_bonus_icon, "Mireya Lopez", "33331111", "mireya@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.forest_mushroom_icon, "Juan Lopez", "44442222", "juan@gmail.com", likes));

    }*/



    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewHome.setLayoutManager(llm);

    }

    @Override
    public HomeRecyclerViewAdapter crearAdaptador(ArrayList<Animal> animales) {
        return new HomeRecyclerViewAdapter(getActivity(), animales);
    }

    @Override
    public void inicializarAdaptadorRV(HomeRecyclerViewAdapter adaptador) {
        recyclerViewHome.setAdapter(adaptador);
    }
}
