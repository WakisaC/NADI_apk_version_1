package com.example.wakisa.nadi_v1;

import android.content.Intent;
import android.content.*;
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
import android.widget.*;

import java.lang.*;



public class underweight extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underweight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button b = (Button)findViewById(R.id.wbtn);


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
        getMenuInflater().inflate(R.menu.underweight, menu);
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
            feedback();

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
    public void feedback(){
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

    ///overweight advice//////////////
    public void overWeightAdvice() {
        Intent intent = new Intent(this,overweightAdvice.class);
        startActivity(intent);

    }
    public void underwWeightAdvice(){
        Intent intent = new Intent(this,underweightAdvice.class);
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
    public void alertUnder(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry the child is underweight. Go to advice?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        underwWeightAdvice();
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
    public void alertOver(){
        Context context;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Sorry the child is overweight. Go to advice?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        overWeightAdvice();
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

    //method to get weight of Malechild
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

    public void results(){
        int ageY,ageM;
        double w;
        String weight_,age_y,age_m;

        final EditText weight =(EditText)findViewById(R.id.edit1);
        final EditText agey =(EditText)findViewById(R.id.edit2);
        final EditText agem =(EditText)findViewById(R.id.edit3);

        ///get input from the user///////
        weight_= weight.getText().toString();

        age_y=agey.getText().toString();

        age_m=agem.getText().toString();


        /// check if radio button checked///
        RadioButton male,female;

        male =(RadioButton)findViewById(R.id.rMale);
        female = (RadioButton)findViewById(R.id.rFemale);

        // validate user input////////

        if (TextUtils.isEmpty(weight_)){
            Toast.makeText(this," Enter Weight value ",Toast.LENGTH_LONG).show();

        }
        else if (TextUtils.isEmpty(age_y)){
            Toast.makeText(this," Enter Age in years ",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(age_m)){
            Toast.makeText(this," Enter Age in months ",Toast.LENGTH_LONG).show();
        }

        else {
            ///convert string to int and double
            w = Double.parseDouble(weight_);
            ageY = Integer.parseInt(age_y);
            ageM = Integer.parseInt(age_m);

            ///// handle age input before giving feedback to user//////////
            if (ageY > 5 || (ageY == 5 && ageM > 0)) {
                alertYears();

            }
            else if (ageM > 12) {
                alertMonths();
            }
            else if (w > 150){
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
            else {

                if (male.isChecked()) {

                    String growth = maleWeight(w, ageY, ageM);

                    if (growth == "Normal weight")
                        //alert user the childs normality
                        alertNormal();
                    else if (growth == "Underweight")
                        alertUnder();
                    else if (growth == "Overweight")
                        alertOver();


                } else if (female.isChecked()) {

                    String growth = femaleWeight(w, ageY, ageM);

                    if (growth == "Normal weight")
                        //alert user the childs normality
                        alertNormal();
                    else if (growth == "Underweight")
                        alertUnder();
                    else if (growth == "Overweight")
                        alertOver();

                } else {
                    // alert user to choose gender

                    alertRadioButtonCheck();
                }
            }
        }
    }

}
