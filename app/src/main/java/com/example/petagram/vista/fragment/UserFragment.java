package com.example.petagram.vista.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapter.UserRecyclerViewAdapter;
import com.example.petagram.model.Animal;

import java.util.ArrayList;
import java.util.Random;

public class UserFragment extends Fragment {

    private UserRecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Animal> animalNames;
    private Random random = new Random();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // put animals names and icons y animalNames
        setAnimalNames();

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.animals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new UserRecyclerViewAdapter(getActivity(), animalNames);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(recyclerViewAdapter);


        return view;

    }




    private void setAnimalNames(){
        animalNames = new ArrayList<>();

        int image = 0;

        for (int i = 0; i < 12; i++){
            animalNames.add(new Animal(getImageDog(image), "", randomRate(10)));
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