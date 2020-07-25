package com.example.petagram;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public static ArrayList<Animal> favorites = new ArrayList<>();
    public static int numberFavorites = 0;
    public static TextView fav;
    private ImageView star;
    private Toolbar toolbar;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing components
        initComponents();


        fav.setText(numberFavorites + "");

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                intent.putParcelableArrayListExtra("list", favorites);
                startActivity(intent);
            }
        });



        //Adding toolbar to the activity
        addToolbar();

        // Adding icons in tabs
        addIconsTabLayout();
        
    }

    private void initComponents(){
        viewPager = findViewById(R.id.pager);
        fav = findViewById(R.id.fav);
        star = findViewById(R.id.star);
        tabLayout = findViewById(R.id.tabLayout);
        toolbar = findViewById(R.id.toolbar);
    }

    private void addToolbar(){
        setSupportActionBar(toolbar);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new Tab1Fragment(), "");
        tabAdapter.addFragment(new Tab2Fragment(), "");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void addIconsTabLayout(){

        ArrayList<Integer> icons = new ArrayList<>();

        icons.add(R.drawable.home_icon);
        icons.add(R.drawable.dog_icon);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(icons.get(i));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_acerca:
                intent = new Intent(MainActivity.this, BiographyActivity.class);
                startActivity(intent);
                break;
            case R.id.action_contact:
                intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                break;
            default:
                System.out.println("Estoy en default");
        }

        return super.onOptionsItemSelected(item);


    }
}
