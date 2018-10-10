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
import android.widget.Toast;
import android.widget.*;

import android.content.*;

public class stunting extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button b =(Button)findViewById(R.id.hbtn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            results();

            }
        });
    }

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
        getMenuInflater().inflate(R.menu.height, menu);
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
            feedback1();

        } else if (id == R.id.nav_notes) {
            notes();

        }else if (id == R.id.nav_About) {
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
    public void feedback1(){
        Intent intent = new Intent(this,Feedback.class);
        startActivity(intent);
    }
    public void database(){
        Intent intent = new Intent(this,Database.class);
        startActivity(intent);
    }
    public void notes(){
        Intent intent = new Intent(this,NoteMain.class);
        startActivity(intent);
    }

    ////Stunted advice method//////////
    public void stuntedAdvice(){
       Intent intent = new Intent(this,stuntingAdvice.class);
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
                        stuntedAdvice();

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

    //methods to get childs height

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



    public void results(){
        int ageY,ageM;
        double h;
        String height_,age_y,age_m;

        final EditText height =(EditText)findViewById(R.id.edit1);
        final EditText agey =(EditText)findViewById(R.id.edit2);
        final EditText agem =(EditText)findViewById(R.id.edit3);

        height_= height.getText().toString();


        age_y=agey.getText().toString();


        age_m=agem.getText().toString();


        /// check if radio button checked///
        RadioButton male,female;

        male =(RadioButton)findViewById(R.id.rMale);
        female = (RadioButton)findViewById(R.id.rFemale);

        if (TextUtils.isEmpty(height_)){
            Toast.makeText(this," Enter Height value ",Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(age_y)){
            Toast.makeText(this," Enter Age in years ",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(age_m)){
            Toast.makeText(this," Enter Age in months ",Toast.LENGTH_LONG).show();
        }
        else{
            ///convert string to int and double
            h = Double.parseDouble(height_);
            ageY=Integer.parseInt(age_y);
            ageM=Integer.parseInt(age_m);

            ///// handle age input before giving feedback to user//////////
            if (ageY > 5 || (ageY == 5 && ageM > 0)) {
                alertYears();

            }
            else if (ageM > 12) {
                alertMonths();
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
            else {

                if (male.isChecked()){

                      String growth = maleHeight(h,ageY,ageM);

                    if (growth=="Normal Child Height")
                        //alert user the childs normality
                        alertNormal();
                    else if (growth=="Stunted child")
                        alertStunted();
                    else if(growth=="Tall child"){
                        alertTall();
                    }


                 }
                 else if (female.isChecked()){

                String growth = femaleHeight(h,ageY,ageM);

                if (growth=="Normal Child Height")
                    //alert user the childs normality
                    alertNormal();
                else if (growth=="Stunted child")
                    alertStunted();
                else if(growth=="Tall child"){
                    alertTall();
                }

                }
                else{
                // alert user to choose gender
                alertRadioButtonCheck();
            }
          }
        }





    }



}
