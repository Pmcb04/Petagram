package com.example.petagram.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petagram.R;
import com.example.petagram.adapter.TabPageAdapter;
import com.example.petagram.model.Animal;
import com.example.petagram.vista.fragment.HomeFragment;
import com.example.petagram.vista.fragment.UserFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public static ArrayList<Animal> favorites;
    public static int numberFavorites = 0;
    public static TextView fav;
    private static String user_insta;
    private ImageView star;
    private Toolbar toolbar;
    private TabPageAdapter tabPageAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int INSTAGRAM = 0;


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
        favorites = new ArrayList<>();
    }

    private void addToolbar(){
        setSupportActionBar(toolbar);

        tabPageAdapter = new TabPageAdapter(getSupportFragmentManager());
        tabPageAdapter.addFragment(new HomeFragment(), "");
        tabPageAdapter.addFragment(new UserFragment(), "");

        viewPager.setAdapter(tabPageAdapter);
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
            case R.id.action_user:
                intent = new Intent(MainActivity.this, InstagramActivity.class);
                startActivityForResult(intent, INSTAGRAM);
                break;
            default:
                System.out.println("Estoy en default");
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == INSTAGRAM) && (resultCode == RESULT_OK)) {
            user_insta = data.getDataString();
            System.out.println(user_insta);
        }
    }


}

