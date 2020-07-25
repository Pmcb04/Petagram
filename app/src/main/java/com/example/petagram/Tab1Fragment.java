package com.example.petagram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class Tab1Fragment extends Fragment {


    private MyRecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Animal> animalNames;
    private Random random = new Random();
    private String[] namesDog = {
            "Laika", "Dexter", "Niebla", "Scooby", "Lola"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        // put animals names and icons y animalNames
        setAnimalNames();

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.animals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(), animalNames);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;

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