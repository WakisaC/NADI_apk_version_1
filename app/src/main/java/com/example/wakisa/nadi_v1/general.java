package com.example.wakisa.nadi_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.support.*;
import android.content.*;
import android.widget.*;
import android.os.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class general extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // create objects to access data from other classes
    followUpDatabase myDb;
    EditText edweight,edheight;
    Button bt;

    ////variables to copy values to next activity//
    String muac_copy,wasting_copy;
    double weight_copy,height_copy;
    String weight_, height_, muac_, age_y, age_m;
    String server_url2 ="http://192.168.0.50/nadi/generalResult.php";
    AlertDialog.Builder builder;

    //////variables to get weight and height and pass them directly to general results to save the numbers in database
    String weightValue,heightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /////////Result button events/////////////////
        Button b = (Button)findViewById(R.id.rBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result();

            }
        });
        //////////////////////////////database code///////////////////////////////
        myDb =new followUpDatabase(this);

    }
    //////////Method to add data to database///////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            home();
        } else if (id == R.id.nav_choose) {
            choose();

        } else if (id == R.id.nav_progress) {
                database();

        } else if (id == R.id.nav_feedback) {
            feedbackl();

        } else if (id == R.id.nav_notes) {
            notes();

        } else if (id == R.id.nav_About) {
            about();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void home(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void database(){
        Intent intent = new Intent(this,Database.class);
        startActivity(intent);
    }
    public void choose(){
        Intent intent= new Intent(this,choose.class);
        startActivity(intent);
    }
    public void general(){
        Intent intent = new Intent(this,general.class);
        startActivity(intent);
    }
    public void about(){
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
    }
    public void feedbackl(){
        Intent intent = new Intent(this,Feedback.class);
        startActivity(intent);
    }
    public void notes(){
        Intent intent = new Intent(this,NoteMain.class);
        startActivity(intent);
    }

    // Alert user to validate input//
    public void alertNormal(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Congratulations, the child is growing normally.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void alertStunted(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry the child is stunted. Go to advice?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void alertTall(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry the child is Tall.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void alertOedema(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry, the child is undernourished.Visit a nearest Health centre or Hospital");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void alertRadioButtonCheck(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Please Choose Gender!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void alertOedemaRadioButtonCheck(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Please Choose Oedema status on check button!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void alertYears(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry,the child age exceeds 5 years!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void alertMonths(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry,invalid age in months!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    //////methods to get height

    public String maleHeight(double height, int years,int months){

        String growth="";

        if ((years==0 && months==0)&&(height>=46.1 && height<=53.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==0)&&(height>53.7)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==1)&&(height>=50.8 && height<=58.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==1)&&(height>58.6)) {
            growth = "Tall child";
        }

        else  if ((years==0 && months==2)&&(height>=54.4 && height<=62.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==2)&&(height>62.4)) {
            growth = "Tall child";
        }

        else  if ((years==0 && months==3)&&(height>=57.3 && height<=65.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==3)&&(height>65.5)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==4)&&(height>=59.7 && height<=68)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==4)&&(height>68)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==5)&&(height>=61.7 && height<=70.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==5)&&(height>70.1)) {
            growth = "Tall child";
        }


        else if ((years==0 && months==6)&&(height>=63.3 && height<=71.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==6)&&(height>71.9)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==7)&&(height>=64.8&& height<=73.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==7)&&( height>73.5)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==8)&&(height>=66.2 && height<=75)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==8)&&(height>75)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==9)&&(height>=67.5 && height<=76.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==9)&&(height>76.5)) {
            growth = "Tall child";
        }


        else if ((years==0 && months==10)&&(height>=68.7 && height<=77.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==10)&&( height>77.9)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==11)&&(height>=69.9 && height<=79.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==11)&&( height>79.2)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==12)&&(height>=71 && height<=80.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==12)&&( height>80.5)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==0)&&(height>=71 && height<=80.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==0)&&( height>80.5)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==1)&&(height>=72.1 && height<=81.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==1)&&( height>81.8)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==2)&&(height>=73.1 && height<=83)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==2)&&( height>83)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==3)&&(height>=74.1 && height<=84.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==3)&&(height>84.2)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==4)&&(height>=75 && height<=85.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==4)&&( height>85.4)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==5)&&(height>=76 && height<=86.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==5)&&(height>86.5)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==6)&&(height>=76.9 && height<=87.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==6)&&( height>87.7)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==7)&&(height>=77.7 && height<=88.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==7)&&( height>88.8)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==8)&&(height>=78.6 && height<=89.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==8)&&(height>89.8)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==9)&&(height>=79.4 && height<=90.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==9)&&( height>90.9)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==10)&&(height>=80.2 && height<=91.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==10)&&(height>91.9)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==11)&&(height>=81 && height<=92.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==11)&&( height>92.9)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==0)&&(height>=81.7 && height<=93.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==0)&&( height>93.9)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==1)&&(height>=81.7 && height<=94.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==1)&&( height>94.2)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==2)&&(height>=82.5 && height<=95.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==2)&&( height>95.2)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==3)&&(height>=83.1 && height<=96.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==3)&&(height>96.1)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==4)&&(height>=83.8 && height<=97)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==4)&&( height>97)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==5)&&(height>=84.5 && height<=97.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==5)&&( height>97.9)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==6)&&(height>=85.1 && height<=98.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==6)&&(height>98.7)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==7)&&(height>=85.7 && height<=99.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==7)&&( height>99.6)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==8)&&(height>=86.4 && height<=100.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==8)&&(height>100.4)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==9)&&(height>=86.9 && height<=101.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==9)&&( height>101.2)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==10)&&(height>=87.5 && height<=102)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==10)&&(height>102)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==11)&&(height>=88.1 && height<=102.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==11)&&( height>102.7)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==0)&&(height>=88.7 && height<=103.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==0)&&( height>103.5)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==1)&&(height>=89.2 && height<=104.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==1)&&( height>104.2)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==2)&&(height>=89.8 && height<=105)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==2)&&( height>105)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==3)&&(height>=90.3 && height<=105.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==3)&&( height>105.7)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==4)&&(height>=90.9 && height<=106.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==4)&&( height>106.4)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==5)&&(height>=91.4 && height<=107.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==5)&&( height>107.1)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==6)&&(height>=91.9 && height<=107.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==6)&&( height>107.8)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==7)&&(height>=92.4 && height<=108.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==7)&&(height>108.5)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==8)&&(height>=93 && height<=109.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==8)&&(height>109.1)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==9)&&(height>=93.5 && height<=109.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==9)&&( height>109.8)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==10)&&(height>=94 && height<=110.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==10)&&( height>110.4)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==11)&&(height>=94.4 && height<=111.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==11)&&( height>111.1)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==0)&&(height>=94.9 && height<=111.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==0)&&(height>111.7)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==1)&&(height>=95.4 && height<=112.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==1)&&( height>112.4)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==2)&&(height>=95.9 && height<=113)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==2)&&( height>113)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==3)&&(height>=96.4 && height<=113.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==3)&&( height>113.6)){
            growth = "Tall child";
        }


        else if ((years==4 && months==4)&&(height>=96.9 && height<=114.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==4)&&( height>114.2)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==5)&&(height>=97.4 && height<=114.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==5)&&( height>114.9)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==6)&&(height>=97.8 && height<=115.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==6)&&( height>115.5)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==7)&&(height>=98.3 && height<=116.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==7)&&(height>116.1)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==8)&&(height>=98.8 && height<=116.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==8)&&( height>116.7)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==9)&&(height>=99.3 && height<=117.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==9)&&( height>117.4)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==10)&&(height>=99.7 && height<=118)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==10)&&( height>118)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==11)&&(height>=100.2 && height<=118.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==11)&&(height>118.6)) {
            growth = "Tall child";
        }

        else if ((years==5 && months==0)&&(height>=100.7 && height<=119.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==5 && months==0)&&( height>119.2)) {
            growth = "Tall child";
        }

        else if (years>5 || months>12) {
            growth = "invalid age!!";
        }

        else{growth="Stunted child";}




        return growth;

    }
    public String femaleHeight(double height, int years,int months){
        String growth;

        if ((years==0 && months==0)&&(height>=45.4 && height<=52.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==0)&&(height>70.3)) {
            growth = "Tall child";
        }
        else if ((years==0 && months==1)&&(height>=49.8 && height<=57.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==1)&&(height>57.6)) {
            growth = "Tall child";
        }
        else if ((years==0 && months==2)&&(height>=53 && height<=61.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==2)&&(height>61.1)) {
            growth = "Tall child";
        }
        else if ((years==0 && months==3)&&(height>=55.6 && height<=64)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==3)&&(height>64)) {
            growth = "Tall child";
        }
        else if ((years==0 && months==4)&&(height>=57.8 && height<=66.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==4)&&(height>66.4)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==5)&&(height>=59.6 && height<=68.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==5)&&(height>68.5)) {
            growth = "Tall child";
        }
        else if ((years==0 && months==6)&&(height>=61.2 && height<=70.3)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==6)&&(height>70.3)) {
            growth = "Tall child";
        }


        else if ((years==0 && months==7)&&(height>=62.7 && height<=71.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==7)&&( height>71.9)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==8)&&(height>=64 && height<=73.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==8)&&(height>73.5)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==9)&&(height>=65.3 && height<=75)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==9)&&(height>75)) {
            growth = "Tall child";
        }


        else if ((years==0 && months==10)&&(height>=66.5 && height<=76.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==10)&&( height>76.4)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==11)&&(height>=67.7 && height<=77.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==11)&&( height>77.8)) {
            growth = "Tall child";
        }

        else if ((years==0 && months==12)&&(height>=68.9 && height<=79.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==0 && months==12)&&( height>79.2)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==0)&&(height>=68.9 && height<=79.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==0)&&( height>79.2)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==1)&&(height>=70 && height<=80.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==1)&&( height>80.5)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==2)&&(height>=71 && height<=81.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==2)&&( height>81.7)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==3)&&(height>=72 && height<=83)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==3)&&(height>83)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==4)&&(height>=73 && height<=84.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==4)&&( height>84.2)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==5)&&(height>=74 && height<=85.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==5)&&(height>85.4)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==6)&&(height>=74.9 && height<=86.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==6)&&( height>86.5)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==7)&&(height>=75.8&& height<=87.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==7)&&( height>87.6)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==8)&&(height>=76.7&& height<=88.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==8)&&(height>88.7)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==9)&&(height>=77.5 && height<=89.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==9)&&( height>89.8)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==10)&&(height>=78.4&& height<=90.8)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==10)&&(height>90.8)) {
            growth = "Tall child";
        }

        else if ((years==1 && months==11)&&(height>=79.2 && height<=91.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==1 && months==11)&&( height>91.9)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==0)&&(height>=80 && height<=92.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==0)&&( height>92.9)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==1)&&(height>=80 && height<=93.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==1)&&( height>93.1)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==2)&&(height>=80.8&& height<=94.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==2)&&( height>94.1)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==3)&&(height>=81.5&& height<=95)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==3)&&(height>95)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==4)&&(height>=82.2 && height<=96)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==4)&&( height>96)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==5)&&(height>=82.9 && height<=96.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==5)&&( height>96.9)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==6)&&(height>=83.6 && height<=97.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==6)&&(height>97.7)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==7)&&(height>=84.3&& height<=98.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==7)&&( height>98.6)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==8)&&(height>=84.9 && height<=99.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==8)&&(height>99.4)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==9)&&(height>=85.6&& height<=100.3)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==9)&&( height>100.3)) {
            growth = "Tall child";
        }


        else if ((years==2 && months==10)&&(height>=86.2 && height<=101.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==10)&&(height>101.1)) {
            growth = "Tall child";
        }

        else if ((years==2 && months==11)&&(height>=86.8 && height<=101.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==2 && months==11)&&( height>101.9)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==0)&&(height>=87.4 && height<=102.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==0)&&( height>102.7)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==1)&&(height>=88 && height<=103.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==1)&&( height>103.4)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==2)&&(height>=88.6&& height<=104.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==2)&&( height>104.2)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==3)&&(height>=89.2 && height<=105)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==3)&&( height>105)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==4)&&(height>=89.8 && height<=105.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==4)&&( height>105.7)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==5)&&(height>=90.4 && height<=106.4)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==5)&&( height>106.4)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==6)&&(height>=90.9 && height<=107.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==6)&&( height>107.2)) {
            growth = "Tall child";
        }

        else if ((years==3 && months==7)&&(height>=91.5 && height<=107.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==7)&&(height>107.9)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==8)&&(height>=92&& height<=108.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==8)&&(height>108.6)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==9)&&(height>=92.5&& height<=109.3)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==9)&&( height>109.3)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==10)&&(height>=93.1 && height<=110)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==10)&&( height>110)) {
            growth = "Tall child";
        }


        else if ((years==3 && months==11)&&(height>=93.6 && height<=110.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==3 && months==11)&&( height>110.7)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==0)&&(height>=94.1 && height<=111.3)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==0)&&(height>111.3)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==1)&&(height>=94.6 && height<=112)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==1)&&( height>112)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==2)&&(height>=95.1 && height<=112.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==2)&&( height>112.7)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==3)&&(height>=95.6 && height<=113.3)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==3)&&( height>113.3)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==4)&&(height>=96.1 && height<=114)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==4)&&( height>114)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==5)&&(height>=96.6 && height<=114.6)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==5)&&( height>114.6)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==6)&&(height>=97.1 && height<=115.2)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==6)&&( height>115.2)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==7)&&(height>=97.6 && height<=115.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==7)&&(height>115.9)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==8)&&(height>=98.1 && height<=116.5)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==8)&&( height>116.5)) {
            growth = "Tall child";
        }


        else if ((years==4 && months==9)&&(height>=98.5 && height<=117.1)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==9)&&( height>117.1)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==10)&&(height>=99 && height<=117.7)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==10)&&( height>117.7)) {
            growth = "Tall child";
        }

        else if ((years==4 && months==11)&&(height>=99.5&& height<=118.3)) {
            growth = "Normal Child Height";
        }
        else if ((years==4 && months==11)&&(height>118.3)) {
            growth = "Tall child";
        }

        else if ((years==5 && months==0)&&(height>=99.9 && height<=118.9)) {
            growth = "Normal Child Height";
        }
        else if ((years==5 && months==0)&&( height>118.9)) {
            growth = "Tall child";
        }

        else if (years>5 || months>12) {
            growth = "invalid age!!";
        }

        else{growth="Stunted child";}

        return growth;
    }

    ///////methods to get weight////////////
    public String maleWeight(double weight,int years,int months){

        String output="";

        if(years==0 && months==0){
            if(weight>=2.5 && weight<=4.4)
                output="Normal weight";
            else if(weight<2.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }

        else if(years==0 && months==1){
            if(weight>=3.4 && weight<=5.8)
                output="Normal weight";
            else if(weight<3.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==2){
            if(weight>=4.3 && weight<=7.1)
                output="Normal weight";
            else if(weight<4.3)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==3){
            if(weight>=5.0 && weight<=8.0)
                output="Normal weight";
            else if(weight<5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==4){
            if(weight>=5.6 && weight<=8.7)
                output="Normal weight";
            else if(weight<5.6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==5){
            if(weight>=6 && weight<=9.3)
                output="Normal weight";
            else if(weight<6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }


        else if(years==0 && months==6){
            if(weight>=6.4 && weight<=9.8)
                output="Normal weight";
            else if(weight<6.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==7){
            if(weight>=6.7 && weight<=10.3)
                output="Normal weight";
            else if(weight<6.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 0&& months==8){
            if(weight>=6.9 && weight<= 10.7)
                output="Normal weight";
            else if(weight<6.9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==9){
            if(weight>=7.1 && weight<= 11.0)
                output="Normal weight";
            else if(weight< 7.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==10){
            if(weight>=7.4 && weight<=11.4)
                output="Normal weight";
            else if(weight< 7.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==11){
            if(weight>=7.6 && weight<=11.7)
                output="Normal weight";
            else if(weight<7.6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 0&& months==12){
            if(weight>=7.7 && weight<= 12)
                output="Normal weight";
            else if(weight< 7.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==0){
            if(weight>= 7.7&& weight<=12 )
                output="Normal weight";
            else if(weight< 7.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==1){
            if(weight>=7.9&& weight<=12.3 )
                output="Normal weight";
            else if(weight<7.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1 && months== 2){
            if(weight>=8.1 && weight<=12.6 )
                output="Normal weight";
            else if(weight<8.1 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==3){
            if(weight>=8.3 && weight<= 12.8)
                output="Normal weight";
            else if(weight<8.3)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==4){
            if(weight>=8.4 && weight<=13.1)
                output="Normal weight";
            else if(weight< 8.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==5){
            if(weight>=8.6&& weight<=13.4 )
                output="Normal weight";
            else if(weight<8.6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==6){
            if(weight>=8.8 && weight<=13.7 )
                output="Normal weight";
            else if(weight< 8.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==7){
            if(weight>=8.9 && weight<= 13.9)
                output="Normal weight";
            else if(weight<8.9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==8){
            if(weight>=9.1 && weight<=14.2 )
                output="Normal weight";
            else if(weight<9.1 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==9){
            if(weight>=9.2&& weight<=14.5 )
                output="Normal weight";
            else if(weight<9.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==10){
            if(weight>=9.4&& weight<=14.7 )
                output="Normal weight";
            else if(weight<9.4 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months== 11){
            if(weight>=9.5 && weight<=15 )
                output="Normal weight";
            else if(weight<9.5 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==12){
            if(weight>=9.7&& weight<=15.3)
                output="Normal weight";
            else if(weight<9.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        //////////////////////////////////

        else if(years== 2&& months==0){
            if(weight>=9.7&& weight<=15.3)
                output="Normal weight";
            else if(weight<9.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==1){
            if(weight>=9.8 && weight<=15.5)
                output="Normal weight";
            else if(weight<9.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2 && months== 2){
            if(weight>=10 && weight<= 15.8)
                output="Normal weight";
            else if(weight<10 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==3){
            if(weight>=10.1&& weight<=16.1)
                output="Normal weight";
            else if(weight<10.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==4){
            if(weight>=10.2 && weight<=16.3)
                output="Normal weight";
            else if(weight<10.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==5){
            if(weight>=10.4 && weight<= 16.6)
                output="Normal weight";
            else if(weight<10.4 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==6){
            if(weight>= 10.5&& weight<=16.9 )
                output="Normal weight";
            else if(weight<10.5 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==7){
            if(weight>=10.7 && weight<=17.1 )
                output="Normal weight";
            else if(weight<10.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==8){
            if(weight>=10.8 && weight<= 17.4)
                output="Normal weight";
            else if(weight<10.8 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==9){
            if(weight>=10.9 && weight<= 17.6)
                output="Normal weight";
            else if(weight<10.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==10){
            if(weight>=11.0 && weight<=17.8)
                output="Normal weight";
            else if(weight< 11)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months== 11){
            if(weight>=11.2 && weight<=18.1 )
                output="Normal weight";
            else if(weight<11.2 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==12){
            if(weight>=11.3 && weight<=18.3 )
                output="Normal weight";
            else if(weight<11.3 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        ////////////////////////////////

        else if(years== 3&& months==0){
            if(weight>=11.3 && weight<=18.3 )
                output="Normal weight";
            else if(weight<11.3 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==1){
            if(weight>=11.4 && weight<=18.6 )
                output="Normal weight";
            else if(weight<11.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months== 2){
            if(weight>=11.5 && weight<=18.8)
                output="Normal weight";
            else if(weight<11.5 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==3){
            if(weight>=11.6 && weight<=19.0 )
                output="Normal weight";
            else if(weight<11.6 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==4){
            if(weight>=11.8 && weight<=19.3 )
                output="Normal weight";
            else if(weight< 11.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==5){
            if(weight>=11.9 && weight<=19.5)
                output="Normal weight";
            else if(weight<11.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==6){
            if(weight>=12 && weight<= 19.7)
                output="Normal weight";
            else if(weight< 12)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==7){
            if(weight>=12.1 && weight<=20 )
                output="Normal weight";
            else if(weight<12.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==8){
            if(weight>=12.2 && weight<=20.2 )
                output="Normal weight";
            else if(weight<12.2 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==9){
            if(weight>=12.4 && weight<=20.5 )
                output="Normal weight";
            else if(weight<12.4 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==10){
            if(weight>=12.5 && weight<=20.7)
                output="Normal weight";
            else if(weight<12.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months== 11){
            if(weight>=12.6 && weight<=20.9 )
                output="Normal weight";
            else if(weight<12.6 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }

        else if(years== 3&& months==12){
            if(weight>=12.7 && weight<=21.2 )
                output="Normal weight";
            else if(weight<12.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        ///////////////////////////////////

        else if(years== 4&& months==0){
            if(weight>=12.7 && weight<=21.2 )
                output="Normal weight";
            else if(weight<12.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==1){
            if(weight>=12.8 && weight<=21.4 )
                output="Normal weight";
            else if(weight<12.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months== 2){
            if(weight>=12.9 && weight<=21.7 )
                output="Normal weight";
            else if(weight<12.9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==3){
            if(weight>=13.1 && weight<=21.9 )
                output="Normal weight";
            else if(weight< 13.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==4){
            if(weight>=13.2 && weight<=22.2 )
                output="Normal weight";
            else if(weight<13.2 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==5){
            if(weight>= 13.3&& weight<=22.4 )
                output="Normal weight";
            else if(weight<13.3 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==6){
            if(weight>=13.4 && weight<=22.7 )
                output="Normal weight";
            else if(weight< 13.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==7){
            if(weight>=13.5 && weight<=22.9)
                output="Normal weight";
            else if(weight< 13.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==8){
            if(weight>=13.6 && weight<=23.2 )
                output="Normal weight";
            else if(weight<13.6 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==9){
            if(weight>=13.7 && weight<=23.4 )
                output="Normal weight";
            else if(weight<13.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==10){
            if(weight>=13.8 && weight<=23.7 )
                output="Normal weight";
            else if(weight<13.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months== 11){
            if(weight>=14 && weight<= 23.9)
                output="Normal weight";
            else if(weight<14 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4 && months== 12){
            if(weight>=14.1 && weight<=24.2 )
                output="Normal weight";
            else if(weight<14.1 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        ////////////////////////////////
        else if(years==5&& months== 0){
            if(weight>=14.1 && weight<=24.2 )
                output="Normal weight";
            else if(weight<14.1 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }

        return output;

    }
    public String femaleWeight(double weight,int years,int months){
        String output ="";
        if(years==0 && months==0){
            if(weight>=2.4 && weight<=4.2)
                output="Normal weight";
            else if(weight<2.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==1){
            if(weight>=3.2 && weight<=5.5)
                output="Normal weight";
            else if(weight<3.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==2){
            if(weight>=3.9 && weight<=6.6)
                output="Normal weight";
            else if(weight<3.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==3){
            if(weight>=4.5 && weight<=7.5)
                output="Normal weight";
            else if(weight<4.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==4){
            if(weight>=5 && weight<=8.2)
                output="Normal weight";
            else if(weight<5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==5){
            if(weight>=5.4 && weight<=8.8)
                output="Normal weight";
            else if(weight<5.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }


        else if(years==0 && months==6){
            if(weight>=5.7 && weight<=9.3)
                output="Normal weight";
            else if(weight<5.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==7){
            if(weight>=6 && weight<=9.8)
                output="Normal weight";
            else if(weight<6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 0&& months==8){
            if(weight>=6.3 && weight<= 10.2)
                output="Normal weight";
            else if(weight<6.3 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==9){
            if(weight>=6.5 && weight<= 10.5)
                output="Normal weight";
            else if(weight< 6.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==10){
            if(weight>=6.7&& weight<=10.9)
                output="Normal weight";
            else if(weight< 6.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==0 && months==11){
            if(weight>=6.9 && weight<=11.2)
                output="Normal weight";
            else if(weight<6.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 0&& months==12){
            if(weight>=7 && weight<= 11.5)
                output="Normal weight";
            else if(weight< 7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==0){
            if(weight>= 7&& weight<=11.5 )
                output="Normal weight";
            else if(weight< 7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==1){
            if(weight>=7.2&& weight<=11.8 )
                output="Normal weight";
            else if(weight<7.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1 && months== 2){
            if(weight>=7.4 && weight<=12.1 )
                output="Normal weight";
            else if(weight<7.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==3){
            if(weight>=7.6 && weight<= 12.4)
                output="Normal weight";
            else if(weight<7.6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==4){
            if(weight>=7.7 && weight<=12.6)
                output="Normal weight";
            else if(weight< 7.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==5){
            if(weight>=7.9&& weight<=12.9 )
                output="Normal weight";
            else if(weight<7.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==6){
            if(weight>=8.1 && weight<=13.2 )
                output="Normal weight";
            else if(weight< 8.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==7){
            if(weight>=8.2&& weight<= 13.5)
                output="Normal weight";
            else if(weight<8.2 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==8){
            if(weight>=8.4 && weight<=13.7 )
                output="Normal weight";
            else if(weight<8.4 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months==9){
            if(weight>=8.6&& weight<=14 )
                output="Normal weight";
            else if(weight<8.6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==10){
            if(weight>=8.7&& weight<=14.3 )
                output="Normal weight";
            else if(weight<8.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==1 && months== 11){
            if(weight>=8.9 && weight<=14.6 )
                output="Normal weight";
            else if(weight<8.9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==12){
            if(weight>=9&& weight<=14.8)
                output="Normal weight";
            else if(weight<9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 1&& months==12){
            if(weight>=9&& weight<=14.8)
                output="Normal weight";
            else if(weight<9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        //////////////////////////////////

        else if(years== 2&& months==0){
            if(weight>=9&& weight<=14.8)
                output="Normal weight";
            else if(weight<9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==1){
            if(weight>=9.2 && weight<=15.1)
                output="Normal weight";
            else if(weight<9.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2 && months== 2){
            if(weight>=9.4 && weight<= 15.4)
                output="Normal weight";
            else if(weight<9.4 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==3){
            if(weight>=9.5&& weight<=15.7)
                output="Normal weight";
            else if(weight<9.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==4){
            if(weight>=9.7 && weight<=16)
                output="Normal weight";
            else if(weight<9.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==5){
            if(weight>=9.8 && weight<= 16.2)
                output="Normal weight";
            else if(weight<9.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==6){
            if(weight>= 10&& weight<=16.5 )
                output="Normal weight";
            else if(weight<10 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==7){
            if(weight>=10.1 && weight<=16.8)
                output="Normal weight";
            else if(weight<10.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==8){
            if(weight>=10.3 && weight<= 17.1)
                output="Normal weight";
            else if(weight<10.3 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months==9){
            if(weight>=10.4 && weight<= 17.3)
                output="Normal weight";
            else if(weight<10.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==10){
            if(weight>=10.5 && weight<=17.6)
                output="Normal weight";
            else if(weight< 10.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==2 && months== 11){
            if(weight>=10.7&& weight<=17.9)
                output="Normal weight";
            else if(weight<10.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 2&& months==12){
            if(weight>=10.8 && weight<=18.1 )
                output="Normal weight";
            else if(weight<10.8 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }

        ////////////////////////////////

        else if(years== 3&& months==0){
            if(weight>=10.8 && weight<=18.1 )
                output="Normal weight";
            else if(weight<10.8 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==1){
            if(weight>=10.9 && weight<=18.4 )
                output="Normal weight";
            else if(weight<10.9)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months== 2){
            if(weight>=11.1 && weight<=18.7)
                output="Normal weight";
            else if(weight<11.1 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==3){
            if(weight>=11.2 && weight<=19.0 )
                output="Normal weight";
            else if(weight<11.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==4){
            if(weight>=11.3&& weight<=19.2 )
                output="Normal weight";
            else if(weight< 11.3)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==5){
            if(weight>=11.5 && weight<=19.5)
                output="Normal weight";
            else if(weight<11.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==6){
            if(weight>=11.6 && weight<= 19.8)
                output="Normal weight";
            else if(weight< 11.6)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==7){
            if(weight>=11.7 && weight<=20.1)
                output="Normal weight";
            else if(weight<11.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==8){
            if(weight>=11.8 && weight<=20.4 )
                output="Normal weight";
            else if(weight<11.8 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months==9){
            if(weight>=12 && weight<=20.7)
                output="Normal weight";
            else if(weight<12)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==10){
            if(weight>=12.1 && weight<=20.9)
                output="Normal weight";
            else if(weight<12.1)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==3&& months== 11){
            if(weight>=12.2 && weight<=21.2 )
                output="Normal weight";
            else if(weight<12.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 3&& months==12){
            if(weight>=12.3 && weight<=21.5 )
                output="Normal weight";
            else if(weight<12.73)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        ///////////////////////////////////

        else if(years== 4&& months==0){
            if(weight>=12.3 && weight<=21.5 )
                output="Normal weight";
            else if(weight<12.73)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==1){
            if(weight>=12.4 && weight<=21.8 )
                output="Normal weight";
            else if(weight<12.4)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months== 2){
            if(weight>=12.6 && weight<=22.1 )
                output="Normal weight";
            else if(weight<12.6 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==3){
            if(weight>=12.7 && weight<=22.4 )
                output="Normal weight";
            else if(weight< 12.7)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==4){
            if(weight>=12.8 && weight<=22.6 )
                output="Normal weight";
            else if(weight<12.8)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==5){
            if(weight>= 12.9&& weight<=22.9 )
                output="Normal weight";
            else if(weight<12.9 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==6){
            if(weight>=13&& weight<=23.2 )
                output="Normal weight";
            else if(weight< 13)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==7){
            if(weight>=13.2&& weight<=23.5)
                output="Normal weight";
            else if(weight< 13.2)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==8){
            if(weight>=13.3 && weight<=23.8 )
                output="Normal weight";
            else if(weight<13.3 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months==9){
            if(weight>=13.4 && weight<=24.1 )
                output="Normal weight";
            else if(weight<13.4 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years== 4&& months==10){
            if(weight>=13.5 && weight<=24.4 )
                output="Normal weight";
            else if(weight<13.5)
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months== 11){
            if(weight>=13.6 && weight<= 24.6)
                output="Normal weight";
            else if(weight<13.6 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        else if(years==4&& months== 12){
            if(weight>=13.7 && weight<=24.9 )
                output="Normal weight";
            else if(weight<13.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }
        ////////////////////////////////
        else if(years==5&& months== 0){
            if(weight>=13.7 && weight<=24.9 )
                output="Normal weight";
            else if(weight<13.7 )
                output="Underweight";
            else{
                output="Overweight";
            }

        }

        return output;
    }

    ///////methods to get results from wasting assessment//////////////
    public String maleWasting(double height,double weight, int years, int months){
        String limit="";

        if ((years==1 || years==0 ) &&(months>=0 && months<=12)){

            if ((height==45)&&(weight>=2 && weight<=3 )){
                limit = "Normal Child";
            } else if ((height==45)&&(weight>3 )){
                limit = "Overnourished";
            }
            else if((height==45.5)&&(weight>=2.1 && weight<=3.1 )){
                limit = "Normal Child";
            }
            else if ((height==45.5)&&(weight>3.1 )){
                limit = "Overnourished";
            }

            else if((height==46)&&(weight>=2.2 && weight<=3.1)){
                limit ="Normal Child";
            }
            else if ((height==46)&&(weight>3.1 )){
                limit = "Overnourished";
            }
            else if((height==46.5 )&&(weight>=2.3 && weight<=3.2 )){
                limit ="Normal Child";
            }
            else if ((height==46.5)&&(weight>3.2 )){
                limit = "Overnourished";
            }
            else if((height==47 )&&(weight>=2.3&& weight<=3.3)){
                limit ="Normal Child";
            } else if ((height==47)&&(weight>3.3 )){
                limit = "Overnourished";
            }

            else if((height== 47.5)&&(weight>=2.4 && weight<=3.4)){
                limit ="Normal Child";
            } else if ((height==47.5)&&(weight>3.4 )){
                limit = "Overnourished";
            }

            else if((height== 48)&&(weight>=2.5&& weight<=3.6 )){
                limit ="Normal Child";
            }  else if((height== 48)&&( weight>3.6 )){
                limit ="Overnourished";
            }
            else if((height==48.5 )&&(weight>=2.6 && weight<=3.7)){
                limit ="Normal Child";
            }  else if((height== 48.5)&&( weight>3.7 )){
                limit ="Overnourished";
            }

            else if((height==49 )&&(weight>=2.6 && weight<=3.8)){
                limit ="Normal Child";
            } else if((height== 49)&&( weight>3.8 )){
                limit ="Overnourished";
            }

            else if((height==49.5 )&&(weight>=2.7 && weight<=3.9)){
                limit ="Normal Child";
            }  else if((height== 49.5)&&( weight>3.9)){
                limit ="Overnourished";
            }

            else if((height== 50)&&(weight>=2.8 && weight<=4 )){
                limit ="Normal Child";
            }  else if((height== 50)&&( weight>4 )){
                limit ="Overnourished";
            }

            else if((height==50.5)&&(weight>=2.9 && weight<=4.1)){
                limit ="Normal Child";
            }  else if((height== 50.5)&&( weight>4.1)){
                limit ="Overnourished";
            } else if((height== 51)&&(weight>=3 && weight<=4.2)){
                limit ="Normal Child";
            } else if((height== 51)&&( weight>4.2 )){
                limit ="Overnourished";
            }

            else if((height==51.5 )&&(weight>=3.1 && weight<=4.4)){
                limit ="Normal Child";
            } else if((height== 51.5)&&( weight>4.4)){
                limit ="Overnourished";
            }


            else if((height==52 )&&(weight>=3.2 && weight<=4.5)){
                limit ="Normal Child";
            }  else if((height== 52)&&( weight>4.5 )){
                limit ="Overnourished";
            }
            else if((height==52.5 )&&(weight>=3.3 && weight<=4.6)){
                limit ="Normal Child";
            }  else if((height== 52.5)&&( weight>4.6)){
                limit ="Overnourished";
            }

            else if((height== 53)&&(weight>=3.4 && weight<=4.8)){
                limit ="Normal Child";
            }  else if((height== 53)&&( weight>4.8 )){
                limit ="Overnourished";
            }

            else if((height== 53.5)&&(weight>=3.5 && weight<=4.9 )){
                limit ="Normal Child";
            }  else if((height== 53.5)&&( weight>4.9)){
                limit ="Overnourished";
            }

            else if((height== 54)&&(weight>=3.6 && weight<=5.1)){
                limit ="Normal Child";
            }  else if((height== 54)&&( weight>5.1 )){
                limit ="Overnourished";
            }

            else if((height==54.5 )&&(weight>=3.7 && weight<=5.3)){
                limit ="Normal Child";
            }  else if((height== 54.5)&&( weight>5.3 )){
                limit ="Overnourished";
            }

            else if((height==55 )&&(weight>=3.8 && weight<=5.4)){
                limit ="Normal Child";
            } else if((height== 55)&&( weight>5.4 )){
                limit ="Overnourished";
            }

            else if((height== 55.5)&&(weight>=4 && weight<=5.6 )){
                limit ="Normal Child";
            }  else if((height== 55.5)&&( weight>5.6 )){
                limit ="Overnourished";
            }

            else if((height==56 )&&(weight>=4.1 && weight<=5.8)){
                limit ="Normal Child";
            }  else if((height== 56)&&( weight>5.8 )){
                limit ="Overnourished";
            }

            else if((height==56.5 )&&(weight>=4.2 && weight<=5.9)){
                limit ="Normal Child";
            }  else if((height== 56.5)&&( weight>5.9 )){
                limit ="Overnourished";
            }

            else if((height==57 )&&(weight>=4.3 && weight<=6.1)){
                limit ="Normal Child";
            } else if((height== 57)&&( weight>6.1 )){
                limit ="Overnourished";
            }

            else if((height==57.5)&&(weight>=4.5 && weight<=6.3)){
                limit ="Normal Child";
            }  else if((height== 57.5)&&( weight>6.3 )){
                limit ="Overnourished";
            }

            else if((height== 58)&&(weight>=4.6 && weight<=6.4)){
                limit ="Normal Child";
            }  else if((height== 58)&&( weight>6.4 )){
                limit ="Overnourished";
            }

            else if((height==58.5 )&&(weight>=4.7 && weight<=6.6)){
                limit ="Normal Child";
            }  else if((height== 58.5)&&( weight>6.6 )){
                limit ="Overnourished";
            }

            else if((height== 59)&&(weight>=4.8 && weight<=6.8)){
                limit ="Normal Child";
            }  else if((height== 59)&&( weight>6.8 )){
                limit ="Overnourished";
            }

            else if((height== 59.5)&&(weight>=5 && weight<=7)){
                limit ="Normal Child";
            }  else if((height== 59.5)&&( weight>7 )){
                limit ="Overnourished";
            }

            else if((height== 60)&&(weight>=5.1 && weight<=7.1)){
                limit ="Normal Child";
            }  else if((height== 60)&&( weight>7.1 )){
                limit ="Overnourished";
            }

            else if((height== 60.5)&&(weight>=5.2 && weight<=7.3 )){
                limit ="Normal Child";
            }else if((height== 60.5)&&( weight>7.3 )){
                limit ="Overnourished";
            }

            else if((height== 61)&&(weight>=5.3 && weight<=7.4)){
                limit ="Normal Child";
            } else if((height== 61)&&( weight>7.4 )){
                limit ="Overnourished";
            }

            else if((height== 61.5)&&(weight>=5.4 && weight<=7.6)){
                limit ="Normal Child";
            }else if((height== 61.5)&&( weight>7.6)){
                limit ="Overnourished";
            }

            else if((height== 62)&&(weight>=5.6 && weight<=7.7)){
                limit ="Normal Child";
            } else if((height== 62)&&( weight>7.7 )){
                limit ="Overnourished";
            }

            else if((height== 62.5)&&(weight>=5.7&& weight<=7.9)){
                limit ="Normal Child";
            }else if((height== 62.5)&&( weight>7.9 )){
                limit ="Overnourished";
            }

            else if((height== 63)&&(weight>=5.8 && weight<=8)){
                limit ="Normal Child";
            }else if((height== 63)&&( weight>8 )){
                limit ="Overnourished";
            }

            else if((height== 63.5)&&(weight>=5.9 && weight<=8.2)){
                limit ="Normal Child";
            } else if((height== 63.5)&&( weight>8.2 )){
                limit ="Overnourished";
            }

            else if((height== 64)&&(weight>=6 && weight<=8.3)){
                limit ="Normal Child";
            } else if((height== 64)&&( weight>8.3 )){
                limit ="Overnourished";
            }

            else if((height== 64.5)&&(weight>=6.1 && weight<=8.5)){
                limit ="Normal Child";
            }else if((height== 64.5)&&( weight>8.5 )){
                limit ="Overnourished";
            }

            else if((height== 65)&&(weight>=6.2 && weight<=8.6)){
                limit ="Normal Child";
            }else if((height== 65)&&( weight>8.6 )){
                limit ="Overnourished";
            }

            else if((height== 65.5)&&(weight>=6.3 && weight<=8.7)){
                limit ="Normal Child";
            } else if((height== 65.5)&&( weight>8.7 )){
                limit ="Overnourished";
            }

            else if((height== 66)&&(weight>=6.4 && weight<=8.9)){
                limit ="Normal Child";
            }else if((height== 66)&&( weight>8.9 )){
                limit ="Overnourished";
            }

            else if((height== 66.5)&&(weight>=6.5 && weight<=9.0)){
                limit ="Normal Child";
            }else if((height== 66.5)&&( weight>9.0 )){
                limit ="Overnourished";
            }

            else if((height== 67)&&(weight>=6.6 && weight<=9.2)){
                limit ="Normal Child";
            } else if((height== 67)&&( weight>9.2 )){
                limit ="Overnourished";
            }

            else if((height==67.5 )&&(weight>=6.7 && weight<=9.3)){
                limit ="Normal Child";
            }else if((height== 67.5)&&( weight>9.3 )){
                limit ="Overnourished";
            }

            else if((height==68 )&&(weight>=6.8 && weight<=9.4)){
                limit ="Normal Child";
            }else if((height== 68)&&( weight>9.4 )){
                limit ="Overnourished";
            }

            else if((height==68.5 )&&(weight>=6.9 && weight<=9.6)){
                limit ="Normal Child";
            }else if((height== 68.5)&&( weight>9.6 )){
                limit ="Overnourished";
            }

            else if((height==69 )&&(weight>=7 && weight<=9.7 )){
                limit ="Normal Child";
            } else if((height== 69)&&( weight>9.7 )){
                limit ="Overnourished";
            }

            else if((height==69.5 )&&(weight>=7.1 && weight<=9.8)){
                limit ="Normal Child";
            }else if((height== 69.5)&&( weight>9.8)){
                limit ="Overnourished";
            }

            else if((height==70 )&&(weight>=7.2 && weight<=10)){
                limit ="Normal Child";
            }else if((height== 70)&&( weight>10 )){
                limit ="Overnourished";
            }

            else if((height== 70.5)&&(weight>=7.3 && weight<=10.1)){
                limit ="Normal Child";
            }else if((height== 70.5)&&( weight>10.1 )){
                limit ="Overnourished";
            }

            else if((height== 71)&&(weight>=7.4 && weight<=10.2)){
                limit ="Normal Child";
            }else if((height== 71)&&( weight>10.2 )){
                limit ="Overnourished";
            }

            else if((height==71.5 )&&(weight>=7.5 && weight<=10.4)){
                limit ="Normal Child";
            }else if((height== 71.5)&&( weight>10.4 )){
                limit ="Overnourished";
            }

            else if((height==72 )&&(weight>=7.6 && weight<=10.5)){
                limit ="Normal Child";
            }else if((height== 72)&&( weight>10.5 )){
                limit ="Overnourished";
            }

            else if((height==72.5 )&&(weight>=7.6 && weight<=10.6)){
                limit ="Normal Child";
            } else if((height== 72.5)&&( weight>10.6 )){
                limit ="Overnourished";
            }

            else if((height== 73)&&(weight>=7.7 && weight<=10.8)){
                limit ="Normal Child";
            }else if((height== 73)&&( weight>10.8 )){
                limit ="Overnourished";
            }

            else if((height== 73.5)&&(weight>=7.8 && weight<=10.9)){
                limit ="Normal Child";
            } else if((height== 73.5)&&( weight>10.9 )){
                limit ="Overnourished";
            }

            else if((height== 74)&&(weight>=7.9 && weight<=11)){
                limit ="Normal Child";
            }else if((height== 74)&&( weight>11 )){
                limit ="Overnourished";
            }

            else if((height==74.5 )&&(weight>=8.0 && weight<=11.2 )){
                limit ="Normal Child";
            }else if((height== 74.5)&&( weight>11.2 )){
                limit ="Overnourished";
            }

            else if((height== 75)&&(weight>=8.1 && weight<=11.3)){
                limit ="Normal Child";
            } else if((height== 75)&&( weight>11.3 )){
                limit ="Overnourished";
            }

            else if((height== 75.5)&&(weight>=8.2 && weight<=11.4)){
                limit ="Normal Child";
            } else if((height== 75.5)&&( weight>11.4 )){
                limit ="Overnourished";
            }

            else if((height== 76)&&(weight>=8.3 && weight<=11.5)){
                limit ="Normal Child";
            }else if((height== 76)&&( weight>11.5 )){
                limit ="Overnourished";
            }

            else if((height== 76.5)&&(weight>=8.3 && weight<=11.6 )){
                limit ="Normal Child";
            }else if((height== 76.5)&&( weight>11.6 )){
                limit ="Overnourished";
            }

            else if((height== 77)&&(weight>=8.4 && weight<=11.7)){
                limit ="Normal Child";
            }else if((height== 77)&&( weight>11.7 )){
                limit ="Overnourished";
            }

            else if((height== 77.5)&&(weight>=8.5 && weight<=11.9 )){
                limit ="Normal Child";
            }else if((height== 77.5)&&( weight>11.9 )){
                limit ="Overnourished";
            }

            else if((height== 78)&&(weight>=8.6 && weight<=12)){
                limit ="Normal Child";
            } else if((height== 78)&&( weight>12 )){
                limit ="Overnourished";
            }

            else if((height== 78.5)&&(weight>=8.7 && weight<=12.1)){
                limit ="Normal Child";
            } else if((height==78.5)&&( weight>12.1 )){
                limit ="Overnourished";
            }

            else if((height==79 )&&(weight>=8.7 && weight<=12.2)){
                limit ="Normal Child";
            }  else if((height==79)&&( weight>12.2)){
                limit ="Overnourished";
            }

            else if((height== 79.5)&&(weight>=8.8 && weight<=12.3)){
                limit ="Normal Child";
            }  else if((height==79.5)&&( weight>12.3 )){
                limit ="Overnourished";
            }

            else if((height== 80)&&(weight>=8.9 && weight<=12.4 )){
                limit ="Normal Child";
            }  else if((height==80)&&( weight>12.4 )){
                limit ="Overnourished";
            }

            else if((height== 80.5)&&(weight>=9 && weight<=12.5)){
                limit ="Normal Child";
            }  else if((height==80.5)&&( weight>12.5 )){
                limit ="Overnourished";
            }

            else if((height== 81)&&(weight>=9.1 && weight<=12.6)){
                limit ="Normal Child";
            }  else if((height==81)&&( weight>12.6 )){
                limit ="Overnourished";
            }

            else if((height== 81.5)&&(weight>=9.1&& weight<=12.7)){
                limit ="Normal Child";
            } else if((height==81.5)&&( weight>12.7 )){
                limit ="Overnourished";
            }

            else if((height== 82)&&(weight>=9.2 && weight<=12.8)){
                limit ="Normal Child";
            }  else if((height==82)&&( weight>12.8 )){
                limit ="Overnourished";
            }

            else if((height== 82.5)&&(weight>=9.3 && weight<=13)){
                limit ="Normal Child";
            }  else if((height==82.5)&&( weight>13 )){
                limit ="Overnourished";
            }

            else if((height== 83)&&(weight>=9.4 && weight<=13.1 )){
                limit ="Normal Child";
            } else if((height==83)&&( weight>13.1 )){
                limit ="Overnourished";
            }

            else if((height== 83.5)&&(weight>=9.5 && weight<=13.2)){
                limit ="Normal Child";
            }  else if((height==83.5)&&( weight>13.2)){
                limit ="Overnourished";
            }

            else if((height== 84)&&(weight>=9.6 && weight<=13.3)){
                limit ="Normal Child";
            }  else if((height==84)&&( weight>13.3)){
                limit ="Overnourished";
            }

            else if((height== 84.5)&&(weight>=9.7 && weight<=13.5)){
                limit ="Normal Child";
            }  else if((height==84.5)&&( weight>13.5 )){
                limit ="Overnourished";
            }

            else if((height== 85)&&(weight>=9.8 && weight<=13.6 )){
                limit ="Normal Child";
            } else if((height==85)&&( weight>13.6 )){
                limit ="Overnourished";
            }

            else if((height== 85.5)&&(weight>=9.9 && weight<=13.7 )){
                limit ="Normal Child";
            }else if((height==85.5)&&( weight>13.7 )){
                limit ="Overnourished";
            }

            else if((height== 86)&&(weight>=10 && weight<=13.9)){
                limit ="Normal Child";
            } else if((height==86)&&( weight>13.9 )){
                limit ="Overnourished";
            }

            else if((height== 86.5)&&(weight>=10.1 && weight<=14)){
                limit ="Normal Child";
            } else if((height==86.5)&&( weight>14)){
                limit ="Overnourished";
            }

            else if((height== 87)&&(weight>=10.2&& weight<=14.2)){
                limit ="Normal Child";
            } else if((height==87)&&( weight>14.2 )){
                limit ="Overnourished";
            }

            else if((height== 87.5)&&(weight>=10.4 && weight<=14.3)){
                limit ="Normal Child";
            } else if((height==87.5)&&( weight>14.3 )){
                limit ="Overnourished";
            }

            else if((height== 88)&&(weight>=10.5 && weight<=14.5)){
                limit ="Normal Child";
            } else if((height==88)&&( weight>14.5 )){
                limit ="Overnourished";
            }

            else if((height== 88.5)&&(weight>=10.6 && weight<=14.6)){
                limit ="Normal Child";
            }else if((height==88.5)&&( weight>14.6 )){
                limit ="Overnourished";
            }

            else if((height== 89)&&(weight>=10.7 && weight<=14.7)){
                limit ="Normal Child";
            }else if((height==89)&&( weight>14.7 )){
                limit ="Overnourished";
            }

            else if((height== 89.5)&&(weight>=10.8 && weight<=14.9)){
                limit ="Normal Child";
            } else if((height==89.5)&&( weight>14.9 )){
                limit ="Overnourished";
            }

            else if((height== 90)&&(weight>=10.9 && weight<=15)){
                limit ="Normal Child";
            } else if((height==90)&&( weight>15 )){
                limit ="Overnourished";
            }

            else if((height== 90.5)&&(weight>=11 && weight<=15.1)){
                limit ="Normal Child";
            }else if((height==90.5)&&( weight>15.1)){
                limit ="Overnourished";
            }


            else if((height== 91)&&(weight>=11.1 && weight<=15.3)){
                limit ="Normal Child";
            }else if((height==91)&&( weight>15.3 )){
                limit ="Overnourished";
            }

            else if((height== 91.5)&&(weight>=11.2 && weight<=15.4)){
                limit ="Normal Child";
            }else if((height==91.5)&&( weight>15.4 )){
                limit ="Overnourished";
            }

            else if((height== 92)&&(weight>=11.3 && weight<=15.6)){
                limit ="Normal Child";
            }else if((height==92)&&( weight>15.6 )){
                limit ="Overnourished";
            }

            else if((height== 92.5)&&(weight>=11.4 && weight<=15.7 )){
                limit ="Normal Child";
            } else if((height==92.5)&&( weight>15.7 )){
                limit ="Overnourished";
            }

            else if((height== 93)&&(weight>=11.5 && weight<=15.8)){
                limit ="Normal Child";
            } else if((height==93)&&( weight>15.8)){
                limit ="Overnourished";
            }

            else if((height== 93.5)&&(weight>=11.6 && weight<=16)){
                limit ="Normal Child";
            }else if((height==93.5)&&( weight>16 )){
                limit ="Overnourished";
            }

            else if((height== 94)&&(weight>=11.7 && weight<=16.1)){
                limit ="Normal Child";
            } else if((height==94)&&( weight>16.1 )){
                limit ="Overnourished";
            }

            else if((height== 94.5)&&(weight>=11.8 && weight<=16.3)){
                limit ="Normal Child";
            } else if((height==94.5)&&( weight>16.3 )){
                limit ="Overnourished";
            }

            else if((height== 95)&&(weight>=11.9 && weight<=16.4)){
                limit ="Normal Child";
            }else if((height==95)&&( weight>16.4 )){
                limit ="Overnourished";
            }

            else if((height== 95.5)&&(weight>=12 && weight<=16.5)){
                limit ="Normal Child";
            } else if((height==95.5)&&( weight>16.5 )){
                limit ="Overnourished";
            }

            else if((height== 96)&&(weight>=12.1 && weight<=16.7)){
                limit ="Normal Child";
            }else if((height==96)&&( weight>16.7 )){
                limit ="Overnourished";
            }

            else if((height== 96.5)&&(weight>=12.2 && weight<=16.8)){
                limit ="Normal Child";
            } else if((height==96.5)&&( weight>16.8 )){
                limit ="Overnourished";
            }

            else if((height==97 )&&(weight>=12.3 && weight<=17.0)){
                limit ="Normal Child";
            }else if((height==97)&&( weight>17.0 )){
                limit ="Overnourished";
            }

            else if((height== 97.5)&&(weight>=12.4 && weight<=17.1)){
                limit ="Normal Child";
            }else if((height==97.5)&&( weight>17.1 )){
                limit ="Overnourished";
            }

            else if((height== 98)&&(weight>=12.5 && weight<=17.3)){
                limit ="Normal Child";
            }else if((height==98)&&( weight>17.3 )){
                limit ="Overnourished";
            }

            else if((height== 98.5)&&(weight>=12.6 && weight<=17.5)){
                limit ="Normal Child";
            }else if((height==98.5)&&( weight>17.5 )){
                limit ="Overnourished";
            }

            else if((height== 99)&&(weight>=12.7 && weight<=17.6)){
                limit ="Normal Child";
            }else if((height==99)&&( weight>17.6 )){
                limit ="Overnourished";
            }

            else if((height== 99.5)&&(weight>=12.8 && weight<=17.8 )){
                limit ="Normal Child";
            }else if((height==99.5)&&( weight>17.8)){
                limit ="Overnourished";
            }

            else if((height== 100)&&(weight>=12.9 && weight<=18 )){
                limit ="Normal Child";
            }else if((height==100)&&( weight>18 )){
                limit ="Overnourished";
            } else if((height== 100.5)&&(weight>=13 && weight<=18.1)){
                limit ="Normal Child";
            }else if((height==100.5)&&( weight>18.1 )){
                limit ="Overnourished";
            }

            else if((height== 101)&&(weight>=13.2 && weight<=18.3)){
                limit ="Normal Child";
            }else if((height==101)&&( weight>18.3 )){
                limit ="Overnourished";
            }

            else if((height== 101.5)&&(weight>=13.3 && weight<=18.5)){
                limit ="Normal Child";
            }else if((height==101.5)&&( weight>18.5)){
                limit ="Overnourished";
            }

            else if((height== 102)&&(weight>=13.4 && weight<=18.7)){
                limit ="Normal Child";
            }else if((height==102)&&( weight>18.7)){
                limit ="Overnourished";
            }  else if((height== 102.5)&&(weight>=13.5 && weight<=18.8)){
                limit ="Normal Child";
            }else if((height==102.5)&&( weight>18.8 )){
                limit ="Overnourished";
            }

            else if((height== 103)&&(weight>=13.6 && weight<=19)){
                limit ="Normal Child";
            }else if((height==103)&&( weight>19 )){
                limit ="Overnourished";
            }

            else if((height== 103.5)&&(weight>=13.7 && weight<=19.2 )){
                limit ="Normal Child";
            }else if((height==103.5)&&( weight>19.2)){
                limit ="Overnourished";
            }

            else if((height== 104)&&(weight>=13.9 && weight<=19.4)){
                limit ="Normal Child";
            }else if((height==104)&&( weight>19.4 )){
                limit ="Overnourished";
            }

            else if((height== 104.5)&&(weight>=14 && weight<=19.6)){
                limit ="Normal Child";
            }else if((height==104.5)&&( weight>19.6 )){
                limit ="Overnourished";
            }

            else if((height== 105)&&(weight>=14.1 && weight<=19.8)){
                limit ="Normal Child";
            }else if((height==105)&&( weight>19.8 )){
                limit ="Overnourished";
            }

            else if((height== 105.5)&&(weight>=14.2 && weight<=20.0)){
                limit ="Normal Child";
            }else if((height==105.5)&&( weight>20.0 )){
                limit ="Overnourished";
            }

            else if((height== 106)&&(weight>=14.4 && weight<=20.2)){
                limit ="Normal Child";
            }else if((height==106)&&( weight>20.2 )){
                limit ="Overnourished";
            }  else if((height== 106.5)&&(weight>=14.5 && weight<=20.4)){
                limit ="Normal Child";
            } else if((height==106.5)&&( weight>20.4 )){
                limit ="Overnourished";
            }
            else if((height==107 )&&(weight>=14.6 && weight<=20.6)){
                limit ="Normal Child";
            }else if((height==107)&&( weight>20.6 )){
                limit ="Overnourished";
            }

            else if((height== 107.5)&&(weight>=14.7 && weight<=20.8)){
                limit ="Normal Child";
            }else if((height==107.5)&&( weight>20.8 )){
                limit ="Overnourished";
            }

            else if((height== 108)&&(weight>=14.9 && weight<=21.0)){
                limit ="Normal Child";
            }else if((height==108)&&( weight>21.0 )){
                limit ="Overnourished";
            }

            else if((height==108.5 )&&(weight>=15.0 && weight<=21.2)){
                limit ="Normal Child";
            }else if((height==108.5)&&( weight>21.2 )){
                limit ="Overnourished";
            }  else if((height== 109)&&(weight>=15.1 && weight<=21.4)){
                limit ="Normal Child";
            }else if((height==109)&&( weight>21.4 )){
                limit ="Overnourished";
            }

            else if((height== 109.5)&&(weight>=15.3 && weight<=21.7)){
                limit ="Normal Child";
            }else if((height==109.5)&&( weight>21.7 )){
                limit ="Overnourished";
            }

            else if((height==110 )&&(weight>=15.4 && weight<=21.9)){
                limit ="Normal Child";
            }else if((height==110)&&( weight>21.9 )){
                limit ="Overnourished";
            }
            else{limit="Wasted";}
        }
        else if(((years==2 || years==3)||(years==4))&&(months>=0 &&months<=12)){

            if((height== 65)&&(weight>=6.3 && weight<=8.8)){
                limit ="Normal Child";
            } else if((height==65)&&( weight>8.8 )){
                limit ="Overnourished";
            }

            else if((height== 65.5)&&(weight>=6.4 && weight<=8.94)){
                limit ="Normal Child";
            } else if((height==65.5)&&( weight>8.94 )){
                limit ="Overnourished";
            }

            else if((height== 66)&&(weight>=6.5 && weight<=9.08)){
                limit ="Normal Child";
            } else if((height==66)&&( weight>9.08 )){
                limit ="Overnourished";
            }

            else if((height== 66.5)&&(weight>=6.6 && weight<=9.22)){
                limit ="Normal Child";
            }  else if((height==66.5)&&( weight>9.22 )){
                limit ="Overnourished";
            }

            else if((height== 67)&&(weight>=6.7 && weight<=9.36)){
                limit ="Normal Child";
            }  else if((height==67)&&( weight>9.36 )){
                limit ="Overnourished";
            }

            else if((height==67.5 )&&(weight>=6.8 && weight<=9.5)){
                limit ="Normal Child";
            } else if((height==67.5)&&( weight>9.5)){
                limit ="Overnourished";
            }

            else if((height==68 )&&(weight>=6.9 && weight<=9.64)){
                limit ="Normal Child";
            }  else if((height==68)&&( weight>9.64 )){
                limit ="Overnourished";
            }

            else if((height==68.5 )&&(weight>=7 && weight<=9.78)){
                limit ="Normal Child";
            } else if((height==68.5)&&( weight>9.78 )){
                limit ="Overnourished";
            }

            else if((height==69 )&&(weight>=7.1 && weight<=9.92 )){
                limit ="Normal Child";
            }  else if((height==69)&&( weight>9.92 )){
                limit ="Overnourished";
            }

            else if((height==69.5 )&&(weight>=7.2 && weight<=10.06)){
                limit ="Normal Child";
            } else if((height==69.5)&&( weight>10.06 )){
                limit ="Overnourished";
            }

            else if((height==70 )&&(weight>=7.3 && weight<=10.2)){
                limit ="Normal Child";
            } else if((height==70)&&( weight>10.2 )){
                limit ="Overnourished";
            }

            else if((height== 70.5)&&(weight>=7.4 && weight<=10.32)){
                limit ="Normal Child";
            } else if((height==70.5)&&( weight>10.32 )){
                limit ="Overnourished";
            }

            else if((height== 71)&&(weight>=7.5 && weight<=10.44)){
                limit ="Normal Child";
            }else if((height==71)&&( weight>10.44 )){
                limit ="Overnourished";
            }

            else if((height==71.5 )&&(weight>=7.6 && weight<=10.56)){
                limit ="Normal Child";
            } else if((height==71.5)&&( weight>10.56 )){
                limit ="Overnourished";
            }

            else if((height==72 )&&(weight>=7.7 && weight<=10.68)){
                limit ="Normal Child";
            }else if((height==72)&&( weight>10.68 )){
                limit ="Overnourished";
            }

            else if((height==72.5 )&&(weight>=7.8 && weight<=10.8)){
                limit ="Normal Child";
            }else if((height==72.5)&&( weight>10.8 )){
                limit ="Overnourished";
            }

            else if((height== 73)&&(weight>=7.9 && weight<=10.92)){
                limit ="Normal Child";
            }else if((height==73)&&( weight>10.92 )){
                limit ="Overnourished";
            }

            else if((height== 73.5)&&(weight>=7.9 && weight<=11.04)){
                limit ="Normal Child";
            } else if((height==73.5)&&( weight>11.04 )){
                limit ="Overnourished";
            }

            else if((height== 74)&&(weight>=8.0&& weight<=11.16)){
                limit ="Normal Child";
            }else if((height==74)&&( weight>11.16 )){
                limit ="Overnourished";
            }

            else if((height==74.5 )&&(weight>=8.1 && weight<=11.28 )){
                limit ="Normal Child";
            }else if((height==74.5)&&( weight>11.28 )){
                limit ="Overnourished";
            }

            else if((height== 75)&&(weight>=8.2 && weight<=11.4)){
                limit ="Normal Child";
            } else if((height==75)&&( weight>11.4 )){
                limit ="Overnourished";
            }

            else if((height== 75.5)&&(weight>=8.3 && weight<=11.52)){
                limit ="Normal Child";
            }else if((height==75.5)&&( weight>11.52 )){
                limit ="Overnourished";
            }

            else if((height== 76)&&(weight>=8.4 && weight<=11.52)){
                limit ="Normal Child";
            } else if((height==76)&&( weight>11.52 )){
                limit ="Overnourished";
            }

            else if((height== 76.5)&&(weight>=8.5 && weight<=11.63 )){
                limit ="Normal Child";
            }else if((height==76.5)&&( weight>11.63 )){
                limit ="Overnourished";
            }

            else if((height== 77)&&(weight>=8.5 && weight<=11.88)){
                limit ="Normal Child";
            } else if((height==77)&&( weight>11.88 )){
                limit ="Overnourished";
            }

            else if((height== 77.5)&&(weight>=8.6 && weight<=12 )){
                limit ="Normal Child";
            } else if((height==77.5)&&( weight>12 )){
                limit ="Overnourished";
            }

            else if((height== 78)&&(weight>=8.7&& weight<=12.12)){
                limit ="Normal Child";
            }else if((height==78)&&( weight>12.2 )){
                limit ="Overnourished";
            }

            else if((height== 78.5)&&(weight>=8.8 && weight<=12.24)){
                limit ="Normal Child";
            }else if((height==78.5)&&( weight>12.24 )){
                limit ="Overnourished";
            }

            else if((height==79 )&&(weight>=8.8 && weight<=12.36)){
                limit ="Normal Child";
            } else if((height==79)&&( weight>12.36 )){
                limit ="Overnourished";
            }

            else if((height== 79.5)&&(weight>=8.9 && weight<=12.48)){
                limit ="Normal Child";
            } else if((height==79.5)&&( weight>12.2 )){
                limit ="Overnourished";
            }

            else if((height== 80)&&(weight>=9 && weight<=12.6 )){
                limit ="Normal Child";
            }else if((height==80)&&( weight>12.6 )){
                limit ="Overnourished";
            }

            else if((height== 80.5)&&(weight>=9.1 && weight<=12.72)){
                limit ="Normal Child";
            } else if((height==80.5)&&( weight>12.72 )){
                limit ="Overnourished";
            }

            else if((height== 81)&&(weight>=9.2 && weight<=12.84)){
                limit ="Normal Child";
            } else if((height==81)&&( weight>12.84 )){
                limit ="Overnourished";
            }

            else if((height== 81.5)&&(weight>=9.3 && weight<=12.96)){
                limit ="Normal Child";
            } else if((height==81.5)&&( weight>12.96 )){
                limit ="Overnourished";
            }

            else if((height== 82)&&(weight>=9.3&& weight<=13.08)){
                limit ="Normal Child";
            }else if((height==82)&&( weight>13.08 )){
                limit ="Overnourished";
            }

            else if((height== 82.5)&&(weight>=9.4 && weight<=13.2)){
                limit ="Normal Child";
            }else if((height==82.5)&&( weight>13.2 )){
                limit ="Overnourished";
            }

            else if((height== 83)&&(weight>=9.5&& weight<=13.32 )){
                limit ="Normal Child";
            }else if((height==83)&&( weight>13.32 )){
                limit ="Overnourished";
            }

            else if((height== 83.5)&&(weight>=9.6 && weight<=13.44)){
                limit ="Normal Child";
            } else if((height==83.5)&&( weight>13.44 )){
                limit ="Overnourished";
            }

            else if((height== 84)&&(weight>=9.7 && weight<=13.56)){
                limit ="Normal Child";
            }else if((height==84)&&( weight>13.56 )){
                limit ="Overnourished";
            }

            else if((height== 84.5)&&(weight>=9.9 && weight<=13.68)){
                limit ="Normal Child";
            } else if((height==84.5)&&( weight>13.68 )){
                limit ="Overnourished";
            }

            else if((height== 85)&&(weight>=10 && weight<=13.8 )){
                limit ="Normal Child";
            }else if((height==85)&&( weight>13.8 )){
                limit ="Overnourished";
            }

            else if((height== 85.5)&&(weight>=10.1 && weight<=13.94 )){
                limit ="Normal Child";
            }else if((height==85.5)&&( weight>13.94 )){
                limit ="Overnourished";
            }

            else if((height== 86)&&(weight>=10.2 && weight<=14.08)){
                limit ="Normal Child";
            } else if((height==86)&&( weight>14.08 )){
                limit ="Overnourished";
            }

            else if((height== 86.5)&&(weight>=10.3 && weight<=14.22)){
                limit ="Normal Child";
            }else if((height==86.5)&&( weight>14.44 )){
                limit ="Overnourished";
            }
            else if((height== 87)&&(weight>=10.4&& weight<=14.36)){
                limit ="Normal Child";
            }else if((height==87)&&( weight>14.36 )){
                limit ="Overnourished";
            }
            else if((height== 87.5)&&(weight>=10.5 && weight<=14.5)){
                limit ="Normal Child";
            } else if((height==87.5)&&( weight>14.5 )){
                limit ="Overnourished";
            }
            else if((height== 88)&&(weight>=10.6 && weight<=14.64)){
                limit ="Normal Child";
            }else if((height==88)&&( weight>14.64 )){
                limit ="Overnourished";
            }

            else if((height== 88.5)&&(weight>=10.7 && weight<=14.78)){
                limit ="Normal Child";
            }else if((height==88.5)&&( weight>14.78 )){
                limit ="Overnourished";
            }

            else if((height== 89)&&(weight>=10.8 && weight<=14.92)){
                limit ="Normal Child";
            } else if((height==89)&&( weight>14.92 )){
                limit ="Overnourished";
            }

            else if((height== 89.5)&&(weight>=10.9 && weight<=15.06)){
                limit ="Normal Child";
            } else if((height==89.5)&&( weight>15.06 )){
                limit ="Overnourished";
            }

            else if((height== 90)&&(weight>=11 && weight<=15.2)){
                limit ="Normal Child";
            }else if((height==90)&&( weight>15.2 )){
                limit ="Overnourished";
            }

            else if((height== 90.5)&&(weight>=11.1 && weight<=15.34)){
                limit ="Normal Child";
            }else if((height==90.5)&&( weight>15.34 )){
                limit ="Overnourished";
            }

            else if((height== 91)&&(weight>=11.2 && weight<=15.48)){
                limit ="Normal Child";
            }else if((height==91)&&( weight>15.48 )){
                limit ="Overnourished";
            }

            else if((height== 91.5)&&(weight>=11.3 && weight<=15.62)){
                limit ="Normal Child";
            }else if((height==91.5)&&( weight>15.62 )){
                limit ="Overnourished";
            }

            else if((height== 92)&&(weight>=11.4 && weight<=15.76)){
                limit ="Normal Child";
            }else if((height==92)&&( weight>15.76 )){
                limit ="Overnourished";
            }

            else if((height== 92.5)&&(weight>=11.5 && weight<=15.9 )){
                limit ="Normal Child";
            } else if((height==92.5)&&( weight>15.9 )){
                limit ="Overnourished";
            }

            else if((height== 93)&&(weight>=11.6 && weight<=16.04)){
                limit ="Normal Child";
            }else if((height==93)&&( weight>16.04 )){
                limit ="Overnourished";
            }

            else if((height== 93.5)&&(weight>=11.7 && weight<=16.18)){
                limit ="Normal Child";
            }else if((height==93.5)&&( weight>16.18 )){
                limit ="Overnourished";
            }

            else if((height== 94)&&(weight>=11.8 && weight<=16.32)){
                limit ="Normal Child";
            } else if((height==94)&&( weight>16.3 )){
                limit ="Overnourished";
            }

            else if((height== 94.5)&&(weight>=11.9 && weight<=16.46)){
                limit ="Normal Child";
            }else if((height==94.5)&&( weight>16.46 )){
                limit ="Overnourished";
            }

            else if((height== 95)&&(weight>=12 && weight<=16.6)){
                limit ="Normal Child";
            }else if((height==95)&&( weight>16.6 )){
                limit ="Overnourished";
            }

            else if((height== 95.5)&&(weight>=12.1 && weight<=16.76)){
                limit ="Normal Child";
            } else if((height==95.5)&&( weight>16.76 )){
                limit ="Overnourished";
            }

            else if((height== 96)&&(weight>=12.2 && weight<=16.92)){
                limit ="Normal Child";
            }else if((height==96)&&( weight>16.92 )){
                limit ="Overnourished";
            }

            else if((height== 96.5)&&(weight>=12.3 && weight<=17.08)){
                limit ="Normal Child";
            }else if((height==96.5)&&( weight>17.08 )){
                limit ="Overnourished";
            }

            else if((height==97 )&&(weight>=12.4 && weight<=17.24)){
                limit ="Normal Child";
            }else if((height==97)&&( weight>17.24 )){
                limit ="Overnourished";
            }

            else if((height== 97.5)&&(weight>=12.5 && weight<=17.4 )){
                limit ="Normal Child";
            }else if((height==97.5)&&( weight>17.4 )){
                limit ="Overnourished";
            }

            else if((height== 98)&&(weight>=12.6 && weight<=17.56)){
                limit ="Normal Child";
            }else if((height==98)&&( weight>17.56 )){
                limit ="Overnourished";
            }

            else if((height== 98.5)&&(weight>=12.8 && weight<=17.72)){
                limit ="Normal Child";
            }else if((height==98.5)&&( weight>17.72 )){
                limit ="Overnourished";
            }

            else if((height== 99)&&(weight>=12.9 && weight<=17.88)){
                limit ="Normal Child";
            }else if((height==99)&&( weight>17.88 )){
                limit ="Overnourished";
            }

            else if((height== 99.5)&&(weight>=13 && weight<=18.04 )){
                limit ="Normal Child";
            }else if((height==99.5)&&( weight>18.04 )){
                limit ="Overnourished";
            }

            else if((height== 100)&&(weight>=13.1 && weight<=18.2 )){
                limit ="Normal Child";
            }else if((height==100)&&( weight>18.2 )){
                limit ="Overnourished";
            }

            else if((height== 100.5)&&(weight>=13.2 && weight<=18.39)){
                limit ="Normal Child";
            }else if((height==100.5)&&( weight>18.39 )){
                limit ="Overnourished";
            }

            else if((height== 101)&&(weight>=13.3 && weight<=18.58)){
                limit ="Normal Child";
            }else if((height==101)&&( weight>18.58 )){
                limit ="Overnourished";
            }

            else if((height== 101.5)&&(weight>=13.4 && weight<=18.77)){
                limit ="Normal Child";
            }else if((height==101.5)&&( weight>18.77)){
                limit ="Overnourished";
            }

            else if((height== 102)&&(weight>=13.6 && weight<=18.96)){
                limit ="Normal Child";
            }else if((height==102)&&( weight>18.96 )){
                limit ="Overnourished";
            }

            else if((height== 102.5)&&(weight>=13.7 && weight<=19.15)){
                limit ="Normal Child";
            }else if((height==102.5)&&( weight>19.15 )){
                limit ="Overnourished";
            }

            else if((height== 103)&&(weight>=13.8 && weight<=19.34)){
                limit ="Normal Child";
            }else if((height==103)&&( weight>19.34 )){
                limit ="Overnourished";
            }

            else if((height== 103.5)&&(weight>=13.9 && weight<=19.53 )){
                limit ="Normal Child";
            }else if((height==103.5)&&( weight>19.53 )){
                limit ="Overnourished";
            }

            else if((height== 104)&&(weight>=14 && weight<=19.72)){
                limit ="Normal Child";
            }else if((height==104)&&( weight>19.72 )){
                limit ="Overnourished";
            }

            else if((height== 104.5)&&(weight>=14.2&& weight<=19.91)){
                limit ="Normal Child";
            }else if((height==104.5)&&( weight>19.91 )){
                limit ="Overnourished";
            }

            else if((height== 105)&&(weight>=14.3 && weight<=20.1)){
                limit ="Normal Child";
            }else if((height==105)&&( weight>20.1 )){
                limit ="Overnourished";
            }

            else if((height== 105.5)&&(weight>=14.4 && weight<=20.31)){
                limit ="Normal Child";
            }else if((height==105.5)&&( weight>20.31 )){
                limit ="Overnourished";
            }

            else if((height== 106)&&(weight>=14.5 && weight<=20.52)){
                limit ="Normal Child";
            }else if((height==106)&&( weight>20.52 )){
                limit ="Overnourished";
            }

            else if((height== 106.5)&&(weight>=14.7 && weight<=20.73)){
                limit ="Normal Child";
            }else if((height==106.5)&&( weight>20.73 )){
                limit ="Overnourished";
            }

            else if((height==107 )&&(weight>=14.8 && weight<=20.94)){
                limit ="Normal Child";
            }else if((height==107)&&( weight>20.94 )){
                limit ="Overnourished";
            }

            else if((height== 107.5)&&(weight>=14.9 && weight<=21.15)){
                limit ="Normal Child";
            }else if((height==107.5)&&( weight>21.15 )){
                limit ="Overnourished";
            }

            else if((height== 108)&&(weight>=15.1 && weight<=21.36)){
                limit ="Normal Child";
            }else if((height==108)&&( weight>21.36 )){
                limit ="Overnourished";
            }

            else if((height==108.5 )&&(weight>=15.2 && weight<=21.57)){
                limit ="Normal Child";
            }else if((height==108.5)&&( weight>21.57 )){
                limit ="Overnourished";
            }

            else if((height== 109)&&(weight>=15.3 && weight<=21.78)){
                limit ="Normal Child";
            }else if((height==109)&&( weight>21.78 )){
                limit ="Overnourished";
            }

            else if((height== 109.5)&&(weight>=15.5 && weight<=21.99)){
                limit ="Normal Child";
            }else if((height==109.5)&&( weight>21.99 )){
                limit ="Overnourished";
            }

            else if((height==110 )&&(weight>=15.6 && weight<=22.2)){
                limit ="Normal Child";
            }else if((height==110)&&( weight>22.2 )){
                limit ="Overnourished";
            }

            else if((height== 110.5)&&(weight>=15.8 && weight<=22.44 )){
                limit ="Normal Child";
            }else if((height==110.5)&&( weight>22.44 )){
                limit ="Overnourished";
            }


            else if((height== 111)&&(weight>=15.9 && weight<=22.68)){
                limit ="Normal Child";
            }else if((height==111)&&( weight>22.68 )){
                limit ="Overnourished";
            }


            else if((height== 111.5)&&(weight>=16.0 && weight<=22.92)){
                limit ="Normal Child";
            }else if((height==111.5)&&( weight>22.92 )){
                limit ="Overnourished";
            }

            else if((height== 112)&&(weight>=16.2 && weight<=23.16)){
                limit ="Normal Child";
            }else if((height==112)&&( weight>23.16 )){
                limit ="Overnourished";
            }

            else if((height== 112.5)&&(weight>=16.3 && weight<=23.4)){
                limit ="Normal Child";
            }else if((height==112.5)&&( weight>23.4 )){
                limit ="Overnourished";
            }

            else if((height== 113)&&(weight>=16.5 && weight<=23.64)){
                limit ="Normal Child";
            }else if((height==113)&&( weight>23.64 )){
                limit ="Overnourished";
            }

            else if((height== 113.5)&&(weight>=16.6 && weight<=23.88)){
                limit ="Normal Child";
            }else if((height==113.5)&&( weight>23.88 )){
                limit ="Overnourished";
            }

            else if((height== 114)&&(weight>=16.8 && weight<=24.12)){
                limit ="Normal Child";
            }else if((height==114)&&( weight>24.12 )){
                limit ="Overnourished";
            }

            else if((height== 114.5)&&(weight>=16.9 && weight<=24.36)){
                limit ="Normal Child";
            }else if((height==114.5)&&( weight>24.36 )){
                limit ="Overnourished";
            }

            else if((height== 115)&&(weight>=17.1 && weight<=24.6)){
                limit ="Normal Child";
            }else if((height==115)&&( weight>24.6 )){
                limit ="Overnourished";
            }

            else if((height== 115.5)&&(weight>=17.2 && weight<=24.86)){
                limit ="Normal Child";
            }else if((height==115.5)&&( weight>24.86 )){
                limit ="Overnourished";
            }

            else if((height== 116)&&(weight>=17.4 && weight<=25.12)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>25.12 )){
                limit ="Overnourished";
            }

            else if((height== 116.5)&&(weight>=17.5 && weight<=25.38)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>25.38 )){
                limit ="Overnourished";
            }

            else if((height== 117)&&(weight>=17.7 && weight<=25.64)){
                limit ="Normal Child";
            }else if((height==117)&&( weight>25.64 )){
                limit ="Overnourished";
            }

            else if((height== 117.5)&&(weight>=17.9 && weight<=25.9)){
                limit ="Normal Child";
            }else if((height==117.5)&&( weight>25.9 )){
                limit ="Overnourished";
            }

            else if((height== 118)&&(weight>=18 && weight<=26.16)){
                limit ="Normal Child";
            }else if((height==118)&&( weight>26.16 )){
                limit ="Overnourished";
            }

            else if((height== 118.5)&&(weight>=18.2 && weight<=26.42)){
                limit ="Normal Child";
            }else if((height==118.5)&&( weight>26.42 )){
                limit ="Overnourished";
            }

            else if((height== 119)&&(weight>=18.3 && weight<=26.68)){
                limit ="Normal Child";
            }else if((height==119)&&( weight>26.68 )){
                limit ="Overnourished";
            }

            else if((height== 119.5)&&(weight>=18.5 && weight<=26.94)){
                limit ="Normal Child";
            }else if((height==119.5)&&( weight>26.94 )){
                limit ="Overnourished";
            }

            else if((height== 120)&&(weight>=18.6 && weight<=27.2)){
                limit ="Normal Child";
            }else if((height==120)&&( weight>27.2 )){
                limit ="Overnourished";
            }

            else {limit="Wasted";}
        }
        else if((years==5)&&(months==0 )){
            if((height== 65)&&(weight>=6.3 && weight<=8.8)){
                limit ="Normal Child";
            } else if((height==65)&&( weight>8.8 )){
                limit ="Overnourished";
            }

            else if((height== 65.5)&&(weight>=6.4 && weight<=8.94)){
                limit ="Normal Child";
            } else if((height==65.5)&&( weight>8.94 )){
                limit ="Overnourished";
            }

            else if((height== 66)&&(weight>=6.5 && weight<=9.08)){
                limit ="Normal Child";
            } else if((height==66)&&( weight>9.08 )){
                limit ="Overnourished";
            }

            else if((height== 66.5)&&(weight>=6.6 && weight<=9.22)){
                limit ="Normal Child";
            }  else if((height==66.5)&&( weight>9.22 )){
                limit ="Overnourished";
            }

            else if((height== 67)&&(weight>=6.7 && weight<=9.36)){
                limit ="Normal Child";
            }  else if((height==67)&&( weight>9.36 )){
                limit ="Overnourished";
            }

            else if((height==67.5 )&&(weight>=6.8 && weight<=9.5)){
                limit ="Normal Child";
            } else if((height==67.5)&&( weight>9.5)){
                limit ="Overnourished";
            }

            else if((height==68 )&&(weight>=6.9 && weight<=9.64)){
                limit ="Normal Child";
            }  else if((height==68)&&( weight>9.64 )){
                limit ="Overnourished";
            }

            else if((height==68.5 )&&(weight>=7 && weight<=9.78)){
                limit ="Normal Child";
            } else if((height==68.5)&&( weight>9.78 )){
                limit ="Overnourished";
            }

            else if((height==69 )&&(weight>=7.1 && weight<=9.92 )){
                limit ="Normal Child";
            }  else if((height==69)&&( weight>9.92 )){
                limit ="Overnourished";
            }

            else if((height==69.5 )&&(weight>=7.2 && weight<=10.06)){
                limit ="Normal Child";
            } else if((height==69.5)&&( weight>10.06 )){
                limit ="Overnourished";
            }

            else if((height==70 )&&(weight>=7.3 && weight<=10.2)){
                limit ="Normal Child";
            } else if((height==70)&&( weight>10.2 )){
                limit ="Overnourished";
            }

            else if((height== 70.5)&&(weight>=7.4 && weight<=10.32)){
                limit ="Normal Child";
            } else if((height==70.5)&&( weight>10.32 )){
                limit ="Overnourished";
            }

            else if((height== 71)&&(weight>=7.5 && weight<=10.44)){
                limit ="Normal Child";
            }else if((height==71)&&( weight>10.44 )){
                limit ="Overnourished";
            }

            else if((height==71.5 )&&(weight>=7.6 && weight<=10.56)){
                limit ="Normal Child";
            } else if((height==71.5)&&( weight>10.56 )){
                limit ="Overnourished";
            }

            else if((height==72 )&&(weight>=7.7 && weight<=10.68)){
                limit ="Normal Child";
            }else if((height==72)&&( weight>10.68 )){
                limit ="Overnourished";
            }

            else if((height==72.5 )&&(weight>=7.8 && weight<=10.8)){
                limit ="Normal Child";
            }else if((height==72.5)&&( weight>10.8 )){
                limit ="Overnourished";
            }

            else if((height== 73)&&(weight>=7.9 && weight<=10.92)){
                limit ="Normal Child";
            }else if((height==73)&&( weight>10.92 )){
                limit ="Overnourished";
            }

            else if((height== 73.5)&&(weight>=7.9 && weight<=11.04)){
                limit ="Normal Child";
            } else if((height==73.5)&&( weight>11.04 )){
                limit ="Overnourished";
            }

            else if((height== 74)&&(weight>=8.0&& weight<=11.16)){
                limit ="Normal Child";
            }else if((height==74)&&( weight>11.16 )){
                limit ="Overnourished";
            }

            else if((height==74.5 )&&(weight>=8.1 && weight<=11.28 )){
                limit ="Normal Child";
            }else if((height==74.5)&&( weight>11.28 )){
                limit ="Overnourished";
            }

            else if((height== 75)&&(weight>=8.2 && weight<=11.4)){
                limit ="Normal Child";
            } else if((height==75)&&( weight>11.4 )){
                limit ="Overnourished";
            }

            else if((height== 75.5)&&(weight>=8.3 && weight<=11.52)){
                limit ="Normal Child";
            }else if((height==75.5)&&( weight>11.52 )){
                limit ="Overnourished";
            }

            else if((height== 76)&&(weight>=8.4 && weight<=11.52)){
                limit ="Normal Child";
            } else if((height==76)&&( weight>11.52 )){
                limit ="Overnourished";
            }

            else if((height== 76.5)&&(weight>=8.5 && weight<=11.63 )){
                limit ="Normal Child";
            }else if((height==76.5)&&( weight>11.63 )){
                limit ="Overnourished";
            }

            else if((height== 77)&&(weight>=8.5 && weight<=11.88)){
                limit ="Normal Child";
            } else if((height==77)&&( weight>11.88 )){
                limit ="Overnourished";
            }

            else if((height== 77.5)&&(weight>=8.6 && weight<=12 )){
                limit ="Normal Child";
            } else if((height==77.5)&&( weight>12 )){
                limit ="Overnourished";
            }

            else if((height== 78)&&(weight>=8.7&& weight<=12.12)){
                limit ="Normal Child";
            }else if((height==78)&&( weight>12.2 )){
                limit ="Overnourished";
            }

            else if((height== 78.5)&&(weight>=8.8 && weight<=12.24)){
                limit ="Normal Child";
            }else if((height==78.5)&&( weight>12.24 )){
                limit ="Overnourished";
            }

            else if((height==79 )&&(weight>=8.8 && weight<=12.36)){
                limit ="Normal Child";
            } else if((height==79)&&( weight>12.36 )){
                limit ="Overnourished";
            }

            else if((height== 79.5)&&(weight>=8.9 && weight<=12.48)){
                limit ="Normal Child";
            } else if((height==79.5)&&( weight>12.2 )){
                limit ="Overnourished";
            }

            else if((height== 80)&&(weight>=9 && weight<=12.6 )){
                limit ="Normal Child";
            }else if((height==80)&&( weight>12.6 )){
                limit ="Overnourished";
            }

            else if((height== 80.5)&&(weight>=9.1 && weight<=12.72)){
                limit ="Normal Child";
            } else if((height==80.5)&&( weight>12.72 )){
                limit ="Overnourished";
            }

            else if((height== 81)&&(weight>=9.2 && weight<=12.84)){
                limit ="Normal Child";
            } else if((height==81)&&( weight>12.84 )){
                limit ="Overnourished";
            }

            else if((height== 81.5)&&(weight>=9.3 && weight<=12.96)){
                limit ="Normal Child";
            } else if((height==81.5)&&( weight>12.96 )){
                limit ="Overnourished";
            }

            else if((height== 82)&&(weight>=9.3&& weight<=13.08)){
                limit ="Normal Child";
            }else if((height==82)&&( weight>13.08 )){
                limit ="Overnourished";
            }

            else if((height== 82.5)&&(weight>=9.4 && weight<=13.2)){
                limit ="Normal Child";
            }else if((height==82.5)&&( weight>13.2 )){
                limit ="Overnourished";
            }

            else if((height== 83)&&(weight>=9.5&& weight<=13.32 )){
                limit ="Normal Child";
            }else if((height==83)&&( weight>13.32 )){
                limit ="Overnourished";
            }

            else if((height== 83.5)&&(weight>=9.6 && weight<=13.44)){
                limit ="Normal Child";
            } else if((height==83.5)&&( weight>13.44 )){
                limit ="Overnourished";
            }

            else if((height== 84)&&(weight>=9.7 && weight<=13.56)){
                limit ="Normal Child";
            }else if((height==84)&&( weight>13.56 )){
                limit ="Overnourished";
            }

            else if((height== 84.5)&&(weight>=9.9 && weight<=13.68)){
                limit ="Normal Child";
            } else if((height==84.5)&&( weight>13.68 )){
                limit ="Overnourished";
            }

            else if((height== 85)&&(weight>=10 && weight<=13.8 )){
                limit ="Normal Child";
            }else if((height==85)&&( weight>13.8 )){
                limit ="Overnourished";
            }

            else if((height== 85.5)&&(weight>=10.1 && weight<=13.94 )){
                limit ="Normal Child";
            }else if((height==85.5)&&( weight>13.94 )){
                limit ="Overnourished";
            }

            else if((height== 86)&&(weight>=10.2 && weight<=14.08)){
                limit ="Normal Child";
            } else if((height==86)&&( weight>14.08 )){
                limit ="Overnourished";
            }

            else if((height== 86.5)&&(weight>=10.3 && weight<=14.22)){
                limit ="Normal Child";
            }else if((height==86.5)&&( weight>14.44 )){
                limit ="Overnourished";
            }
            else if((height== 87)&&(weight>=10.4&& weight<=14.36)){
                limit ="Normal Child";
            }else if((height==87)&&( weight>14.36 )){
                limit ="Overnourished";
            }
            else if((height== 87.5)&&(weight>=10.5 && weight<=14.5)){
                limit ="Normal Child";
            } else if((height==87.5)&&( weight>14.5 )){
                limit ="Overnourished";
            }
            else if((height== 88)&&(weight>=10.6 && weight<=14.64)){
                limit ="Normal Child";
            }else if((height==88)&&( weight>14.64 )){
                limit ="Overnourished";
            }

            else if((height== 88.5)&&(weight>=10.7 && weight<=14.78)){
                limit ="Normal Child";
            }else if((height==88.5)&&( weight>14.78 )){
                limit ="Overnourished";
            }

            else if((height== 89)&&(weight>=10.8 && weight<=14.92)){
                limit ="Normal Child";
            } else if((height==89)&&( weight>14.92 )){
                limit ="Overnourished";
            }

            else if((height== 89.5)&&(weight>=10.9 && weight<=15.06)){
                limit ="Normal Child";
            } else if((height==89.5)&&( weight>15.06 )){
                limit ="Overnourished";
            }

            else if((height== 90)&&(weight>=11 && weight<=15.2)){
                limit ="Normal Child";
            }else if((height==90)&&( weight>15.2 )){
                limit ="Overnourished";
            }

            else if((height== 90.5)&&(weight>=11.1 && weight<=15.34)){
                limit ="Normal Child";
            }else if((height==90.5)&&( weight>15.34 )){
                limit ="Overnourished";
            }

            else if((height== 91)&&(weight>=11.2 && weight<=15.48)){
                limit ="Normal Child";
            }else if((height==91)&&( weight>15.48 )){
                limit ="Overnourished";
            }

            else if((height== 91.5)&&(weight>=11.3 && weight<=15.62)){
                limit ="Normal Child";
            }else if((height==91.5)&&( weight>15.62 )){
                limit ="Overnourished";
            }

            else if((height== 92)&&(weight>=11.4 && weight<=15.76)){
                limit ="Normal Child";
            }else if((height==92)&&( weight>15.76 )){
                limit ="Overnourished";
            }

            else if((height== 92.5)&&(weight>=11.5 && weight<=15.9 )){
                limit ="Normal Child";
            } else if((height==92.5)&&( weight>15.9 )){
                limit ="Overnourished";
            }

            else if((height== 93)&&(weight>=11.6 && weight<=16.04)){
                limit ="Normal Child";
            }else if((height==93)&&( weight>16.04 )){
                limit ="Overnourished";
            }

            else if((height== 93.5)&&(weight>=11.7 && weight<=16.18)){
                limit ="Normal Child";
            }else if((height==93.5)&&( weight>16.18 )){
                limit ="Overnourished";
            }

            else if((height== 94)&&(weight>=11.8 && weight<=16.32)){
                limit ="Normal Child";
            } else if((height==94)&&( weight>16.3 )){
                limit ="Overnourished";
            }

            else if((height== 94.5)&&(weight>=11.9 && weight<=16.46)){
                limit ="Normal Child";
            }else if((height==94.5)&&( weight>16.46 )){
                limit ="Overnourished";
            }

            else if((height== 95)&&(weight>=12 && weight<=16.6)){
                limit ="Normal Child";
            }else if((height==95)&&( weight>16.6 )){
                limit ="Overnourished";
            }

            else if((height== 95.5)&&(weight>=12.1 && weight<=16.76)){
                limit ="Normal Child";
            } else if((height==95.5)&&( weight>16.76 )){
                limit ="Overnourished";
            }

            else if((height== 96)&&(weight>=12.2 && weight<=16.92)){
                limit ="Normal Child";
            }else if((height==96)&&( weight>16.92 )){
                limit ="Overnourished";
            }

            else if((height== 96.5)&&(weight>=12.3 && weight<=17.08)){
                limit ="Normal Child";
            }else if((height==96.5)&&( weight>17.08 )){
                limit ="Overnourished";
            }

            else if((height==97 )&&(weight>=12.4 && weight<=17.24)){
                limit ="Normal Child";
            }else if((height==97)&&( weight>17.24 )){
                limit ="Overnourished";
            }

            else if((height== 97.5)&&(weight>=12.5 && weight<=17.4 )){
                limit ="Normal Child";
            }else if((height==97.5)&&( weight>17.4 )){
                limit ="Overnourished";
            }

            else if((height== 98)&&(weight>=12.6 && weight<=17.56)){
                limit ="Normal Child";
            }else if((height==98)&&( weight>17.56 )){
                limit ="Overnourished";
            }

            else if((height== 98.5)&&(weight>=12.8 && weight<=17.72)){
                limit ="Normal Child";
            }else if((height==98.5)&&( weight>17.72 )){
                limit ="Overnourished";
            }

            else if((height== 99)&&(weight>=12.9 && weight<=17.88)){
                limit ="Normal Child";
            }else if((height==99)&&( weight>17.88 )){
                limit ="Overnourished";
            }

            else if((height== 99.5)&&(weight>=13 && weight<=18.04 )){
                limit ="Normal Child";
            }else if((height==99.5)&&( weight>18.04 )){
                limit ="Overnourished";
            }

            else if((height== 100)&&(weight>=13.1 && weight<=18.2 )){
                limit ="Normal Child";
            }else if((height==100)&&( weight>18.2 )){
                limit ="Overnourished";
            }

            else if((height== 100.5)&&(weight>=13.2 && weight<=18.39)){
                limit ="Normal Child";
            }else if((height==100.5)&&( weight>18.39 )){
                limit ="Overnourished";
            }

            else if((height== 101)&&(weight>=13.3 && weight<=18.58)){
                limit ="Normal Child";
            }else if((height==101)&&( weight>18.58 )){
                limit ="Overnourished";
            }

            else if((height== 101.5)&&(weight>=13.4 && weight<=18.77)){
                limit ="Normal Child";
            }else if((height==101.5)&&( weight>18.77)){
                limit ="Overnourished";
            }

            else if((height== 102)&&(weight>=13.6 && weight<=18.96)){
                limit ="Normal Child";
            }else if((height==102)&&( weight>18.96 )){
                limit ="Overnourished";
            }

            else if((height== 102.5)&&(weight>=13.7 && weight<=19.15)){
                limit ="Normal Child";
            }else if((height==102.5)&&( weight>19.15 )){
                limit ="Overnourished";
            }

            else if((height== 103)&&(weight>=13.8 && weight<=19.34)){
                limit ="Normal Child";
            }else if((height==103)&&( weight>19.34 )){
                limit ="Overnourished";
            }

            else if((height== 103.5)&&(weight>=13.9 && weight<=19.53 )){
                limit ="Normal Child";
            }else if((height==103.5)&&( weight>19.53 )){
                limit ="Overnourished";
            }

            else if((height== 104)&&(weight>=14 && weight<=19.72)){
                limit ="Normal Child";
            }else if((height==104)&&( weight>19.72 )){
                limit ="Overnourished";
            }

            else if((height== 104.5)&&(weight>=14.2&& weight<=19.91)){
                limit ="Normal Child";
            }else if((height==104.5)&&( weight>19.91 )){
                limit ="Overnourished";
            }

            else if((height== 105)&&(weight>=14.3 && weight<=20.1)){
                limit ="Normal Child";
            }else if((height==105)&&( weight>20.1 )){
                limit ="Overnourished";
            }

            else if((height== 105.5)&&(weight>=14.4 && weight<=20.31)){
                limit ="Normal Child";
            }else if((height==105.5)&&( weight>20.31 )){
                limit ="Overnourished";
            }

            else if((height== 106)&&(weight>=14.5 && weight<=20.52)){
                limit ="Normal Child";
            }else if((height==106)&&( weight>20.52 )){
                limit ="Overnourished";
            }

            else if((height== 106.5)&&(weight>=14.7 && weight<=20.73)){
                limit ="Normal Child";
            }else if((height==106.5)&&( weight>20.73 )){
                limit ="Overnourished";
            }

            else if((height==107 )&&(weight>=14.8 && weight<=20.94)){
                limit ="Normal Child";
            }else if((height==107)&&( weight>20.94 )){
                limit ="Overnourished";
            }

            else if((height== 107.5)&&(weight>=14.9 && weight<=21.15)){
                limit ="Normal Child";
            }else if((height==107.5)&&( weight>21.15 )){
                limit ="Overnourished";
            }

            else if((height== 108)&&(weight>=15.1 && weight<=21.36)){
                limit ="Normal Child";
            }else if((height==108)&&( weight>21.36 )){
                limit ="Overnourished";
            }

            else if((height==108.5 )&&(weight>=15.2 && weight<=21.57)){
                limit ="Normal Child";
            }else if((height==108.5)&&( weight>21.57 )){
                limit ="Overnourished";
            }

            else if((height== 109)&&(weight>=15.3 && weight<=21.78)){
                limit ="Normal Child";
            }else if((height==109)&&( weight>21.78 )){
                limit ="Overnourished";
            }

            else if((height== 109.5)&&(weight>=15.5 && weight<=21.99)){
                limit ="Normal Child";
            }else if((height==109.5)&&( weight>21.99 )){
                limit ="Overnourished";
            }

            else if((height==110 )&&(weight>=15.6 && weight<=22.2)){
                limit ="Normal Child";
            }else if((height==110)&&( weight>22.2 )){
                limit ="Overnourished";
            }

            else if((height== 110.5)&&(weight>=15.8 && weight<=22.44 )){
                limit ="Normal Child";
            }else if((height==110.5)&&( weight>22.44 )){
                limit ="Overnourished";
            }


            else if((height== 111)&&(weight>=15.9 && weight<=22.68)){
                limit ="Normal Child";
            }else if((height==111)&&( weight>22.68 )){
                limit ="Overnourished";
            }


            else if((height== 111.5)&&(weight>=16.0 && weight<=22.92)){
                limit ="Normal Child";
            }else if((height==111.5)&&( weight>22.92 )){
                limit ="Overnourished";
            }

            else if((height== 112)&&(weight>=16.2 && weight<=23.16)){
                limit ="Normal Child";
            }else if((height==112)&&( weight>23.16 )){
                limit ="Overnourished";
            }

            else if((height== 112.5)&&(weight>=16.3 && weight<=23.4)){
                limit ="Normal Child";
            }else if((height==112.5)&&( weight>23.4 )){
                limit ="Overnourished";
            }

            else if((height== 113)&&(weight>=16.5 && weight<=23.64)){
                limit ="Normal Child";
            }else if((height==113)&&( weight>23.64 )){
                limit ="Overnourished";
            }

            else if((height== 113.5)&&(weight>=16.6 && weight<=23.88)){
                limit ="Normal Child";
            }else if((height==113.5)&&( weight>23.88 )){
                limit ="Overnourished";
            }

            else if((height== 114)&&(weight>=16.8 && weight<=24.12)){
                limit ="Normal Child";
            }else if((height==114)&&( weight>24.12 )){
                limit ="Overnourished";
            }

            else if((height== 114.5)&&(weight>=16.9 && weight<=24.36)){
                limit ="Normal Child";
            }else if((height==114.5)&&( weight>24.36 )){
                limit ="Overnourished";
            }

            else if((height== 115)&&(weight>=17.1 && weight<=24.6)){
                limit ="Normal Child";
            }else if((height==115)&&( weight>24.6 )){
                limit ="Overnourished";
            }

            else if((height== 115.5)&&(weight>=17.2 && weight<=24.86)){
                limit ="Normal Child";
            }else if((height==115.5)&&( weight>24.86 )){
                limit ="Overnourished";
            }

            else if((height== 116)&&(weight>=17.4 && weight<=25.12)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>25.12 )){
                limit ="Overnourished";
            }

            else if((height== 116.5)&&(weight>=17.5 && weight<=25.38)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>25.38 )){
                limit ="Overnourished";
            }

            else if((height== 117)&&(weight>=17.7 && weight<=25.64)){
                limit ="Normal Child";
            }else if((height==117)&&( weight>25.64 )){
                limit ="Overnourished";
            }

            else if((height== 117.5)&&(weight>=17.9 && weight<=25.9)){
                limit ="Normal Child";
            }else if((height==117.5)&&( weight>25.9 )){
                limit ="Overnourished";
            }

            else if((height== 118)&&(weight>=18 && weight<=26.16)){
                limit ="Normal Child";
            }else if((height==118)&&( weight>26.16 )){
                limit ="Overnourished";
            }

            else if((height== 118.5)&&(weight>=18.2 && weight<=26.42)){
                limit ="Normal Child";
            }else if((height==118.5)&&( weight>26.42 )){
                limit ="Overnourished";
            }

            else if((height== 119)&&(weight>=18.3 && weight<=26.68)){
                limit ="Normal Child";
            }else if((height==119)&&( weight>26.68 )){
                limit ="Overnourished";
            }

            else if((height== 119.5)&&(weight>=18.5 && weight<=26.94)){
                limit ="Normal Child";
            }else if((height==119.5)&&( weight>26.94 )){
                limit ="Overnourished";
            }

            else if((height== 120)&&(weight>=18.6 && weight<=27.2)){
                limit ="Normal Child";
            }else if((height==120)&&( weight>27.2 )){
                limit ="Overnourished";
            }

            else {limit="Wasted";}



        }

        return limit;


    }
    public String femaleWasting(double height,double weight, int years, int months){

        String limit="";


        if ((years==1 || years==0) &&(months>=0 && months<=12)) {


            if ((height==45)&&(weight>=2.1 && weight<=3 )){
                limit = "Normal Child";
            } else if ((height==45)&&(weight>3 )){
                limit = "Overnourished";
            }
            else if((height==45.5)&&(weight>=2.1 && weight<=3.1 )){
                limit = "Normal Child";
            }
            else if ((height==45.5)&&(weight>3.1 )){
                limit = "Overnourished";
            }

            else if((height==46)&&(weight>=2.2 && weight<=3.2)){
                limit ="Normal Child";
            }
            else if ((height==46)&&(weight>3.2 )){
                limit = "Overnourished";
            }
            else if((height==46.5 )&&(weight>=2.3 && weight<=3.3 )){
                limit ="Normal Child";
            }
            else if ((height==46.5)&&(weight>3.3 )){
                limit = "Overnourished";
            }
            else if((height==47 )&&(weight>=2.4&& weight<=3.4)){
                limit ="Normal Child";
            } else if ((height==47)&&(weight>3.4 )){
                limit = "Overnourished";
            }

            else if((height== 47.5)&&(weight>=2.4 && weight<=3.5)){
                limit ="Normal Child";
            } else if ((height==47.5)&&(weight>3.5 )){
                limit = "Overnourished";
            }

            else if((height== 48)&&(weight>=2.5 && weight<=3.6 )){
                limit ="Normal Child";
            }  else if((height== 48)&&( weight>3.6 )){
                limit ="Overnourished";
            }
            else if((height==48.5 )&&(weight>=2.6 && weight<=3.7)){
                limit ="Normal Child";
            }  else if((height== 48.5)&&( weight>3.7 )){
                limit ="Overnourished";
            }

            else if((height==49 )&&(weight>=2.6 && weight<=3.8)){
                limit ="Normal Child";
            } else if((height== 49)&&( weight>3.8 )){
                limit ="Overnourished";
            }

            else if((height==49.5 )&&(weight>=2.7 && weight<=3.9)){
                limit ="Normal Child";
            }  else if((height== 49.5)&&( weight>3.9)){
                limit ="Overnourished";
            }

            else if((height== 50)&&(weight>=2.8 && weight<=4 )){
                limit ="Normal Child";
            }  else if((height== 50)&&( weight>4 )){
                limit ="Overnourished";
            }

            else if((height==50.5)&&(weight>=2.9 && weight<=4.2)){
                limit ="Normal Child";
            }  else if((height== 50.5)&&( weight>4.2)){
                limit ="Overnourished";
            } else if((height== 51)&&(weight>=3 && weight<=4.3)){
                limit ="Normal Child";
            } else if((height== 51)&&( weight>4.3 )){
                limit ="Overnourished";
            }

            else if((height==51.5 )&&(weight>=3.1 && weight<=4.4)){
                limit ="Normal Child";
            } else if((height== 51.5)&&( weight>4.4 )){
                limit ="Overnourished";
            }


            else if((height==52 )&&(weight>=3.2 && weight<=4.6)){
                limit ="Normal Child";
            }  else if((height== 52)&&( weight>4.6 )){
                limit ="Overnourished";
            }
            else if((height==52.5 )&&(weight>=3.3 && weight<=4.7)){
                limit ="Normal Child";
            }  else if((height== 52.5)&&( weight>4.7 )){
                limit ="Overnourished";
            }

            else if((height== 53)&&(weight>=3.4 && weight<=4.9)){
                limit ="Normal Child";
            }  else if((height== 53)&&( weight>4.9 )){
                limit ="Overnourished";
            }

            else if((height== 53.5)&&(weight>=3.5 && weight<=5.0 )){
                limit ="Normal Child";
            }  else if((height== 53.5)&&( weight>5.0 )){
                limit ="Overnourished";
            }

            else if((height== 54)&&(weight>=3.6 && weight<=5.2)){
                limit ="Normal Child";
            }  else if((height== 54)&&( weight>5.2 )){
                limit ="Overnourished";
            }

            else if((height==54.5 )&&(weight>=3.7 && weight<=5.3)){
                limit ="Normal Child";
            }  else if((height== 54.5)&&( weight>5.3 )){
                limit ="Overnourished";
            }

            else if((height==55 )&&(weight>=3.8 && weight<=5.5)){
                limit ="Normal Child";
            } else if((height== 55)&&( weight>5.5 )){
                limit ="Overnourished";
            }

            else if((height== 55.5)&&(weight>=3.9 && weight<=5.7 )){
                limit ="Normal Child";
            }  else if((height== 55.5)&&( weight>5.7 )){
                limit ="Overnourished";
            }

            else if((height==56 )&&(weight>=4 && weight<=5.8)){
                limit ="Normal Child";
            }  else if((height== 56)&&( weight>5.8 )){
                limit ="Overnourished";
            }

            else if((height==56.5 )&&(weight>=4.1 && weight<=6)){
                limit ="Normal Child";
            }  else if((height== 56.5)&&( weight>6)){
                limit ="Overnourished";
            }

            else if((height==57 )&&(weight>=4.3&& weight<=6.1)){
                limit ="Normal Child";
            } else if((height== 57)&&( weight>6.1)){
                limit ="Overnourished";
            }

            else if((height==57.5)&&(weight>=4.4 && weight<=6.3)){
                limit ="Normal Child";
            }  else if((height== 57.5)&&( weight>6.3 )){
                limit ="Overnourished";
            }

            else if((height== 58)&&(weight>=4.5 && weight<=6.5)){
                limit ="Normal Child";
            }  else if((height== 58)&&( weight>6.5)){
                limit ="Overnourished";
            }

            else if((height==58.5 )&&(weight>=4.6 && weight<=6.6)){
                limit ="Normal Child";
            }  else if((height== 58.5)&&( weight>6.62)){
                limit ="Overnourished";
            }

            else if((height== 59)&&(weight>=4.7&& weight<=6.8)){
                limit ="Normal Child";
            }  else if((height== 59)&&( weight>6.8 )){
                limit ="Overnourished";
            }

            else if((height== 59.5)&&(weight>=4.8 && weight<=6.9)){
                limit ="Normal Child";
            }  else if((height== 59.5)&&( weight>6.9)){
                limit ="Overnourished";
            }

            else if((height== 60)&&(weight>=4.9 && weight<=7.1)){
                limit ="Normal Child";
            }  else if((height== 60)&&( weight>7.1 )){
                limit ="Overnourished";
            }

            else if((height== 60.5)&&(weight>=5 && weight<=7.3 )){
                limit ="Normal Child";
            }else if((height== 60.5)&&( weight>7.3 )){
                limit ="Overnourished";
            }

            else if((height== 61)&&(weight>=5.1 && weight<=7.4)){
                limit ="Normal Child";
            } else if((height== 61)&&( weight>7.4 )){
                limit ="Overnourished";
            }

            else if((height== 61.5)&&(weight>=5.2 && weight<=7.6)){
                limit ="Normal Child";
            }else if((height== 61.5)&&( weight>7.6 )){
                limit ="Overnourished";
            }

            else if((height== 62)&&(weight>=5.3 && weight<=7.7)){
                limit ="Normal Child";
            } else if((height== 62)&&( weight>7.7 )){
                limit ="Overnourished";
            }

            else if((height== 62.5)&&(weight>=5.4&& weight<=7.8)){
                limit ="Normal Child";
            }else if((height== 62.5)&&( weight>7.8 )){
                limit ="Overnourished";
            }

            else if((height== 63)&&(weight>=5.5 && weight<=8)){
                limit ="Normal Child";
            }else if((height== 63)&&( weight>8 )){
                limit ="Overnourished";
            }

            else if((height== 63.5)&&(weight>=5.6 && weight<=8.1)){
                limit ="Normal Child";
            } else if((height== 63.5)&&( weight>8.1 )){
                limit ="Overnourished";
            }

            else if((height== 64)&&(weight>=5.7 && weight<=8.3)){
                limit ="Normal Child";
            } else if((height== 64)&&( weight>8.3 )){
                limit ="Overnourished";
            }

            else if((height== 64.5)&&(weight>=5.8 && weight<=8.4)){
                limit ="Normal Child";
            }else if((height== 64.5)&&( weight>8.4 )){
                limit ="Overnourished";
            }

            else if((height== 65)&&(weight>=5.9 && weight<=8.6)){
                limit ="Normal Child";
            }else if((height== 65)&&( weight>8.6 )){
                limit ="Overnourished";
            }

            else if((height== 65.5)&&(weight>=6 && weight<=8.7)){
                limit ="Normal Child";
            } else if((height== 65.5)&&( weight>8.7 )){
                limit ="Overnourished";
            }

            else if((height== 66)&&(weight>=6.1 && weight<=8.8)){
                limit ="Normal Child";
            }else if((height== 66)&&( weight>8.8 )){
                limit ="Overnourished";
            }

            else if((height== 66.5)&&(weight>=6.2 && weight<=9)){
                limit ="Normal Child";
            }else if((height== 66.5)&&( weight>9 )){
                limit ="Overnourished";
            }

            else if((height== 67)&&(weight>=6.3 && weight<=9.1)){
                limit ="Normal Child";
            } else if((height== 67)&&( weight>9.1 )){
                limit ="Overnourished";
            }

            else if((height==67.5 )&&(weight>=6.4 && weight<=9.2)){
                limit ="Normal Child";
            }else if((height== 67.5)&&( weight>9.2 )){
                limit ="Overnourished";
            }

            else if((height==68 )&&(weight>=6.5 && weight<=9.4)){
                limit ="Normal Child";
            }else if((height== 68)&&( weight>9.4)){
                limit ="Overnourished";
            }

            else if((height==68.5 )&&(weight>=6.6 && weight<=9.5)){
                limit ="Normal Child";
            }else if((height== 68.5)&&( weight>9.5 )){
                limit ="Overnourished";
            }

            else if((height==69 )&&(weight>=6.7 && weight<=9.6)){
                limit ="Normal Child";
            } else if((height== 69)&&( weight>9.6 )){
                limit ="Overnourished";
            }

            else if((height==69.5 )&&(weight>=6.8 && weight<=9.7)){
                limit ="Normal Child";
            }else if((height== 69.5)&&( weight>9.7 )){
                limit ="Overnourished";
            }

            else if((height==70 )&&(weight>=6.9 && weight<=9.9)){
                limit ="Normal Child";
            }else if((height== 70)&&( weight>9.9 )){
                limit ="Overnourished";
            }

            else if((height== 70.5)&&(weight>=6.9 && weight<=10.0)){
                limit ="Normal Child";
            }else if((height== 70.5)&&( weight>10.0 )){
                limit ="Overnourished";
            }

            else if((height== 71)&&(weight>=7 && weight<=10.1)){
                limit ="Normal Child";
            }else if((height== 71)&&( weight>10.1 )){
                limit ="Overnourished";
            }

            else if((height==71.5 )&&(weight>=7.1 && weight<=10.2)){
                limit ="Normal Child";
            }else if((height== 71.5)&&( weight>10.2 )){
                limit ="Overnourished";
            }

            else if((height==72 )&&(weight>=7.2&& weight<=10.3)){
                limit ="Normal Child";
            }else if((height== 72)&&( weight>10.3 )){
                limit ="Overnourished";
            }

            else if((height==72.5 )&&(weight>=7.3 && weight<=10.5)){
                limit ="Normal Child";
            } else if((height== 72.5)&&( weight>10.5 )){
                limit ="Overnourished";
            }

            else if((height== 73)&&(weight>=7.4 && weight<=10.6)){
                limit ="Normal Child";
            }else if((height== 73)&&( weight>10.6 )){
                limit ="Overnourished";
            }

            else if((height== 73.5)&&(weight>=7.4 && weight<=10.7)){
                limit ="Normal Child";
            } else if((height== 73.5)&&( weight>10.7 )){
                limit ="Overnourished";
            }

            else if((height== 74)&&(weight>=7.5 && weight<=10.8)){
                limit ="Normal Child";
            }else if((height== 74)&&( weight>10.8 )){
                limit ="Overnourished";
            }

            else if((height==74.5 )&&(weight>=7.6 && weight<=10.9 )){
                limit ="Normal Child";
            }else if((height== 74.5)&&( weight>10.9 )){
                limit ="Overnourished";
            }

            else if((height== 75)&&(weight>=7.7 && weight<=11)){
                limit ="Normal Child";
            } else if((height== 75)&&( weight>11 )){
                limit ="Overnourished";
            }

            else if((height== 75.5)&&(weight>=7.8 && weight<=11.1)){
                limit ="Normal Child";
            } else if((height== 75.5)&&( weight>11.1 )){
                limit ="Overnourished";
            }

            else if((height== 76)&&(weight>=7.8 && weight<=11.2)){
                limit ="Normal Child";
            }else if((height== 76)&&( weight>11.2 )){
                limit ="Overnourished";
            }

            else if((height== 76.5)&&(weight>=7.9 && weight<=11.4 )){
                limit ="Normal Child";
            }else if((height== 76.5)&&( weight>11.4 )){
                limit ="Overnourished";
            }

            else if((height== 77)&&(weight>=8 && weight<=11.5)){
                limit ="Normal Child";
            }else if((height== 77)&&( weight>11.5 )){
                limit ="Overnourished";
            }

            else if((height== 77.5)&&(weight>=8.1 && weight<=11.6 )){
                limit ="Normal Child";
            }else if((height== 77.5)&&( weight>11.6 )){
                limit ="Overnourished";
            }

            else if((height== 78)&&(weight>=8.2 && weight<=11.7)){
                limit ="Normal Child";
            } else if((height== 78)&&( weight>11.7 )){
                limit ="Overnourished";
            }

            else if((height== 78.5)&&(weight>=8.2 && weight<=11.8)){
                limit ="Normal Child";
            } else if((height==78.5)&&( weight>11.8 )){
                limit ="Overnourished";
            }

            else if((height==79 )&&(weight>=8.3 && weight<=11.9)){
                limit ="Normal Child";
            }  else if((height==79)&&( weight>11.9)){
                limit ="Overnourished";
            }

            else if((height== 79.5)&&(weight>=8.4 && weight<=12)){
                limit ="Normal Child";
            }  else if((height==79.5)&&( weight>12)){
                limit ="Overnourished";
            }

            else if((height== 80)&&(weight>=8.5 && weight<=12.1)){
                limit ="Normal Child";
            }  else if((height==80)&&( weight>12.1 )){
                limit ="Overnourished";
            }

            else if((height== 80.5)&&(weight>=8.6 && weight<=12.3)){
                limit ="Normal Child";
            }  else if((height==80.5)&&( weight>12.3 )){
                limit ="Overnourished";
            }

            else if((height== 81)&&(weight>=8.7 && weight<=12.4)){
                limit ="Normal Child";
            }  else if((height==81)&&( weight>12.4 )){
                limit ="Overnourished";
            }

            else if((height== 81.5)&&(weight>=8.8 && weight<=12.5)){
                limit ="Normal Child";
            } else if((height==81.5)&&( weight>12.5)){
                limit ="Overnourished";
            }

            else if((height== 82)&&(weight>=8.8&& weight<=12.6)){
                limit ="Normal Child";
            }  else if((height==82)&&( weight>12.6 )){
                limit ="Overnourished";
            }

            else if((height== 82.5)&&(weight>=8.9 && weight<=12.8)){
                limit ="Normal Child";
            }  else if((height==82.5)&&( weight>12.8 )){
                limit ="Overnourished";
            }

            else if((height== 83)&&(weight>=9.0 && weight<=12.9 )){
                limit ="Normal Child";
            } else if((height==83)&&( weight>12.9 )){
                limit ="Overnourished";
            }

            else if((height== 83.5)&&(weight>=9.1 && weight<=13.1)){
                limit ="Normal Child";
            }  else if((height==83.5)&&( weight>13.1)){
                limit ="Overnourished";
            }

            else if((height== 84)&&(weight>=9.2&& weight<=13.2)){
                limit ="Normal Child";
            }  else if((height==84)&&( weight>13.2)){
                limit ="Overnourished";
            }

            else if((height== 84.5)&&(weight>=9.3 && weight<=13.3)){
                limit ="Normal Child";
            }  else if((height==84.5)&&( weight>13.3 )){
                limit ="Overnourished";
            }

            else if((height== 85)&&(weight>=9.4 && weight<=13.5 )){
                limit ="Normal Child";
            } else if((height==85)&&( weight>13.5 )){
                limit ="Overnourished";
            }

            else if((height== 85.5)&&(weight>=9.5 && weight<=13.6)){
                limit ="Normal Child";
            }else if((height==85.5)&&( weight>13.6 )){
                limit ="Overnourished";
            }

            else if((height== 86)&&(weight>=9.7&& weight<=13.8)){
                limit ="Normal Child";
            } else if((height==86)&&( weight>13.8 )){
                limit ="Overnourished";
            }

            else if((height== 86.5)&&(weight>=9.8 && weight<=13.9)){
                limit ="Normal Child";
            } else if((height==86.5)&&( weight>13.9 )){
                limit ="Overnourished";
            }

            else if((height== 87)&&(weight>=9.9 && weight<=14.1)){
                limit ="Normal Child";
            } else if((height==87)&&( weight>14.1 )){
                limit ="Overnourished";
            }

            else if((height== 87.5)&&(weight>=10 && weight<=14.2)){
                limit ="Normal Child";
            } else if((height==87.5)&&( weight>14.2 )){
                limit ="Overnourished";
            }

            else if((height== 88)&&(weight>=10.1 && weight<=14.4)){
                limit ="Normal Child";
            } else if((height==88)&&( weight>14.4 )){
                limit ="Overnourished";
            }

            else if((height== 88.5)&&(weight>=10.2 && weight<=14.5)){
                limit ="Normal Child";
            }else if((height==88.5)&&( weight>14.5 )){
                limit ="Overnourished";
            }

            else if((height== 89)&&(weight>=10.3 && weight<=14.7)){
                limit ="Normal Child";
            }else if((height==89)&&( weight>14.7 )){
                limit ="Overnourished";
            }

            else if((height== 89.5)&&(weight>=10.4 && weight<=14.8)){
                limit ="Normal Child";
            } else if((height==89.5)&&( weight>14.8 )){
                limit ="Overnourished";
            }

            else if((height== 90)&&(weight>=10.5 && weight<=15)){
                limit ="Normal Child";
            } else if((height==90)&&( weight>15 )){
                limit ="Overnourished";
            }

            else if((height== 90.5)&&(weight>=10.6 && weight<=15.1)){
                limit ="Normal Child";
            }else if((height==90.5)&&( weight>15.1 )){
                limit ="Overnourished";
            }


            else if((height== 91)&&(weight>=10.7 && weight<=15.3)){
                limit ="Normal Child";
            }else if((height==91)&&( weight>15.3 )){
                limit ="Overnourished";
            }

            else if((height== 91.5)&&(weight>=10.8 && weight<=15.5)){
                limit ="Normal Child";
            }else if((height==91.5)&&( weight>15.5 )){
                limit ="Overnourished";
            }

            else if((height== 92)&&(weight>=10.9 && weight<=15.6)){
                limit ="Normal Child";
            }else if((height==92)&&( weight>15.6 )){
                limit ="Overnourished";
            }

            else if((height== 92.5)&&(weight>=11 && weight<=15.8 )){
                limit ="Normal Child";
            } else if((height==92.5)&&( weight>15.8 )){
                limit ="Overnourished";
            }

            else if((height== 93)&&(weight>=11.1 && weight<=15.9)){
                limit ="Normal Child";
            } else if((height==93)&&( weight>15.9)){
                limit ="Overnourished";
            }

            else if((height== 93.5)&&(weight>=11.2 && weight<=16.1)){
                limit ="Normal Child";
            }else if((height==93.5)&&( weight>16.1 )){
                limit ="Overnourished";
            }

            else if((height== 94)&&(weight>=11.3 && weight<=16.2)){
                limit ="Normal Child";
            } else if((height==94)&&( weight>16.2 )){
                limit ="Overnourished";
            }

            else if((height== 94.5)&&(weight>=11.4 && weight<=16.4)){
                limit ="Normal Child";
            } else if((height==94.5)&&( weight>16.4 )){
                limit ="Overnourished";
            }

            else if((height== 95)&&(weight>=11.5 && weight<=16.5)){
                limit ="Normal Child";
            }else if((height==95)&&( weight>16.5 )){
                limit ="Overnourished";
            }

            else if((height== 95.5)&&(weight>=11.6 && weight<=16.7)){
                limit ="Normal Child";
            } else if((height==95.5)&&( weight>16.7)){
                limit ="Overnourished";
            }

            else if((height== 96)&&(weight>=11.7 && weight<=16.8)){
                limit ="Normal Child";
            }else if((height==96)&&( weight>16.8 )){
                limit ="Overnourished";
            }

            else if((height== 96.5)&&(weight>=11.8 && weight<=17)){
                limit ="Normal Child";
            } else if((height==96.5)&&( weight>17 )){
                limit ="Overnourished";
            }

            else if((height==97 )&&(weight>=12 && weight<=17.1)){
                limit ="Normal Child";
            }else if((height==97)&&( weight>17.1 )){
                limit ="Overnourished";
            }

            else if((height== 97.5)&&(weight>=12.1 && weight<=17.3 )){
                limit ="Normal Child";
            }else if((height==97.5)&&( weight>17.3 )){
                limit ="Overnourished";
            }

            else if((height== 98)&&(weight>=12.2&& weight<=17.5)){
                limit ="Normal Child";
            }else if((height==98)&&( weight>17.5 )){
                limit ="Overnourished";
            }

            else if((height== 98.5)&&(weight>=12.3 && weight<=17.6)){
                limit ="Normal Child";
            }else if((height==98.5)&&( weight>17.6 )){
                limit ="Overnourished";
            }

            else if((height== 99)&&(weight>=12.4 && weight<=17.8)){
                limit ="Normal Child";
            }else if((height==99)&&( weight>17.8 )){
                limit ="Overnourished";
            }

            else if((height== 99.5)&&(weight>=12.5 && weight<=18)){
                limit ="Normal Child";
            }else if((height==99.5)&&( weight>18 )){
                limit ="Overnourished";
            }

            else if((height== 100)&&(weight>=12.6 && weight<=18.1 )){
                limit ="Normal Child";
            }else if((height==100)&&( weight>18.1 )){
                limit ="Overnourished";
            } else if((height== 100.5)&&(weight>=12.7 && weight<=18.3)){
                limit ="Normal Child";
            }else if((height==100.5)&&( weight>18.3 )){
                limit ="Overnourished";
            }

            else if((height== 101)&&(weight>=12.8 && weight<=18.5)){
                limit ="Normal Child";
            }else if((height==101)&&( weight>18.5 )){
                limit ="Overnourished";
            }

            else if((height== 101.5)&&(weight>=13 && weight<=18.7)){
                limit ="Normal Child";
            }else if((height==101.5)&&( weight>18.7)){
                limit ="Overnourished";
            }

            else if((height== 102)&&(weight>=13.1 && weight<=18.9)){
                limit ="Normal Child";
            }else if((height==102)&&( weight>18.9 )){
                limit ="Overnourished";
            }  else if((height== 102.5)&&(weight>=13.2 && weight<=19.0)){
                limit ="Normal Child";
            }else if((height==102.5)&&( weight>19.0)){
                limit ="Overnourished";
            }

            else if((height== 103)&&(weight>=13.3 && weight<=19.2)){
                limit ="Normal Child";
            }else if((height==103)&&( weight>19.2 )){
                limit ="Overnourished";
            }

            else if((height== 103.5)&&(weight>=13.5 && weight<=19.4 )){
                limit ="Normal Child";
            }else if((height==103.5)&&( weight>19.4)){
                limit ="Overnourished";
            }
            else if((height== 104)&&(weight>=13.6 && weight<=19.6)){
                limit ="Normal Child";
            }else if((height==104)&&( weight>19.6 )){
                limit ="Overnourished";
            }


            else if((height== 104.5)&&(weight>=13.7 && weight<=19.8)){
                limit ="Normal Child";
            }else if((height==104.5)&&( weight>19.8 )){
                limit ="Overnourished";
            }

            else if((height== 105)&&(weight>=13.8 && weight<=20)){
                limit ="Normal Child";
            }else if((height==105)&&( weight>20 )){
                limit ="Overnourished";
            }

            else if((height== 105.5)&&(weight>=14 && weight<=20.2)){
                limit ="Normal Child";
            }else if((height==105.5)&&( weight>20.2 )){
                limit ="Overnourished";
            }

            else if((height== 106)&&(weight>=14.1 && weight<=20.5)){
                limit ="Normal Child";
            }else if((height==106)&&( weight>20.5 )){
                limit ="Overnourished";
            }  else if((height== 106.5)&&(weight>=14.3 && weight<=20.7)){
                limit ="Normal Child";
            } else if((height==106.5)&&( weight>20.7 )){
                limit ="Overnourished";
            }
            else if((height==107 )&&(weight>=14.4 && weight<=20.9)){
                limit ="Normal Child";
            }else if((height==107)&&( weight>20.9)){
                limit ="Overnourished";
            }

            else if((height== 107.5)&&(weight>=14.5 && weight<=21.1)){
                limit ="Normal Child";
            }else if((height==107.5)&&( weight>21.1 )){
                limit ="Overnourished";
            }

            else if((height== 108)&&(weight>=14.7 && weight<=21.3)){
                limit ="Normal Child";
            }else if((height==108)&&( weight>21.3 )){
                limit ="Overnourished";
            }

            else if((height==108.5 )&&(weight>=14.8 && weight<=21.6)){
                limit ="Normal Child";
            }else if((height==108.5)&&( weight>21.6 )){
                limit ="Overnourished";
            }  else if((height== 109)&&(weight>=15 && weight<=21.8)){
                limit ="Normal Child";
            }else if((height==109)&&( weight>21.8 )){
                limit ="Overnourished";
            }

            else if((height== 109.5)&&(weight>=15.1 && weight<=22.0)){
                limit ="Normal Child";
            }else if((height==109.5)&&( weight>22.0 )){
                limit ="Overnourished";
            }

            else if((height==110 )&&(weight>=15.3 && weight<=22.3)){
                limit ="Normal Child";
            }else if((height==110)&&( weight>22.3 )){
                limit ="Overnourished";
            }
            else{limit="Wasted";}
        }
        else if(((years==2 || years==3)||(years==4))&&(months>=0 &&months<=12)){

            if((height== 65)&&(weight>=6.1 && weight<=8.7)){
                limit ="Normal Child";
            } else if((height==65)&&( weight>8.7 )){
                limit ="Overnourished";
            }

            else if((height== 65.5)&&(weight>=6.2 && weight<=8.9)){
                limit ="Normal Child";
            } else if((height==65.5)&&( weight>8.9 )){
                limit ="Overnourished";
            }

            else if((height== 66)&&(weight>=6.3 && weight<=8.9)){
                limit ="Normal Child";
            } else if((height==66)&&( weight>8.9)){
                limit ="Overnourished";
            }

            else if((height== 66.5)&&(weight>=6.4 && weight<=9.0)){
                limit ="Normal Child";
            }  else if((height==66.5)&&( weight>9.0 )){
                limit ="Overnourished";
            }

            else if((height== 67)&&(weight>=6.4 && weight<=9.1)){
                limit ="Normal Child";
            }  else if((height==67)&&( weight>9.1 )){
                limit ="Overnourished";
            }

            else if((height==67.5 )&&(weight>=6.5 && weight<=9.3)){
                limit ="Normal Child";
            } else if((height==67.5)&&( weight>9.3)){
                limit ="Overnourished";
            }

            else if((height==68 )&&(weight>=6.6 && weight<=9.5)){
                limit ="Normal Child";
            }  else if((height==68)&&( weight>9.5 )){
                limit ="Overnourished";
            }

            else if((height==68.5 )&&(weight>=6.7 && weight<=9.7)){
                limit ="Normal Child";
            } else if((height==68.5)&&( weight>9.7)){
                limit ="Overnourished";
            }

            else if((height==69 )&&(weight>=6.8 && weight<=9.8 )){
                limit ="Normal Child";
            }  else if((height==69)&&( weight>9.8 )){
                limit ="Overnourished";
            }

            else if((height==69.5 )&&(weight>=6.9 && weight<=9.9)){
                limit ="Normal Child";
            } else if((height==69.5)&&( weight>9.9 )){
                limit ="Overnourished";
            }

            else if((height==70 )&&(weight>=7 && weight<=10)){
                limit ="Normal Child";
            } else if((height==70)&&( weight>10 )){
                limit ="Overnourished";
            }

            else if((height== 70.5)&&(weight>=7.1 && weight<=10.1)){
                limit ="Normal Child";
            } else if((height==70.5)&&( weight>10.1 )){
                limit ="Overnourished";
            }

            else if((height== 71)&&(weight>=7.1 && weight<=10.3)){
                limit ="Normal Child";
            }else if((height==71)&&( weight>10.3 )){
                limit ="Overnourished";
            }

            else if((height==71.5 )&&(weight>=7.2&& weight<=10.4)){
                limit ="Normal Child";
            } else if((height==71.5)&&( weight>10.4 )){
                limit ="Overnourished";
            }

            else if((height==72 )&&(weight>=7.3&& weight<=10.5)){
                limit ="Normal Child";
            }else if((height==72)&&( weight>10.5 )){
                limit ="Overnourished";
            }

            else if((height==72.5 )&&(weight>=7.4 && weight<=10.6)){
                limit ="Normal Child";
            }else if((height==72.5)&&( weight>10.6 )){
                limit ="Overnourished";
            }

            else if((height== 73)&&(weight>=7.5 && weight<=10.7)){
                limit ="Normal Child";
            }else if((height==73)&&( weight>10.7 )){
                limit ="Overnourished";
            }

            else if((height== 73.5)&&(weight>=7.6 && weight<=10.8)){
                limit ="Normal Child";
            } else if((height==73.5)&&( weight>10.8)){
                limit ="Overnourished";
            }

            else if((height== 74)&&(weight>=7.6 && weight<=11)){
                limit ="Normal Child";
            }else if((height==74)&&( weight>11)){
                limit ="Overnourished";
            }

            else if((height==74.5 )&&(weight>=7.7 && weight<=11.1 )){
                limit ="Normal Child";
            }else if((height==74.5)&&( weight>11.01)){
                limit ="Overnourished";
            }

            else if((height== 75)&&(weight>=7.8 && weight<=11.2)){
                limit ="Normal Child";
            } else if((height==75)&&( weight>11.2 )){
                limit ="Overnourished";
            }

            else if((height== 75.5)&&(weight>=7.9 && weight<=11.3)){
                limit ="Normal Child";
            }else if((height==75.5)&&( weight>11.3 )){
                limit ="Overnourished";
            }

            else if((height== 76)&&(weight>=8 && weight<=11.4)){
                limit ="Normal Child";
            } else if((height==76)&&( weight>11.4)){
                limit ="Overnourished";
            }

            else if((height== 76.5)&&(weight>=8 && weight<=11.5)){
                limit ="Normal Child";
            }else if((height==76.5)&&( weight>11.5 )){
                limit ="Overnourished";
            }

            else if((height== 77)&&(weight>=8.1 && weight<=11.6)){
                limit ="Normal Child";
            } else if((height==77)&&( weight>11.6 )){
                limit ="Overnourished";
            }

            else if((height== 77.5)&&(weight>=8.2 && weight<=11.7 )){
                limit ="Normal Child";
            } else if((height==77.5)&&( weight>11.7 )){
                limit ="Overnourished";
            }

            else if((height== 78)&&(weight>=8.3 && weight<=11.8)){
                limit ="Normal Child";
            }else if((height==78)&&( weight>11.8)){
                limit ="Overnourished";
            }

            else if((height== 78.5)&&(weight>=8.4 && weight<=12.0)){
                limit ="Normal Child";
            }else if((height==78.5)&&( weight>12.0 )){
                limit ="Overnourished";
            }

            else if((height==79 )&&(weight>=8.4&& weight<=12.1)){
                limit ="Normal Child";
            } else if((height==79)&&( weight>12.1)){
                limit ="Overnourished";
            }

            else if((height== 79.5)&&(weight>=8.5 && weight<=12.2)){
                limit ="Normal Child";
            } else if((height==79.5)&&( weight>12.2 )){
                limit ="Overnourished";
            }

            else if((height== 80)&&(weight>=8.6 && weight<=12.3 )){
                limit ="Normal Child";
            }else if((height==80)&&( weight>12.3 )){
                limit ="Overnourished";
            }

            else if((height== 80.5)&&(weight>=8.7 && weight<=12.4)){
                limit ="Normal Child";
            } else if((height==80.5)&&( weight>12.4 )){
                limit ="Overnourished";
            }

            else if((height== 81)&&(weight>=8.8 && weight<=12.6)){
                limit ="Normal Child";
            } else if((height==81)&&( weight>12.6 )){
                limit ="Overnourished";
            }

            else if((height== 81.5)&&(weight>=8.9 && weight<=12.7)){
                limit ="Normal Child";
            } else if((height==81.5)&&( weight>12.7 )){
                limit ="Overnourished";
            }

            else if((height== 82)&&(weight>=9 && weight<=12.8)){
                limit ="Normal Child";
            }else if((height==82)&&( weight>12.8 )){
                limit ="Overnourished";
            }

            else if((height== 82.5)&&(weight>=9.1 && weight<=13)){
                limit ="Normal Child";
            }else if((height==82.5)&&( weight>13 )){
                limit ="Overnourished";
            }

            else if((height== 83)&&(weight>=9.2 && weight<=13.1)){
                limit ="Normal Child";
            }else if((height==83)&&( weight>13.1 )){
                limit ="Overnourished";
            }

            else if((height== 83.5)&&(weight>=9.3 && weight<=13.3)){
                limit ="Normal Child";
            } else if((height==83.5)&&( weight>13.3 )){
                limit ="Overnourished";
            }

            else if((height== 84)&&(weight>=9.4&& weight<=13.4)){
                limit ="Normal Child";
            }else if((height==84)&&( weight>13.4 )){
                limit ="Overnourished";
            }

            else if((height== 84.5)&&(weight>=9.5 && weight<=13.5)){
                limit ="Normal Child";
            } else if((height==84.5)&&( weight>13.5 )){
                limit ="Overnourished";
            }

            else if((height== 85)&&(weight>=9.6 && weight<=13.7 )){
                limit ="Normal Child";
            }else if((height==85)&&( weight>13.7 )){
                limit ="Overnourished";
            }

            else if((height== 85.5)&&(weight>=9.7 && weight<=13.8 )){
                limit ="Normal Child";
            }else if((height==85.5)&&( weight>13.8 )){
                limit ="Overnourished";
            }

            else if((height== 86)&&(weight>=9.8 && weight<=14)){
                limit ="Normal Child";
            } else if((height==86)&&( weight>14 )){
                limit ="Overnourished";
            }

            else if((height== 86.5)&&(weight>=9.9 && weight<=14.2)){
                limit ="Normal Child";
            }else if((height==86.5)&&( weight>14.2 )){
                limit ="Overnourished";
            }
            else if((height== 87)&&(weight>=10&& weight<=14.3)){
                limit ="Normal Child";
            }else if((height==87)&&( weight>14.3 )){
                limit ="Overnourished";
            }
            else if((height== 87.5)&&(weight>=10.1 && weight<=14.5)){
                limit ="Normal Child";
            } else if((height==87.5)&&( weight>14.5 )){
                limit ="Overnourished";
            }
            else if((height== 88)&&(weight>=10.2 && weight<=14.6)){
                limit ="Normal Child";
            }else if((height==88)&&( weight>14.6 )){
                limit ="Overnourished";
            }

            else if((height== 88.5)&&(weight>=10.3 && weight<=14.8)){
                limit ="Normal Child";
            }else if((height==88.5)&&( weight>14.8 )){
                limit ="Overnourished";
            }

            else if((height== 89)&&(weight>=10.4 && weight<=14.9)){
                limit ="Normal Child";
            } else if((height==89)&&( weight>14.9 )){
                limit ="Overnourished";
            }

            else if((height== 89.5)&&(weight>=10.5 && weight<=15.1)){
                limit ="Normal Child";
            } else if((height==89.5)&&( weight>15.1 )){
                limit ="Overnourished";
            }

            else if((height== 90)&&(weight>=10.6 && weight<=15.2)){
                limit ="Normal Child";
            }else if((height==90)&&( weight>15.2 )){
                limit ="Overnourished";
            }

            else if((height== 90.5)&&(weight>=10.7 && weight<=15.4)){
                limit ="Normal Child";
            }else if((height==90.5)&&( weight>15.4 )){
                limit ="Overnourished";
            }

            else if((height== 91)&&(weight>=10.9 && weight<=15.5)){
                limit ="Normal Child";
            }else if((height==91)&&( weight>15.5 )){
                limit ="Overnourished";
            }

            else if((height== 91.5)&&(weight>=11 && weight<=15.7)){
                limit ="Normal Child";
            }else if((height==91.5)&&( weight>15.7 )){
                limit ="Overnourished";
            }

            else if((height== 92)&&(weight>=11.1 && weight<=15.8)){
                limit ="Normal Child";
            }else if((height==92)&&( weight>15.8 )){
                limit ="Overnourished";
            }

            else if((height== 92.5)&&(weight>=11.2 && weight<=16 )){
                limit ="Normal Child";
            } else if((height==92.5)&&( weight>16 )){
                limit ="Overnourished";
            }

            else if((height== 93)&&(weight>=11.3 && weight<=16.1)){
                limit ="Normal Child";
            }else if((height==93)&&( weight>16.1 )){
                limit ="Overnourished";
            }

            else if((height== 93.5)&&(weight>=11.4 && weight<=16.3)){
                limit ="Normal Child";
            }else if((height==93.5)&&( weight>16.3 )){
                limit ="Overnourished";
            }

            else if((height== 94)&&(weight>=11.5 && weight<=16.4)){
                limit ="Normal Child";
            } else if((height==94)&&( weight>16.4 )){
                limit ="Overnourished";
            }

            else if((height== 94.5)&&(weight>=11.6&& weight<=16.6)){
                limit ="Normal Child";
            }else if((height==94.5)&&( weight>16.6 )){
                limit ="Overnourished";
            }

            else if((height== 95)&&(weight>=11.7 && weight<=16.7)){
                limit ="Normal Child";
            }else if((height==95)&&( weight>16.7 )){
                limit ="Overnourished";
            }

            else if((height== 95.5)&&(weight>=11.8 && weight<=16.9)){
                limit ="Normal Child";
            } else if((height==95.5)&&( weight>16.9 )){
                limit ="Overnourished";
            }

            else if((height== 96)&&(weight>=11.9 && weight<=17.0)){
                limit ="Normal Child";
            }else if((height==96)&&( weight>17.0 )){
                limit ="Overnourished";
            }

            else if((height== 96.5)&&(weight>=12.0 && weight<=17.2)){
                limit ="Normal Child";
            }else if((height==96.5)&&( weight>17.2 )){
                limit ="Overnourished";
            }

            else if((height==97 )&&(weight>=12.1 && weight<=17.4)){
                limit ="Normal Child";
            }else if((height==97)&&( weight>17.4 )){
                limit ="Overnourished";
            }

            else if((height== 97.5)&&(weight>=12.2 && weight<=17.5 )){
                limit ="Normal Child";
            }else if((height==97.5)&&( weight>17.5 )){
                limit ="Overnourished";
            }

            else if((height== 98)&&(weight>=12.3 && weight<=17.7)){
                limit ="Normal Child";
            }else if((height==98)&&( weight>17.7 )){
                limit ="Overnourished";
            }

            else if((height== 98.5)&&(weight>=12.4&& weight<=17.9)){
                limit ="Normal Child";
            }else if((height==98.5)&&( weight>17.9 )){
                limit ="Overnourished";
            }

            else if((height== 99)&&(weight>=12.5 && weight<=18.0)){
                limit ="Normal Child";
            }else if((height==99)&&( weight>18.0 )){
                limit ="Overnourished";
            }

            else if((height== 99.5)&&(weight>=12.7 && weight<=18.2 )){
                limit ="Normal Child";
            }else if((height==99.5)&&( weight>18.2 )){
                limit ="Overnourished";
            }

            else if((height== 100)&&(weight>=12.8 && weight<=18.4 )){
                limit ="Normal Child";
            }else if((height==100)&&( weight>18.4 )){
                limit ="Overnourished";
            }

            else if((height== 100.5)&&(weight>=12.9 && weight<=18.6)){
                limit ="Normal Child";
            }else if((height==100.5)&&( weight>18.6 )){
                limit ="Overnourished";
            }

            else if((height== 101)&&(weight>=13.0 && weight<=18.7)){
                limit ="Normal Child";
            }else if((height==101)&&( weight>18.7 )){
                limit ="Overnourished";
            }

            else if((height== 101.5)&&(weight>=13.1 && weight<=18.9)){
                limit ="Normal Child";
            }else if((height==101.5)&&( weight>18.9)){
                limit ="Overnourished";
            }

            else if((height== 102)&&(weight>=13.3 && weight<=19.1)){
                limit ="Normal Child";
            }else if((height==102)&&( weight>19.1 )){
                limit ="Overnourished";
            }

            else if((height== 102.5)&&(weight>=13.4 && weight<=19.3)){
                limit ="Normal Child";
            }else if((height==102.5)&&( weight>19.3 )){
                limit ="Overnourished";
            }

            else if((height== 103)&&(weight>=13.5 && weight<=19.5)){
                limit ="Normal Child";
            }else if((height==103)&&( weight>19.54 )){
                limit ="Overnourished";
            }

            else if((height== 103.5)&&(weight>=13.6 && weight<=19.7 )){
                limit ="Normal Child";
            }else if((height==103.5)&&( weight>19.7 )){
                limit ="Overnourished";
            }

            else if((height== 104)&&(weight>=13.8&& weight<=19.9)){
                limit ="Normal Child";
            }else if((height==104)&&( weight>19.9 )){
                limit ="Overnourished";
            }

            else if((height== 104.5)&&(weight>=13.9 && weight<=20.1)){
                limit ="Normal Child";
            }else if((height==104.5)&&( weight>20.1 )){
                limit ="Overnourished";
            }

            else if((height== 105)&&(weight>=14 && weight<=20.3)){
                limit ="Normal Child";
            }else if((height==105)&&( weight>20.3 )){
                limit ="Overnourished";
            }

            else if((height== 105.5)&&(weight>=14.2&& weight<=20.5)){
                limit ="Normal Child";
            }else if((height==105.5)&&( weight>20.5 )){
                limit ="Overnourished";
            }

            else if((height== 106)&&(weight>=14.3 && weight<=20.8)){
                limit ="Normal Child";
            }else if((height==106)&&( weight>20.8 )){
                limit ="Overnourished";
            }

            else if((height== 106.5)&&(weight>=14.5 && weight<=21)){
                limit ="Normal Child";
            }else if((height==106.5)&&( weight>21 )){
                limit ="Overnourished";
            }

            else if((height==107 )&&(weight>=14.6 && weight<=21.2)){
                limit ="Normal Child";
            }else if((height==107)&&( weight>20.2 )){
                limit ="Overnourished";
            }

            else if((height== 107.5)&&(weight>=14.7 && weight<=21.4)){
                limit ="Normal Child";
            }else if((height==107.5)&&( weight>21.4 )){
                limit ="Overnourished";
            }

            else if((height== 108)&&(weight>=14.9 && weight<=21.7)){
                limit ="Normal Child";
            }else if((height==108)&&( weight>21.7 )){
                limit ="Overnourished";
            }

            else if((height==108.5 )&&(weight>=15.0 && weight<=21.9)){
                limit ="Normal Child";
            }else if((height==108.5)&&( weight>21.9 )){
                limit ="Overnourished";
            }

            else if((height== 109)&&(weight>=15.2 && weight<=22.1)){
                limit ="Normal Child";
            }else if((height==109)&&( weight>22.1)){
                limit ="Overnourished";
            }

            else if((height== 109.5)&&(weight>=15.4 && weight<=22.4)){
                limit ="Normal Child";
            }else if((height==109.5)&&( weight>22.4 )){
                limit ="Overnourished";
            }

            else if((height==110 )&&(weight>=15.5 && weight<=22.6)){
                limit ="Normal Child";
            }else if((height==110)&&( weight>22.6 )){
                limit ="Overnourished";
            }

            else if((height== 110.5)&&(weight>=15.7 && weight<=22.9 )){
                limit ="Normal Child";
            }else if((height==110.5)&&( weight>22.9 )){
                limit ="Overnourished";
            }


            else if((height== 111)&&(weight>=15.8 && weight<=23.1)){
                limit ="Normal Child";
            }else if((height==111)&&( weight>23.1)){
                limit ="Overnourished";
            }


            else if((height== 111.5)&&(weight>=16 && weight<=23.4)){
                limit ="Normal Child";
            }else if((height==111.5)&&( weight>23.4)){
                limit ="Overnourished";
            }

            else if((height== 112)&&(weight>=16.2 && weight<=23.6)){
                limit ="Normal Child";
            }else if((height==112)&&( weight>23.6 )){
                limit ="Overnourished";
            }

            else if((height== 112.5)&&(weight>=16.3 && weight<=23.9)){
                limit ="Normal Child";
            }else if((height==112.5)&&( weight>23.9 )){
                limit ="Overnourished";
            }

            else if((height== 113)&&(weight>=16.5 && weight<=24.2)){
                limit ="Normal Child";
            }else if((height==113)&&( weight>24.2)){
                limit ="Overnourished";
            }

            else if((height== 113.5)&&(weight>=16.7 && weight<=24.4)){
                limit ="Normal Child";
            }else if((height==113.5)&&( weight>24.4 )){
                limit ="Overnourished";
            }

            else if((height== 114)&&(weight>=16.8 && weight<=24.7)){
                limit ="Normal Child";
            }else if((height==114)&&( weight>24.7)){
                limit ="Overnourished";
            }

            else if((height== 114.5)&&(weight>=17.0 && weight<=25)){
                limit ="Normal Child";
            }else if((height==114.5)&&( weight>25 )){
                limit ="Overnourished";
            }

            else if((height== 115)&&(weight>=17.2 && weight<=25.2)){
                limit ="Normal Child";
            }else if((height==115)&&( weight>25.2 )){
                limit ="Overnourished";
            }

            else if((height== 115.5)&&(weight>=17.3 && weight<=25.5)){
                limit ="Normal Child";
            }else if((height==115.5)&&( weight>25.5 )){
                limit ="Overnourished";
            }

            else if((height== 116)&&(weight>=17.5 && weight<=25.8)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>25.76 )){
                limit ="Overnourished";
            }

            else if((height== 116.5)&&(weight>=17.7 && weight<=26.1)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>26.1)){
                limit ="Overnourished";
            }

            else if((height== 117)&&(weight>=17.8 && weight<=26.3)){
                limit ="Normal Child";
            }else if((height==117)&&( weight>26.3 )){
                limit ="Overnourished";
            }

            else if((height== 117.5)&&(weight>=18.0 && weight<=26.6)){
                limit ="Normal Child";
            }else if((height==117.5)&&( weight>26.6 )){
                limit ="Overnourished";
            }

            else if((height== 118)&&(weight>=18.2 && weight<=26.9)){
                limit ="Normal Child";
            }else if((height==118)&&( weight>26.9 )){
                limit ="Overnourished";
            }

            else if((height== 118.5)&&(weight>=18.4 && weight<=27.2)){
                limit ="Normal Child";
            }else if((height==118.5)&&( weight>27.2 )){
                limit ="Overnourished";
            }

            else if((height== 119)&&(weight>=18.5 && weight<=27.4)){
                limit ="Normal Child";
            }else if((height==119)&&( weight>27.4)){
                limit ="Overnourished";
            }

            else if((height== 119.5)&&(weight>=18.7 && weight<=27.7)){
                limit ="Normal Child";
            }else if((height==119.5)&&( weight>27.7 )){
                limit ="Overnourished";
            }

            else if((height== 120)&&(weight>=18.9 && weight<=28)){
                limit ="Normal Child";
            }else if((height==120)&&( weight>28)){
                limit ="Overnourished";
            }

            else {limit="Wasted";}
        }
        else if((years==5)&&(months==0 )){

            if((height== 65)&&(weight>=6.1 && weight<=8.7)){
                limit ="Normal Child";
            } else if((height==65)&&( weight>8.7 )){
                limit ="Overnourished";
            }

            else if((height== 65.5)&&(weight>=6.2 && weight<=8.9)){
                limit ="Normal Child";
            } else if((height==65.5)&&( weight>8.9 )){
                limit ="Overnourished";
            }

            else if((height== 66)&&(weight>=6.3 && weight<=8.9)){
                limit ="Normal Child";
            } else if((height==66)&&( weight>8.9)){
                limit ="Overnourished";
            }

            else if((height== 66.5)&&(weight>=6.4 && weight<=9.0)){
                limit ="Normal Child";
            }  else if((height==66.5)&&( weight>9.0 )){
                limit ="Overnourished";
            }

            else if((height== 67)&&(weight>=6.4 && weight<=9.1)){
                limit ="Normal Child";
            }  else if((height==67)&&( weight>9.1 )){
                limit ="Overnourished";
            }

            else if((height==67.5 )&&(weight>=6.5 && weight<=9.3)){
                limit ="Normal Child";
            } else if((height==67.5)&&( weight>9.3)){
                limit ="Overnourished";
            }

            else if((height==68 )&&(weight>=6.6 && weight<=9.5)){
                limit ="Normal Child";
            }  else if((height==68)&&( weight>9.5 )){
                limit ="Overnourished";
            }

            else if((height==68.5 )&&(weight>=6.7 && weight<=9.7)){
                limit ="Normal Child";
            } else if((height==68.5)&&( weight>9.7)){
                limit ="Overnourished";
            }

            else if((height==69 )&&(weight>=6.8 && weight<=9.8 )){
                limit ="Normal Child";
            }  else if((height==69)&&( weight>9.8 )){
                limit ="Overnourished";
            }

            else if((height==69.5 )&&(weight>=6.9 && weight<=9.9)){
                limit ="Normal Child";
            } else if((height==69.5)&&( weight>9.9 )){
                limit ="Overnourished";
            }

            else if((height==70 )&&(weight>=7 && weight<=10)){
                limit ="Normal Child";
            } else if((height==70)&&( weight>10 )){
                limit ="Overnourished";
            }

            else if((height== 70.5)&&(weight>=7.1 && weight<=10.1)){
                limit ="Normal Child";
            } else if((height==70.5)&&( weight>10.1 )){
                limit ="Overnourished";
            }

            else if((height== 71)&&(weight>=7.1 && weight<=10.3)){
                limit ="Normal Child";
            }else if((height==71)&&( weight>10.3 )){
                limit ="Overnourished";
            }

            else if((height==71.5 )&&(weight>=7.2&& weight<=10.4)){
                limit ="Normal Child";
            } else if((height==71.5)&&( weight>10.4 )){
                limit ="Overnourished";
            }

            else if((height==72 )&&(weight>=7.3&& weight<=10.5)){
                limit ="Normal Child";
            }else if((height==72)&&( weight>10.5 )){
                limit ="Overnourished";
            }

            else if((height==72.5 )&&(weight>=7.4 && weight<=10.6)){
                limit ="Normal Child";
            }else if((height==72.5)&&( weight>10.6 )){
                limit ="Overnourished";
            }

            else if((height== 73)&&(weight>=7.5 && weight<=10.7)){
                limit ="Normal Child";
            }else if((height==73)&&( weight>10.7 )){
                limit ="Overnourished";
            }

            else if((height== 73.5)&&(weight>=7.6 && weight<=10.8)){
                limit ="Normal Child";
            } else if((height==73.5)&&( weight>10.8)){
                limit ="Overnourished";
            }

            else if((height== 74)&&(weight>=7.6 && weight<=11)){
                limit ="Normal Child";
            }else if((height==74)&&( weight>11)){
                limit ="Overnourished";
            }

            else if((height==74.5 )&&(weight>=7.7 && weight<=11.1 )){
                limit ="Normal Child";
            }else if((height==74.5)&&( weight>11.01)){
                limit ="Overnourished";
            }

            else if((height== 75)&&(weight>=7.8 && weight<=11.2)){
                limit ="Normal Child";
            } else if((height==75)&&( weight>11.2 )){
                limit ="Overnourished";
            }

            else if((height== 75.5)&&(weight>=7.9 && weight<=11.3)){
                limit ="Normal Child";
            }else if((height==75.5)&&( weight>11.3 )){
                limit ="Overnourished";
            }

            else if((height== 76)&&(weight>=8 && weight<=11.4)){
                limit ="Normal Child";
            } else if((height==76)&&( weight>11.4)){
                limit ="Overnourished";
            }

            else if((height== 76.5)&&(weight>=8 && weight<=11.5)){
                limit ="Normal Child";
            }else if((height==76.5)&&( weight>11.5 )){
                limit ="Overnourished";
            }

            else if((height== 77)&&(weight>=8.1 && weight<=11.6)){
                limit ="Normal Child";
            } else if((height==77)&&( weight>11.6 )){
                limit ="Overnourished";
            }

            else if((height== 77.5)&&(weight>=8.2 && weight<=11.7 )){
                limit ="Normal Child";
            } else if((height==77.5)&&( weight>11.7 )){
                limit ="Overnourished";
            }

            else if((height== 78)&&(weight>=8.3 && weight<=11.8)){
                limit ="Normal Child";
            }else if((height==78)&&( weight>11.8)){
                limit ="Overnourished";
            }

            else if((height== 78.5)&&(weight>=8.4 && weight<=12.0)){
                limit ="Normal Child";
            }else if((height==78.5)&&( weight>12.0 )){
                limit ="Overnourished";
            }

            else if((height==79 )&&(weight>=8.4&& weight<=12.1)){
                limit ="Normal Child";
            } else if((height==79)&&( weight>12.1)){
                limit ="Overnourished";
            }

            else if((height== 79.5)&&(weight>=8.5 && weight<=12.2)){
                limit ="Normal Child";
            } else if((height==79.5)&&( weight>12.2 )){
                limit ="Overnourished";
            }

            else if((height== 80)&&(weight>=8.6 && weight<=12.3 )){
                limit ="Normal Child";
            }else if((height==80)&&( weight>12.3 )){
                limit ="Overnourished";
            }

            else if((height== 80.5)&&(weight>=8.7 && weight<=12.4)){
                limit ="Normal Child";
            } else if((height==80.5)&&( weight>12.4 )){
                limit ="Overnourished";
            }

            else if((height== 81)&&(weight>=8.8 && weight<=12.6)){
                limit ="Normal Child";
            } else if((height==81)&&( weight>12.6 )){
                limit ="Overnourished";
            }

            else if((height== 81.5)&&(weight>=8.9 && weight<=12.7)){
                limit ="Normal Child";
            } else if((height==81.5)&&( weight>12.7 )){
                limit ="Overnourished";
            }

            else if((height== 82)&&(weight>=9 && weight<=12.8)){
                limit ="Normal Child";
            }else if((height==82)&&( weight>12.8 )){
                limit ="Overnourished";
            }

            else if((height== 82.5)&&(weight>=9.1 && weight<=13)){
                limit ="Normal Child";
            }else if((height==82.5)&&( weight>13 )){
                limit ="Overnourished";
            }

            else if((height== 83)&&(weight>=9.2 && weight<=13.1)){
                limit ="Normal Child";
            }else if((height==83)&&( weight>13.1 )){
                limit ="Overnourished";
            }

            else if((height== 83.5)&&(weight>=9.3 && weight<=13.3)){
                limit ="Normal Child";
            } else if((height==83.5)&&( weight>13.3 )){
                limit ="Overnourished";
            }

            else if((height== 84)&&(weight>=9.4&& weight<=13.4)){
                limit ="Normal Child";
            }else if((height==84)&&( weight>13.4 )){
                limit ="Overnourished";
            }

            else if((height== 84.5)&&(weight>=9.5 && weight<=13.5)){
                limit ="Normal Child";
            } else if((height==84.5)&&( weight>13.5 )){
                limit ="Overnourished";
            }

            else if((height== 85)&&(weight>=9.6 && weight<=13.7 )){
                limit ="Normal Child";
            }else if((height==85)&&( weight>13.7 )){
                limit ="Overnourished";
            }

            else if((height== 85.5)&&(weight>=9.7 && weight<=13.8 )){
                limit ="Normal Child";
            }else if((height==85.5)&&( weight>13.8 )){
                limit ="Overnourished";
            }

            else if((height== 86)&&(weight>=9.8 && weight<=14)){
                limit ="Normal Child";
            } else if((height==86)&&( weight>14 )){
                limit ="Overnourished";
            }

            else if((height== 86.5)&&(weight>=9.9 && weight<=14.2)){
                limit ="Normal Child";
            }else if((height==86.5)&&( weight>14.2 )){
                limit ="Overnourished";
            }
            else if((height== 87)&&(weight>=10&& weight<=14.3)){
                limit ="Normal Child";
            }else if((height==87)&&( weight>14.3 )){
                limit ="Overnourished";
            }
            else if((height== 87.5)&&(weight>=10.1 && weight<=14.5)){
                limit ="Normal Child";
            } else if((height==87.5)&&( weight>14.5 )){
                limit ="Overnourished";
            }
            else if((height== 88)&&(weight>=10.2 && weight<=14.6)){
                limit ="Normal Child";
            }else if((height==88)&&( weight>14.6 )){
                limit ="Overnourished";
            }

            else if((height== 88.5)&&(weight>=10.3 && weight<=14.8)){
                limit ="Normal Child";
            }else if((height==88.5)&&( weight>14.8 )){
                limit ="Overnourished";
            }

            else if((height== 89)&&(weight>=10.4 && weight<=14.9)){
                limit ="Normal Child";
            } else if((height==89)&&( weight>14.9 )){
                limit ="Overnourished";
            }

            else if((height== 89.5)&&(weight>=10.5 && weight<=15.1)){
                limit ="Normal Child";
            } else if((height==89.5)&&( weight>15.1 )){
                limit ="Overnourished";
            }

            else if((height== 90)&&(weight>=10.6 && weight<=15.2)){
                limit ="Normal Child";
            }else if((height==90)&&( weight>15.2 )){
                limit ="Overnourished";
            }

            else if((height== 90.5)&&(weight>=10.7 && weight<=15.4)){
                limit ="Normal Child";
            }else if((height==90.5)&&( weight>15.4 )){
                limit ="Overnourished";
            }

            else if((height== 91)&&(weight>=10.9 && weight<=15.5)){
                limit ="Normal Child";
            }else if((height==91)&&( weight>15.5 )){
                limit ="Overnourished";
            }

            else if((height== 91.5)&&(weight>=11 && weight<=15.7)){
                limit ="Normal Child";
            }else if((height==91.5)&&( weight>15.7 )){
                limit ="Overnourished";
            }

            else if((height== 92)&&(weight>=11.1 && weight<=15.8)){
                limit ="Normal Child";
            }else if((height==92)&&( weight>15.8 )){
                limit ="Overnourished";
            }

            else if((height== 92.5)&&(weight>=11.2 && weight<=16 )){
                limit ="Normal Child";
            } else if((height==92.5)&&( weight>16 )){
                limit ="Overnourished";
            }

            else if((height== 93)&&(weight>=11.3 && weight<=16.1)){
                limit ="Normal Child";
            }else if((height==93)&&( weight>16.1 )){
                limit ="Overnourished";
            }

            else if((height== 93.5)&&(weight>=11.4 && weight<=16.3)){
                limit ="Normal Child";
            }else if((height==93.5)&&( weight>16.3 )){
                limit ="Overnourished";
            }

            else if((height== 94)&&(weight>=11.5 && weight<=16.4)){
                limit ="Normal Child";
            } else if((height==94)&&( weight>16.4 )){
                limit ="Overnourished";
            }

            else if((height== 94.5)&&(weight>=11.6&& weight<=16.6)){
                limit ="Normal Child";
            }else if((height==94.5)&&( weight>16.6 )){
                limit ="Overnourished";
            }

            else if((height== 95)&&(weight>=11.7 && weight<=16.7)){
                limit ="Normal Child";
            }else if((height==95)&&( weight>16.7 )){
                limit ="Overnourished";
            }

            else if((height== 95.5)&&(weight>=11.8 && weight<=16.9)){
                limit ="Normal Child";
            } else if((height==95.5)&&( weight>16.9 )){
                limit ="Overnourished";
            }

            else if((height== 96)&&(weight>=11.9 && weight<=17.0)){
                limit ="Normal Child";
            }else if((height==96)&&( weight>17.0 )){
                limit ="Overnourished";
            }

            else if((height== 96.5)&&(weight>=12.0 && weight<=17.2)){
                limit ="Normal Child";
            }else if((height==96.5)&&( weight>17.2 )){
                limit ="Overnourished";
            }

            else if((height==97 )&&(weight>=12.1 && weight<=17.4)){
                limit ="Normal Child";
            }else if((height==97)&&( weight>17.4 )){
                limit ="Overnourished";
            }

            else if((height== 97.5)&&(weight>=12.2 && weight<=17.5 )){
                limit ="Normal Child";
            }else if((height==97.5)&&( weight>17.5 )){
                limit ="Overnourished";
            }

            else if((height== 98)&&(weight>=12.3 && weight<=17.7)){
                limit ="Normal Child";
            }else if((height==98)&&( weight>17.7 )){
                limit ="Overnourished";
            }

            else if((height== 98.5)&&(weight>=12.4&& weight<=17.9)){
                limit ="Normal Child";
            }else if((height==98.5)&&( weight>17.9 )){
                limit ="Overnourished";
            }

            else if((height== 99)&&(weight>=12.5 && weight<=18.0)){
                limit ="Normal Child";
            }else if((height==99)&&( weight>18.0 )){
                limit ="Overnourished";
            }

            else if((height== 99.5)&&(weight>=12.7 && weight<=18.2 )){
                limit ="Normal Child";
            }else if((height==99.5)&&( weight>18.2 )){
                limit ="Overnourished";
            }

            else if((height== 100)&&(weight>=12.8 && weight<=18.4 )){
                limit ="Normal Child";
            }else if((height==100)&&( weight>18.4 )){
                limit ="Overnourished";
            }

            else if((height== 100.5)&&(weight>=12.9 && weight<=18.6)){
                limit ="Normal Child";
            }else if((height==100.5)&&( weight>18.6 )){
                limit ="Overnourished";
            }

            else if((height== 101)&&(weight>=13.0 && weight<=18.7)){
                limit ="Normal Child";
            }else if((height==101)&&( weight>18.7 )){
                limit ="Overnourished";
            }

            else if((height== 101.5)&&(weight>=13.1 && weight<=18.9)){
                limit ="Normal Child";
            }else if((height==101.5)&&( weight>18.9)){
                limit ="Overnourished";
            }

            else if((height== 102)&&(weight>=13.3 && weight<=19.1)){
                limit ="Normal Child";
            }else if((height==102)&&( weight>19.1 )){
                limit ="Overnourished";
            }

            else if((height== 102.5)&&(weight>=13.4 && weight<=19.3)){
                limit ="Normal Child";
            }else if((height==102.5)&&( weight>19.3 )){
                limit ="Overnourished";
            }

            else if((height== 103)&&(weight>=13.5 && weight<=19.5)){
                limit ="Normal Child";
            }else if((height==103)&&( weight>19.54 )){
                limit ="Overnourished";
            }

            else if((height== 103.5)&&(weight>=13.6 && weight<=19.7 )){
                limit ="Normal Child";
            }else if((height==103.5)&&( weight>19.7 )){
                limit ="Overnourished";
            }

            else if((height== 104)&&(weight>=13.8&& weight<=19.9)){
                limit ="Normal Child";
            }else if((height==104)&&( weight>19.9 )){
                limit ="Overnourished";
            }

            else if((height== 104.5)&&(weight>=13.9 && weight<=20.1)){
                limit ="Normal Child";
            }else if((height==104.5)&&( weight>20.1 )){
                limit ="Overnourished";
            }

            else if((height== 105)&&(weight>=14 && weight<=20.3)){
                limit ="Normal Child";
            }else if((height==105)&&( weight>20.3 )){
                limit ="Overnourished";
            }

            else if((height== 105.5)&&(weight>=14.2&& weight<=20.5)){
                limit ="Normal Child";
            }else if((height==105.5)&&( weight>20.5 )){
                limit ="Overnourished";
            }

            else if((height== 106)&&(weight>=14.3 && weight<=20.8)){
                limit ="Normal Child";
            }else if((height==106)&&( weight>20.8 )){
                limit ="Overnourished";
            }

            else if((height== 106.5)&&(weight>=14.5 && weight<=21)){
                limit ="Normal Child";
            }else if((height==106.5)&&( weight>21 )){
                limit ="Overnourished";
            }

            else if((height==107 )&&(weight>=14.6 && weight<=21.2)){
                limit ="Normal Child";
            }else if((height==107)&&( weight>20.2 )){
                limit ="Overnourished";
            }

            else if((height== 107.5)&&(weight>=14.7 && weight<=21.4)){
                limit ="Normal Child";
            }else if((height==107.5)&&( weight>21.4 )){
                limit ="Overnourished";
            }

            else if((height== 108)&&(weight>=14.9 && weight<=21.7)){
                limit ="Normal Child";
            }else if((height==108)&&( weight>21.7 )){
                limit ="Overnourished";
            }

            else if((height==108.5 )&&(weight>=15.0 && weight<=21.9)){
                limit ="Normal Child";
            }else if((height==108.5)&&( weight>21.9 )){
                limit ="Overnourished";
            }

            else if((height== 109)&&(weight>=15.2 && weight<=22.1)){
                limit ="Normal Child";
            }else if((height==109)&&( weight>22.1)){
                limit ="Overnourished";
            }

            else if((height== 109.5)&&(weight>=15.4 && weight<=22.4)){
                limit ="Normal Child";
            }else if((height==109.5)&&( weight>22.4 )){
                limit ="Overnourished";
            }

            else if((height==110 )&&(weight>=15.5 && weight<=22.6)){
                limit ="Normal Child";
            }else if((height==110)&&( weight>22.6 )){
                limit ="Overnourished";
            }

            else if((height== 110.5)&&(weight>=15.7 && weight<=22.9 )){
                limit ="Normal Child";
            }else if((height==110.5)&&( weight>22.9 )){
                limit ="Overnourished";
            }


            else if((height== 111)&&(weight>=15.8 && weight<=23.1)){
                limit ="Normal Child";
            }else if((height==111)&&( weight>23.1)){
                limit ="Overnourished";
            }


            else if((height== 111.5)&&(weight>=16 && weight<=23.4)){
                limit ="Normal Child";
            }else if((height==111.5)&&( weight>23.4)){
                limit ="Overnourished";
            }

            else if((height== 112)&&(weight>=16.2 && weight<=23.6)){
                limit ="Normal Child";
            }else if((height==112)&&( weight>23.6 )){
                limit ="Overnourished";
            }

            else if((height== 112.5)&&(weight>=16.3 && weight<=23.9)){
                limit ="Normal Child";
            }else if((height==112.5)&&( weight>23.9 )){
                limit ="Overnourished";
            }

            else if((height== 113)&&(weight>=16.5 && weight<=24.2)){
                limit ="Normal Child";
            }else if((height==113)&&( weight>24.2)){
                limit ="Overnourished";
            }

            else if((height== 113.5)&&(weight>=16.7 && weight<=24.4)){
                limit ="Normal Child";
            }else if((height==113.5)&&( weight>24.4 )){
                limit ="Overnourished";
            }

            else if((height== 114)&&(weight>=16.8 && weight<=24.7)){
                limit ="Normal Child";
            }else if((height==114)&&( weight>24.7)){
                limit ="Overnourished";
            }

            else if((height== 114.5)&&(weight>=17.0 && weight<=25)){
                limit ="Normal Child";
            }else if((height==114.5)&&( weight>25 )){
                limit ="Overnourished";
            }

            else if((height== 115)&&(weight>=17.2 && weight<=25.2)){
                limit ="Normal Child";
            }else if((height==115)&&( weight>25.2 )){
                limit ="Overnourished";
            }

            else if((height== 115.5)&&(weight>=17.3 && weight<=25.5)){
                limit ="Normal Child";
            }else if((height==115.5)&&( weight>25.5 )){
                limit ="Overnourished";
            }

            else if((height== 116)&&(weight>=17.5 && weight<=25.8)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>25.76 )){
                limit ="Overnourished";
            }

            else if((height== 116.5)&&(weight>=17.7 && weight<=26.1)){
                limit ="Normal Child";
            }else if((height==116)&&( weight>26.1)){
                limit ="Overnourished";
            }

            else if((height== 117)&&(weight>=17.8 && weight<=26.3)){
                limit ="Normal Child";
            }else if((height==117)&&( weight>26.3 )){
                limit ="Overnourished";
            }

            else if((height== 117.5)&&(weight>=18.0 && weight<=26.6)){
                limit ="Normal Child";
            }else if((height==117.5)&&( weight>26.6 )){
                limit ="Overnourished";
            }

            else if((height== 118)&&(weight>=18.2 && weight<=26.9)){
                limit ="Normal Child";
            }else if((height==118)&&( weight>26.9 )){
                limit ="Overnourished";
            }

            else if((height== 118.5)&&(weight>=18.4 && weight<=27.2)){
                limit ="Normal Child";
            }else if((height==118.5)&&( weight>27.2 )){
                limit ="Overnourished";
            }

            else if((height== 119)&&(weight>=18.5 && weight<=27.4)){
                limit ="Normal Child";
            }else if((height==119)&&( weight>27.4)){
                limit ="Overnourished";
            }

            else if((height== 119.5)&&(weight>=18.7 && weight<=27.7)){
                limit ="Normal Child";
            }else if((height==119.5)&&( weight>27.7 )){
                limit ="Overnourished";
            }

            else if((height== 120)&&(weight>=18.9 && weight<=28)){
                limit ="Normal Child";
            }else if((height==120)&&( weight>28)){
                limit ="Overnourished";
            }

            else {limit="Wasted";}

        }

        return limit;


    }

    //////method to get MUAC results//////////////
    public String getMuac(double muac){
        String value="";

        if(muac < 11.5)
            value = "Severe Malnourished";
        else if (muac>= 11.5 && muac<=12.5) {
            value  = "Moderate Malnourished";
        }
        else{value = "Normal ";}

        return value;

    }

    public void result() {
        /// variables to be used when converting string values to integers and double
        int ageY, ageM;
        double w, h, m;

        final EditText weight = (EditText) findViewById(R.id.edit1);
        final EditText height = (EditText) findViewById(R.id.edit2);
        final EditText muac = (EditText) findViewById(R.id.edit3);
        final EditText agey = (EditText) findViewById(R.id.edit4);
        final EditText agem = (EditText) findViewById(R.id.edit5);

        /////get input from the user//////////////
        weight_ = weight.getText().toString();
        height_ = height.getText().toString();
        muac_ = muac.getText().toString();
        age_y = agey.getText().toString();
        age_m = agem.getText().toString();

            ////copy weight and height values
           weightValue = weight_;
           heightValue = height_;

        /// check if radio button checked///
        RadioButton male, female,oedemaYes,oedemaNo;

        oedemaYes=(RadioButton)findViewById(R.id.oedemaYes);
        oedemaNo=(RadioButton)findViewById(R.id.oedemaNo);

        male = (RadioButton) findViewById(R.id.radMale);
        female = (RadioButton) findViewById(R.id.radFemale);

        /// validate user input////////

        if (TextUtils.isEmpty(weight_)) {
            Toast.makeText(this, " Enter Weight value ", Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(height_)){
            Toast.makeText(this," Enter Height value ",Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(muac_)) {
            Toast.makeText(this, " Enter MUAC value ", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(age_y)) {
            Toast.makeText(this, " Enter Age in years ", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(age_m)) {
            Toast.makeText(this, " Enter Age in months ", Toast.LENGTH_LONG).show();
        }
        else {
            ///convert string to int and double
            w = Double.parseDouble(weight_);
            h = Double.parseDouble(height_);
            m = Double.parseDouble(muac_);
            ageY=Integer.parseInt(age_y);
            ageM=Integer.parseInt(age_m);
            //////// get total months
            int MonthsTotal = (ageY*12) + ageM;

            ///// convert the above to string

            String monthsTotal = Integer.toString(MonthsTotal);

            //////copy some of the above
            weight_copy = w;
            height_copy = h;

            ///// handle age input before giving feedback to user//////////
        if (ageY>5||(ageY==5 && ageM>0)){
            alertYears();

        }
        else if (ageM>12){
            alertMonths();
        }
        else if (w> 30){
            Context context;

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Sorry, under-five child cannot be "+ w + " kg");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Try again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else if (h > 150){
            Context context;
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Sorry, under-five child cannot be "+ h + " cm in height");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Try again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else if (m> 40){
            Context context;

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Sorry, under-five child cannot have "+ m + " cm Mid Upper Arm Circumference");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Try again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        else {

            if (oedemaNo.isChecked()) {


                if (male.isChecked()) {
                        String oedemaResult  ="Negative";
                        String weightResult  = maleWeight(w, ageY, ageM);
                        String heightResult  = maleHeight(h,ageY,ageM);
                        String wastingResult =maleWasting(h,w,ageY,ageM);
                        String muacResult    = getMuac(m);

                        //////Pass the results into general results activity
                      Intent intent = new Intent(this,generalResults.class);
                      intent.putExtra("oedemaID",oedemaResult);
                      intent.putExtra("weightID",weightResult);
                      intent.putExtra("heightID",heightResult);
                      intent.putExtra("wastingID",wastingResult);
                      intent.putExtra("muacID",muacResult);
                      intent.putExtra("weightValueID",weightValue);
                      intent.putExtra("heightValueID",heightValue);
                    ////// pass the total age in months only to save to the database
                      intent.putExtra("monthsTotal",monthsTotal);

                      ////pass weight and height vaalues directly
                      intent.putExtra("weightDouble",w);
                      intent.putExtra("heightDouble",h);
                      startActivity(intent);
                } else if (female.isChecked()) {

                    String oedemaResult  ="Negative";
                    String weightResult  = maleWeight(w, ageY, ageM);
                    String heightResult  = maleHeight(h,ageY,ageM);
                    String wastingResult =maleWasting(h,w,ageY,ageM);
                    String muacResult    = getMuac(m);

                    //////Pass the results into general results activity
                    Intent intent = new Intent(this,generalResults.class);
                    intent.putExtra("oedemaID",oedemaResult);
                    intent.putExtra("weightID",weightResult);
                    intent.putExtra("heightID",heightResult);
                    intent.putExtra("wastingID",wastingResult);
                    intent.putExtra("muacID",muacResult);
                    intent.putExtra("weightValueID",weightValue);
                    intent.putExtra("heightValueID",heightValue);

                    ////pass weight and height vaalues directly
                    intent.putExtra("weightDouble",w);
                    intent.putExtra("heightDouble",h);
                    ////// pass the total age in months only to save to the database
                    intent.putExtra("monthsTotal",monthsTotal);

                    startActivity(intent);

                } else {
                    alertRadioButtonCheck();
                }
            } else if (oedemaYes.isChecked()) {
                if (male.isChecked()) {
                    alertOedema();

                } else if (female.isChecked()) {
                    alertOedema();

                } else {
                    alertRadioButtonCheck();
                }


            } else {
                alertOedemaRadioButtonCheck();
            }
          }
        }
    }

}