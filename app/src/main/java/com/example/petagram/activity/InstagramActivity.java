package com.example.petagram.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.petagram.R;
import com.google.android.material.snackbar.Snackbar;

public class InstagramActivity extends AppCompatActivity {

    TextView user_insta;
    Button save_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_instagram);

        user_insta = findViewById(R.id.name_user_instagram);
        save_user = findViewById(R.id.save_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        save_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cad = user_insta.getText().toString();

                if(!cad.isEmpty()){
                    Intent data = new Intent();
                    data.setData(Uri.parse(cad));
                    setResult(RESULT_OK, data);
                    finish();

                }else{
                    Snackbar.make(view, R.string.error_user, Snackbar.LENGTH_SHORT)
                            .show();

                }
            }
        });



    }
}