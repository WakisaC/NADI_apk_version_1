package com.example.wakisa.nadi_v1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graph extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper myDb;
    MyHelper myHelper;
    Button button;


    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //////////////////////////////////myhelper things///////////////
        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();




        /////////////graph of height  for boys/////////////////////
        GraphView graph = (GraphView)findViewById(R.id.graphHeight);

        LineGraphSeries<DataPoint> Cseries = new LineGraphSeries<>(getTheData_());
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(getDataPoint2());
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(getDataPoint3());

        //////////////the positive side////////////////
        LineGraphSeries<DataPoint> series_1 = new LineGraphSeries<>(getDataPoint4());
        LineGraphSeries<DataPoint> series_2 = new LineGraphSeries<>(getDataPoint5());

        graph.addSeries(series);    ///-3SD
        graph.addSeries(series2); ///-2SD
        graph.addSeries(series3); ///MEDIAN
        graph.addSeries(Cseries);

        graph.setTitle("Length/height-for-age BOYS");

        //////////////the positive side////////////////
        graph.addSeries(series_1);
        graph.addSeries(series_2);

        series.setColor(Color.RED);
        series.setThickness(1);
        series2.setColor(Color.GREEN);
        series2.setThickness(1);
        series3.setColor(Color.GRAY);
        series3.setThickness(2);

        //////////////the positive side////////////////
        series_1.setColor(Color.GREEN);
        series_1.setThickness(1);
        series_2.setColor(Color.RED);
        series_2.setThickness(1);

    }

    /////////// graphs for height/////////

    private DataPoint[]getData_(){
        String[] column ={"Age","Length"};
        Cursor cursor = sqLiteDatabase.query("MyTable",column,null,null,null,null,null);

        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for (int i =0;i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0),cursor.getInt(1));
        }
        return dp;
    }
    private DataPoint[] getDataPoint(){
        DataPoint[]dp =new DataPoint[]{
                new DataPoint(0,44.2),new DataPoint(1,48.9),new DataPoint(2,52.4),  new DataPoint(3,55.3),new DataPoint(4,57.6),new DataPoint(5,59.6),
                new DataPoint(6,61.2),new DataPoint(7,62.7),new DataPoint(8,64),  new DataPoint(9,65.2),new DataPoint(10,66.4),new DataPoint(11,67.6),
                new DataPoint(12,68.6),new DataPoint(13,69.6),new DataPoint(14,70.6),  new DataPoint(15,71.6),new DataPoint(16,72.5),new DataPoint(17,73.3),
                new DataPoint(18,74.2),new DataPoint(19,75.0),new DataPoint(20,75.8),  new DataPoint(21,76.5),new DataPoint(22,77.2),new DataPoint(23,78),
                new DataPoint(24,78.7),new DataPoint(24,78.0),new DataPoint(25,78.6),new DataPoint(26,79.3),  new DataPoint(27,79.9),new DataPoint(28,80.5),new DataPoint(29,81.1),
                new DataPoint(30,81.7),new DataPoint(31,82.3),new DataPoint(32,82.8),  new DataPoint(33,83.4),new DataPoint(34,83.9),new DataPoint(35,84.4),
                new DataPoint(36,85),new DataPoint(37,85.5),new DataPoint(38,86),  new DataPoint(39,86.5),new DataPoint(40,87),new DataPoint(41,87.5),
                new DataPoint(42,88),new DataPoint(43,88.4),new DataPoint(44,88.9),  new DataPoint(45,89.4),new DataPoint(46,89.8),new DataPoint(47,90.3),
                new DataPoint(48,90.7),new DataPoint(49,91.2),new DataPoint(50,91.6),  new DataPoint(51,92.1),new DataPoint(52,92.5),new DataPoint(53,93.0),
                new DataPoint(54,93.4),new DataPoint(55,93.9),new DataPoint(56,94.3),  new DataPoint(57,94.7),new DataPoint(58,95.2),new DataPoint(59,95.6),
                new DataPoint(60,96.1)


        };
        return (dp);
    }
    private DataPoint[] getDataPoint2(){
        DataPoint[]dp =new DataPoint[]{
                new DataPoint(0,46.1),new DataPoint(1,50.8),new DataPoint(2,54.4),  new DataPoint(3,57.3),new DataPoint(4,59.7),new DataPoint(5,61.7),
                new DataPoint(6,63.3),new DataPoint(7,64.8),new DataPoint(8,66.2),  new DataPoint(9,67.5),new DataPoint(10,68.7),new DataPoint(11,69.9),
                new DataPoint(12,71),new DataPoint(13,72.1),new DataPoint(14,73.1),  new DataPoint(15,74.1),new DataPoint(16,75),new DataPoint(17,76),
                new DataPoint(18,76.9),new DataPoint(19,77.7),new DataPoint(20,78.6),  new DataPoint(21,79.4),new DataPoint(22,80.2),new DataPoint(23,81),
                new DataPoint(24,81.7),new DataPoint(24,81),new DataPoint(25,81.7),new DataPoint(26,82.5),  new DataPoint(27,83.1),new DataPoint(28,83.8),new DataPoint(29,84.5),
                new DataPoint(30,85.1),new DataPoint(31,85.7),new DataPoint(32,86.4),  new DataPoint(33,86.9),new DataPoint(34,87.5),new DataPoint(35,88.1),
                new DataPoint(36,88.7),new DataPoint(37,89.2),new DataPoint(38,89.8),  new DataPoint(39,90.3),new DataPoint(40,90.9),new DataPoint(41,91.4),
                new DataPoint(42,91.9),new DataPoint(43,92.4),new DataPoint(44,93),  new DataPoint(45,93.5),new DataPoint(46,94),new DataPoint(47,94.4),
                new DataPoint(48,94.9),new DataPoint(49,95.4),new DataPoint(50,95.9),  new DataPoint(51,96.4),new DataPoint(52,96.9),new DataPoint(53,97.4),
                new DataPoint(54,97.8),new DataPoint(55,98.3),new DataPoint(56,98.8),  new DataPoint(57,99.3),new DataPoint(58,99.7),new DataPoint(59,100.2),
                new DataPoint(60,100.7)




        };
        return (dp);
    }
    private DataPoint[] getDataPoint3(){
        DataPoint[]dp =new DataPoint[]{
                new DataPoint(0,49.9),new DataPoint(1,54.7),new DataPoint(2,58.4),  new DataPoint(3,61.4),new DataPoint(4,63.9),new DataPoint(5,65.9),
                new DataPoint(6,67.6),new DataPoint(7,69.2),new DataPoint(8,70.6),  new DataPoint(9,72.0),new DataPoint(10,73.3),new DataPoint(11,74.5),
                new DataPoint(12,75.7),new DataPoint(13,76.9),new DataPoint(14,78),  new DataPoint(15,79.1),new DataPoint(16,80.2),new DataPoint(17,81.2),
                new DataPoint(18,82.3),new DataPoint(19,83.2),new DataPoint(20,84.2),  new DataPoint(21,85.1),new DataPoint(22,86.0),new DataPoint(23,86.9),
                new DataPoint(24,87.8),new DataPoint(24,87.1),new DataPoint(25,88),new DataPoint(26,88.8),  new DataPoint(27,89.6),new DataPoint(28,90.4),new DataPoint(29,91.2),
                new DataPoint(30,91.9),new DataPoint(31,92.7),new DataPoint(32,93.4),  new DataPoint(33,94.1),new DataPoint(34,94.8),new DataPoint(35,95.4),
                new DataPoint(36,96.1),new DataPoint(37,96.7),new DataPoint(38,97.4),  new DataPoint(39,98),new DataPoint(40,98.6),new DataPoint(41,99.2),
                new DataPoint(42,99.9),new DataPoint(43,100.4),new DataPoint(44,101),  new DataPoint(45,101.6),new DataPoint(46,102.2),new DataPoint(47,102.8),
                new DataPoint(48,103.3),new DataPoint(49,103.9),new DataPoint(50,104.4),  new DataPoint(51,105),new DataPoint(52,105.6),new DataPoint(53,106.1),
                new DataPoint(54,106.7),new DataPoint(55,107.2),new DataPoint(56,107.8),  new DataPoint(57,108.3),new DataPoint(58,108.9),new DataPoint(59,109.4),
                new DataPoint(60,110)




        };
        return (dp);
    }

    private DataPoint[]getDataPoint4(){
        DataPoint[]dp =new DataPoint[]{
                new DataPoint(0,53.7),new DataPoint(1,58.6),new DataPoint(2,62.4),  new DataPoint(3,65.5),new DataPoint(4,68),new DataPoint(5,70.1),
                new DataPoint(6,71.9),new DataPoint(7,73.5),new DataPoint(8,75),  new DataPoint(9,76.5),new DataPoint(10,77.9),new DataPoint(11,79.2),
                new DataPoint(12,80.5),new DataPoint(13,81.8),new DataPoint(14,83),  new DataPoint(15,84.2),new DataPoint(16,85.4),new DataPoint(17,86.5),
                new DataPoint(18,87.7),new DataPoint(19,88.8),new DataPoint(20,89.8),  new DataPoint(21,90.9),new DataPoint(22,91.9),new DataPoint(23,92.9),
                new DataPoint(24,93.9),new DataPoint(24,93.2),new DataPoint(25,94.2),new DataPoint(26,95.2),  new DataPoint(27,96.1),new DataPoint(28,97.),new DataPoint(29,97.9),
                new DataPoint(30,98.7),new DataPoint(31,99.6),new DataPoint(32,100.4),  new DataPoint(33,101.2),new DataPoint(34,102),new DataPoint(35,102.7),
                new DataPoint(36,103.5),new DataPoint(37,104.2),new DataPoint(38,105),  new DataPoint(39,105.7),new DataPoint(40,106.4),new DataPoint(41,107.1),
                new DataPoint(42,107.8),new DataPoint(43,108.5),new DataPoint(44,109.1),  new DataPoint(45,109.8),new DataPoint(46,110.4),new DataPoint(47,111.1),
                new DataPoint(48,111.7),new DataPoint(49,112.4),new DataPoint(50,113.0),  new DataPoint(51,113.6),new DataPoint(52,114.2),new DataPoint(53,114.9),
                new DataPoint(54,115.5),new DataPoint(55,116.1),new DataPoint(56,116.7),  new DataPoint(57,117.4),new DataPoint(58,118),new DataPoint(59,118.6),
                new DataPoint(60,119.2)




        };
        return (dp);
    }
    private DataPoint[] getDataPoint5(){
        DataPoint[]dp =new DataPoint[]{
                new DataPoint(0,55.6),new DataPoint(1,60.6),new DataPoint(2,64.4),  new DataPoint(3,67.6),new DataPoint(4,70.1),new DataPoint(5,72.2),
                new DataPoint(6,74),new DataPoint(7,75.7),new DataPoint(8,77.2),  new DataPoint(9,78.7),new DataPoint(10,80.1),new DataPoint(11,81.5),
                new DataPoint(12,82.9),new DataPoint(13,84.2),new DataPoint(14,85.5),  new DataPoint(15,86.7),new DataPoint(16,88),new DataPoint(17,89.2),
                new DataPoint(18,90.4),new DataPoint(19,91.5),new DataPoint(20,92.6),  new DataPoint(21,93.8),new DataPoint(22,94.9),new DataPoint(23,95.9),
                new DataPoint(24,97),new DataPoint(24,96.3),new DataPoint(25,97.3),new DataPoint(26,98.3),  new DataPoint(27,99.3),new DataPoint(28,100.3),new DataPoint(29,101.2),
                new DataPoint(30,102.1),new DataPoint(31,103),new DataPoint(32,103.9),  new DataPoint(33,104.8),new DataPoint(34,105.6),new DataPoint(35,106.4),
                new DataPoint(36,107.2),new DataPoint(37,108.0),new DataPoint(38,108.8),  new DataPoint(39,109.5),new DataPoint(40,110.3),new DataPoint(41,111),
                new DataPoint(42,111.7),new DataPoint(43,112.5),new DataPoint(44,113.2),  new DataPoint(45,113.9),new DataPoint(46,114.6),new DataPoint(47,115.2),
                new DataPoint(48,115.9),new DataPoint(49,116.6),new DataPoint(50,117.3),  new DataPoint(51,117.9),new DataPoint(52,118.6),new DataPoint(53,119.2),
                new DataPoint(54,119.9),new DataPoint(55,120.6),new DataPoint(56,121.2),  new DataPoint(57,121.9),new DataPoint(58,122.6),new DataPoint(59,123.2),
                new DataPoint(60,123.9)


        };
        return (dp);
    }

  private DataPoint[]getTheData_(){
        String [] columns = {"months","height"};
        Cursor cursor = sqLiteDatabase.query(myDb.TABLE_NAME,columns,null,null,null,null,null);

        DataPoint[]dp =new DataPoint[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i]=new DataPoint(cursor.getInt(0),cursor.getInt(1));
        }
        return dp;
  }




}
