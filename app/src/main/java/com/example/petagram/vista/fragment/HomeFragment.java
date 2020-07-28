package com.example.petagram.vista.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapter.HomeRecyclerViewAdapter;
import com.example.petagram.model.Animal;
import com.example.petagram.db.*;
import com.example.petagram.model.ConstructorAnimales;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {


    private ArrayList<Animal> animalNames;
    private ConstructorAnimales constructorAnimal;
    private BaseDatos db;
    private Random random = new Random();
    private String[] namesDog = {
            "Laika", "Dexter", "Niebla", "Scooby", "Lola"
    };

    public HomeFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        constructorAnimal = new ConstructorAnimales(getActivity());
        db = new BaseDatos(getActivity());

       // animalNames = getData(db);

        // put animals names and icons y animalNames
        setAnimalNames();

        for (Animal animal: animalNames)
            System.out.println(animal.toString());
        System.out.println("AQUI");
        System.out.println("NULL" + animalNames.isEmpty());

        // put animals in data base
        setData();

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.animals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeRecyclerViewAdapter recyclerViewAdapter = new HomeRecyclerViewAdapter(getActivity(), animalNames);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;

    }

    private void setData() {
        for (Animal animal: animalNames)
            constructorAnimal.insertarAnimal(db, animal);

    }

    private ArrayList<Animal> getData(BaseDatos db){
        return db.obtenerTodosLosAnimales();
    }


    private void setAnimalNames(){
        animalNames = new ArrayList<>();

        int image = 0;

        for (String name : namesDog){
            animalNames.add(new Animal(getImageDog(image), name, randomRate(10)));
            image++;

        }
    }

    private int randomRate(int max){
        return random.nextInt(max);
    }

    private int getImageDog(int index){

        switch (index){

            case 0:
                return R.drawable.dog1;

            case 1:
                return R.drawable.dog2;

            case 2:
                return R.drawable.dog3;

            case 3:
                return R.drawable.dog4;

            case 4:
                return R.drawable.dog5;

        }
        return R.drawable.dog1;
    }

}