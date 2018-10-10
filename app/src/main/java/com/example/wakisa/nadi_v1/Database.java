package com.example.wakisa.nadi_v1;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class Database extends AppCompatActivity {
    /////////////create an sqlite db object//////////

    DatabaseHelper myDb;
    AlertDialog.Builder builder;
    Button viewData,uploadButton,followButton,profileButton;

    String server_ur2 ="http://192.168.43.58/nadi/login.php";
    String server_url ="http://192.168.43.58/nadi/login.php";
    String server_ur3 = "http://192.168.43.58/nadi/connection.php"; ///// url to verify if connected to the server

    boolean result = false; //// this variable used to check if server has positive response
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myDb = new DatabaseHelper(this);
        builder = new AlertDialog.Builder(Database.this);

        viewData=(Button)findViewById(R.id.viewdata);
        uploadButton=(Button)findViewById(R.id.upload);
        followButton=(Button)findViewById(R.id.follow);
        profileButton=(Button)findViewById(R.id.profile);

     uploadToServer(); // function for testing on a cloud server

     //send();   // function for testing on localhost
      viewData();

    }

    //////// upload data to cloud server//////////
    public void uploadToServer(){

        uploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Cursor res = myDb.getAllData();
                ///////check if there is data in local sqlite database
                if (res.getCount() == 0) {
                    showMessage("Response ","Sorry no data found!");

                }
                else {
                ////check if there's internet connection//////////////////////
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                    final ProgressDialog dialog = ProgressDialog.show(Database.this, "Data Upload",
                            getString(R.string.progress), true);

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_ur3,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        ////send Data to database
                                        send();
                                        ///dismiss progress bar////
                                        dialog.dismiss();
                                        /////Respond upon successfully uploading Data
                                        builder.setTitle("Server Response");
                                        builder.setMessage("Data Uploaded successfully!! ");
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                ///dismiss progress bar////
                                dialog.dismiss();
                                /////Respond upon successfully uploading Data
                                builder.setTitle("Error Response");
                                builder.setMessage("Sorry Connection to server failed!! ");
                                builder.setPositiveButton("Try Again ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        }) {

                        };
                        Mysingleton.getmInstance(Database.this).addToRequestque(stringRequest);
                    }
                //////////RESPOND IF THERE IS NO INTERNET CONNECTION///////
                else{
                    builder.setTitle("Error Response");
                    builder.setMessage("Sorry No Internet connection" );
                    builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
             }

            }
        });



    }
    public void getFromSQLite( final String bcn,final String months,final String weight,final String height,final String wasting,final String oedema,final String muac){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_ur2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("bcn", bcn);
                params.put("months", months);
                params.put("weight", weight);
                params.put("height", height);
                params.put("wasting",wasting);
                params.put("oedema", oedema);
                params.put("muac", muac);

                return params;
            }
        };
        Mysingleton.getmInstance(Database.this).addToRequestque(stringRequest);
    }

    ///////send data to mysql server on local host//////////
    public void send() {
        final SQLiteDatabase db ;
            uploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Cursor res = myDb.getAllData();
                    //we are connected to a network
                    while ( res.moveToNext()) {
                        getFromSQLite(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7));
                        //////delete data upon uploading////
                        myDb.deleteCreateTable(myDb.TABLE_NAME);
                    }
                }
            });
     }
    public void viewData(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount()==0){
                    showMessage("Response ","Sorry No data found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()){
                    buffer.append("Id : "+"\t"+"\t"+"\t"+"\t"+ res.getString(0)+"\n"+"\n");
                    buffer.append("BCN : "+"\t"+"\t"+"\t"+ res.getString(1)+"\n"+"\n");
                    buffer.append("Months :"+"\t"+"\t"+ res.getString(2)+"\n"+"\n");
                    buffer.append("Weight: "+"\t"+"\t"+"\t"+ res.getString(3)+"\n"+"\n");
                    buffer.append("Height: "+"\t"+"\t"+"\t"+ res.getString(4)+"\n"+"\n");
                    buffer.append("Wasting :"+"\t"+"\t"+ res.getString(5)+"\n"+"\n");
                    buffer.append("Oedema:"+"\t"+"\t"+ res.getString(6)+"\n"+"\n");
                    buffer.append("MUAC  :"+"\t"+"\t"+"\t"+ res.getString(7)+"\n"+"\n"+"\n"+"\n");
                }
                showMessage(" DATA ",buffer.toString());

            }
        });
    }
    public  void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}