package com.example.petagram.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petagram.R;

public class BiographyActivity extends AppCompatActivity {

    private ImageView instagram, linkedln, twitter, github;
    private String URL_INSTAGRAM = "instagram.com/pmcb04/";
    private String URL_LINKEDLN = "linkedin.com/in/pedromiguelcarmona/";
    private String URL_TWITTER = "twitter.com/pmcb04";
    private String URL_GITHUB = "github.com/Pmcb04";
    private boolean type_back = true; // true is back activity and false is back in WebView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_biography);

        instagram = findViewById(R.id.instagram);
        linkedln = findViewById(R.id.linkedln);
        twitter = findViewById(R.id.twitter);
        github = findViewById(R.id.github);

        Toolbar toolbar = findViewById(R.id.toolbar);
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