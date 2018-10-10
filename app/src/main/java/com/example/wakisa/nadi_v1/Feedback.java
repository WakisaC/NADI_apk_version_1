package com.example.wakisa.nadi_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.net.Uri;
import android.widget.TextView;

import java.net.URL;

public class Feedback extends AppCompatActivity {
    String fb ="";
    String linkedIn ="www.linkedin.com/in/nadi-android-application-a76575168/";
    String google ="";
    String twitter ="";
    String instagram ="";
    String email ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /////display a back button///////

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView fb = (TextView)findViewById(R.id.fb);
        fb.setMovementMethod(LinkMovementMethod.getInstance());

        TextView in = (TextView)findViewById(R.id.linked);
        in.setMovementMethod(LinkMovementMethod.getInstance());

        TextView google = (TextView)findViewById(R.id.google);
        google.setMovementMethod(LinkMovementMethod.getInstance());

        TextView mail = (TextView)findViewById(R.id.email);
        mail.setMovementMethod(LinkMovementMethod.getInstance());

        TextView youtube = (TextView)findViewById(R.id.youtube);
        youtube.setMovementMethod(LinkMovementMethod.getInstance());

        TextView twitt = (TextView)findViewById(R.id.twitter);
        twitt.setMovementMethod(LinkMovementMethod.getInstance());

        TextView insta = (TextView)findViewById(R.id.instagram);
        insta.setMovementMethod(LinkMovementMethod.getInstance());


    }

    public void facebook(View view){
        goToUrl (fb);

    }
    public void linkedIn(View view){
        goToUrl (linkedIn);

    }

    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW,uri);

        startActivity(launchBrowser);
    }

}
