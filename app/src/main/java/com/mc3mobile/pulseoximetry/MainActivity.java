package com.mc3mobile.pulseoximetry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.utils.XLabels;
//import com.github.mikephil.charting.utils.XLabels.XLabelPosition;

import com.mc3mobile.pulseoximetry.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Pop.class));
            }

        });

    } */

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To make full screen layout
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Line Chart 1

        mChart = (LineChart) findViewById(R.id.linechart); //defines linchart in xml

        int[] data1 = {0,5,10,15,20,25,30,35,40,45}; // x and Y data values
        int[] data2 = {97,98,96,98,97,96,97,98,99,97}; //see above

        // Trying to figure out how to find the average and display it on the activity_main.xml

        double sum = 0;
        for(int i=0; i < data2.length ; i++)
            sum = sum + data2[i];
        TextView average = (TextView)findViewById(R.id.average);
        double result = sum / data2.length;
        average.setText("Running Average: " + result);


        List<Entry> entries = new ArrayList<>(); // container for x,y entries

        for (int i = 0; i < data1.length; i++) { //for loop, puts data in Entry

            entries.add(new Entry(data1[i], data2[i])); //defines fill
        }

        LineDataSet dataSet = new LineDataSet(entries, "O2 Saturation"); // sets as a line, add entries to dataset
        //dataSet.setColor(...);
        //dataSet.setValueTextColor(...); // styling, ...

        LineData lineData = new LineData(dataSet); //setting data
        mChart.setData(lineData); //drawing the line
        mChart.invalidate(); // refresh

        XAxis xAxis1 = mChart.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Line Chart 2

        mChart = (LineChart) findViewById(R.id.linechart2); //defines linchart in xml

        int[] data3 = {0,5,10,15,20,25,30,35,40,45}; // x and Y data values
        int[] data4 = {61,62,63,61,63,62,61,63,63,61}; //see above

        double sum2 = 0;
        for(int i=0; i < data4.length ; i++)
            sum2 = sum2 + data4[i];
        TextView average2 = (TextView)findViewById(R.id.average2);
        double result2 = sum2 / data4.length;
        average2.setText("Running Average: " + result2);

       List<Entry> entries2 = new ArrayList<>(); // container for x,y entries

        for (int i = 0; i < data3.length; i++) { //for loop, puts data in Entry

            entries2.add(new Entry(data3[i], data4[i])); //defines fill
        }

        LineDataSet dataSet2 = new LineDataSet(entries2, "Pulse Rate"); // sets as a line, add entries to dataset
        //dataSet.setColor();
        //dataSet.setValueTextColor(...); // styling, ...

        LineData lineData2 = new LineData(dataSet2); //setting data
        mChart.setData(lineData2); //drawing the line
        mChart.invalidate(); // refresh

        XAxis xAxis2 = mChart.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);

        }
}