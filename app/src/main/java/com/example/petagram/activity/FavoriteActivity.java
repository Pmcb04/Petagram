package com.example.petagram.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.petagram.adapter.HomeRecyclerViewAdapter;
import com.example.petagram.R;
import com.example.petagram.model.Animal;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity  {

    HomeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_favorite);

        ArrayList<Animal> favorites = getIntent().getParcelableArrayListExtra("list");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.animals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeRecyclerViewAdapter(this, favorites);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onSupportNavigateUp();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;

    }
}