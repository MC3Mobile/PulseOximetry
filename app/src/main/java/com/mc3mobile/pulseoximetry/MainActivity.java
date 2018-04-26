package com.mc3mobile.pulseoximetry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mc3mobile.pulseoximetry.R;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    /*@Override
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

    }*/

    // chart_two_activity_fragment_container
    ArrayList<Integer> time = new ArrayList<>();
    ArrayList<Integer> hr = new ArrayList<>();
    int count = 0;
    int heart = 85;
    int critical = 1;
    private LineChart mChart;
    /***********************************************************************************************
     *
     */
    public static Intent newIntent(Context context){
        return new Intent(context, MainActivity.class);
    }
    /***********************************************************************************************
     *
     */
    private void loadLineChart(ArrayList<Integer> time, ArrayList<Integer> hr){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = LineChartFragment.newInstance(time, hr);
        fragmentManager.beginTransaction().
                replace(R.id.chart_two_activity_fragment_container, fragment).commit();
    }
    /***********************************************************************************************
     *
     //time increment decision
    private void execute() {

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                incrementData();
                //checkCriticalHeart(hr.get(hr.size()-1)); //This crashes the app
                loadLineChart(time, hr);
            }
        }, 0, 1000, TimeUnit.MILLISECONDS); // 1 seconds

    }

    /***********************************************************************************************
     *
     //Y value randomization
    private void incrementData(){

        if (count % 30 == 0 && critical % 2 == 0) {
            heart = 121;
            critical++;
        } else if (count % 30 == 0 && critical % 2 != 0){
            heart = 85;
            critical++;
        }

        time.add(count);
        hr.add(heart);

        count++;

        if (count % 2 == 0) {
            heart++;
        } else {
            heart--;
        }


        if (time.size() > 9) {
            time = new ArrayList<>(time.subList(1, 10));
            hr = new ArrayList<>(hr.subList(1, 10));

            for (int i = 0; i < time.size(); i++){
                Log.i("trace", "In time: " + time.get(i));
            }
        }

        Log.i("trace", Integer.toString(heart) + " " + Integer.toString(count));
    }

    /***********************************************************************************************
     *
     */


  @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To make full screen layout
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        execute();

        // Line Chart 1

        mChart = (LineChart) findViewById(R.id.linechart); //Defines the linechart in xml

        int[] data1 = {0,5,10,15,20,25,30,35,40,45}; //X data values
        int[] data2 = {97,98,96,98,97,96,97,98,99,97}; //Y data labels

        double sum = 0;
        for(int i=0; i < data2.length ; i++)
            sum = sum + data2[i];
        TextView average = (TextView)findViewById(R.id.average);
        double result = sum / data2.length;
        average.setText("Running Average: " + result);

        List<Entry> entries = new ArrayList<>(); //Container for X and Y entries

        for (int i = 0; i < data1.length; i++) { //For loop that puts data in the entry

            entries.add(new Entry(data1[i], data2[i])); //Defines fill
        }

        LineDataSet dataSet = new LineDataSet(entries, "O2 Saturation"); //Sets as a line and adds entries to the dataset
        dataSet.setColor(Color.DKGRAY);

        LineData lineData = new LineData(dataSet); //Setting the data
        mChart.setData(lineData); //Drawing the line
        mChart.invalidate(); //Refreshing

        XAxis xAxis1 = mChart.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Line Chart 2

        mChart = (LineChart) findViewById(R.id.linechart3); //Defines linechart in xml

        int[] data3 = {0,5,10,15,20,25,30,35,40,45}; //X data values
        int[] data4 = {61,62,63,61,63,62,61,63,63,61}; //Y data values

        double sum2 = 0;
        for(int i=0; i < data4.length ; i++)
            sum2 = sum2 + data4[i];
        TextView average2 = (TextView)findViewById(R.id.average2);
        double result2 = sum2 / data4.length;
        average2.setText("Running Average: " + result2);

       List<Entry> entries2 = new ArrayList<>(); //Container for X and Y entries

        for (int i = 0; i < data3.length; i++) { //For loop that puts data in the entry

            entries2.add(new Entry(data3[i], data4[i])); //Defines fill
        }

       LineDataSet dataSet2 = new LineDataSet(entries2, "Pulse Rate"); //Sets as a line and adds entries to the dataset
       dataSet2.setColor(Color.DKGRAY);

        LineData lineData2 = new LineData(dataSet2); //Setting data
        mChart.setData(lineData2); //Drawing the line
        mChart.invalidate(); //Refreshing

        XAxis xAxis2 = mChart.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);

        }
}