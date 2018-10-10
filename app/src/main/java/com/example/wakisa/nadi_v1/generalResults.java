package com.example.wakisa.nadi_v1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class generalResults extends AppCompatActivity {


    EditText edoedema,edweight,edheight,edwasting,edmuac,ID,BCN;
    String server_url ="http://192.168.43.57/nadi/login.php";
    AlertDialog.Builder builder;

    ///// weight and height values from assessment
    double height,weight;
    String v0,v1,v2,v3,v4,v5,v6,v7,bcn;
    EditText childId;

 //////// SQLITE  Variables//////////
    DatabaseHelper myDb;
    Button saveBut,weightAdvice ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //////// create an SQLITE database object/////////
        myDb = new DatabaseHelper(this);
        saveBut=(Button)findViewById(R.id.savedata);

        /// display assessment results/////
       displayResults();

        /////add data to local phone database using SQLITE///////////
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            checkInput_saveData();

            }
        });

}
    public void displayResults(){

    builder = new AlertDialog.Builder(generalResults.this);
        final TextView view0 = (TextView)findViewById(R.id.text1);////view for oedema
        final TextView view1 = (TextView)findViewById(R.id.text2);/////view for weight
        final TextView view2 = (TextView)findViewById(R.id.text3);////view for height
        final TextView view3 = (TextView)findViewById(R.id.text4);//////view for wasting
        final TextView view4 = (TextView)findViewById(R.id.text5);/////view for muac

    /////GET CHILDS BIRTH CERTIFICATE NUMBER
    BCN = (EditText)findViewById(R.id.bcn);
    BCN.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    bcn = BCN.getText().toString();
    final String bcnCheck = bcn;

    //////////set text into the table rows
    Intent i = getIntent();
    v0= i.getStringExtra("oedemaID");
    v1= i.getStringExtra("weightID");
    v2= i.getStringExtra("heightID");
    v3= i.getStringExtra("wastingID");
    v4= i.getStringExtra("muacID");
    v5= i.getStringExtra("weightValueID");
    v6= i.getStringExtra("heightValueID");
    weight = i.getDoubleExtra("weightDouble",0);
    height = i.getDoubleExtra("heightDouble",0);

    ///////set text on the text views/////
    view0.setText(v0);
    view1.setText(v1);
    view2.setText(v2);
    view3.setText(v3);
    view4.setText(v4);

}

    public void checkInput_saveData(){

    builder = new AlertDialog.Builder(generalResults.this);

    /////GET CHILDS BIRTH CERTIFICATE NUMBER
    BCN = (EditText)findViewById(R.id.bcn);
    bcn = BCN.getText().toString();
    final String bcnCheck = bcn;

    //////////set text into the table rows
    Intent i = getIntent();
    v0= i.getStringExtra("oedemaID");
    v1= i.getStringExtra("weightID");
    v2= i.getStringExtra("heightID");
    v3= i.getStringExtra("wastingID");
    v4= i.getStringExtra("muacID");
    v5= i.getStringExtra("weightValueID");
    v6= i.getStringExtra("heightValueID");
    v7= i.getStringExtra("monthsTotal"); ///////////total age in months only

        ///////////Convert the string values to integer and double respectively to be used in the addData function
        int V7  =Integer.parseInt(v7);
        double V5=Double.parseDouble(v5);
        double V6=Double.parseDouble(v6);

    weight = i.getDoubleExtra("weightDouble",0);
    height = i.getDoubleExtra("heightDouble",0);

    if (TextUtils.isEmpty(bcn)){
        builder.setTitle("Error Response");
        builder.setMessage("Enter Birth Certificate Number!! " );
        builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    else{
        addData(bcn,V7,V5,V6,v3,v0,v4);

    }
}
    ///////////////////SQLITE    Functions//////////

    public void addData( final String bcn,final int months, final double weight, final double height, final String wasting, final String oedema, final String muac){

        boolean isInserted = myDb.insertData( bcn,months, weight, height,wasting, oedema, muac);
                if (isInserted=true){
                     builder.setTitle("Server Response");
                     builder.setMessage("Response : Data saved successfully " );
                     builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                        ///// go to general assessment///////
                        gotoGeneralAssessment();
                       }
                     });
                     AlertDialog alertDialog = builder.create();
                     alertDialog.show();
                 }
                 else {
                    builder.setTitle("Server Response");
                    builder.setMessage("Response : An Error occured!! Try again " );
                    builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }



        }
    public void gotoGeneralAssessment(){
        Intent intent=new Intent(this,general.class);
        startActivity(intent);
    }
    public void gotoStuntingAdvice(){
        Intent intent=new Intent(this,stuntingAdvice.class);
        startActivity(intent);
    }
    public void gotoUnderweightAdvice(){
        Intent intent=new Intent(this,underweightAdvice.class);
        startActivity(intent);
    }
    public void gotoOverweight(){
        Intent intent=new Intent(this,overweightAdvice.class);
        startActivity(intent);
    }
    public void gotoWastingAdvice(){
        Intent intent=new Intent(this,wastingAdvice.class);
        startActivity(intent);
    }
    public void gotoOvernourishedAdvice(){
        Intent intent=new Intent(this,overnourishedAdvice.class);
        startActivity(intent);
    }
    public String getWeight(){
        Intent i = getIntent();
        String weight =i.getStringExtra("weightID");

        return weight;
    }
    public String getHeight(){
        Intent i = getIntent();
        String height =i.getStringExtra("heightID");

        return height;
    }
    public String getWasting(){
        Intent i = getIntent();
        String wasting =i.getStringExtra("wastingID");

        return wasting;
    }
    public void weightAdviceOnClick(View view){

        String weight = getWeight();

                if (weight.equals("Underweight")){
                    gotoUnderweightAdvice();
                }
                else if (weight.equals("Overweight")){
                    gotoOverweight();

                }
                else  {
                    builder.setTitle("Normal Child");
                    builder.setMessage("Continue feeding the child with a wide range of healthy foods " );
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
    public void heightAdviceOnClick(View view){

        String height = getHeight();

        if (height.equals("Stunted child")){
            gotoStuntingAdvice();
        }
        else if (height.equals("Tall child")){
            builder.setTitle("Tall Child");
            builder.setMessage("Sorry the child is Tall. Feed the Child with a wide range of healthy foods.We don't have height reducing measures" );
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        else  {
            builder.setTitle("Normal Child");
            builder.setMessage("Continue feeding the child with a wide range of healthy foods " );
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    public void wastingAdviceOnClick(View view){

        String wasting = getWasting();

        if (wasting.equals("Overnourished")){
            gotoOvernourishedAdvice();
        }
        else if (wasting.equals("Wasted")){
            gotoWastingAdvice();

        }
        else  {
            builder.setTitle("Normal Child");
            builder.setMessage("Continue feeding the child with a wide range of healthy foods " );
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

       }



