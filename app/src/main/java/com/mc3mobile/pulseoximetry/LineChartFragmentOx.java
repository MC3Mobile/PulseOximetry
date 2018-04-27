package com.mc3mobile.pulseoximetry;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartFragmentOx extends Fragment {

    private static final String BUNDLE_TIME_OX = "bundle_time_ox";
    private static final String BUNDLE_OX = "bundle_ox";

    LineChart lineChartOx;

    public static LineChartFragmentOx newInstance(ArrayList<Integer> timeox, ArrayList<Integer> o2sat) {

        Bundle bundle = new Bundle();

        bundle.putIntegerArrayList(BUNDLE_TIME_OX, timeox);
        bundle.putIntegerArrayList(BUNDLE_OX, o2sat);

        LineChartFragmentOx fragment = new LineChartFragmentOx();
        fragment.setArguments(bundle);

        return fragment;
    }

    public LineChartFragmentOx() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_chartox, container, false);

        lineChartOx = (LineChart) view.findViewById(R.id.line_chartOx);

        XAxis xAxis = lineChartOx.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //Set x axis only to appear at bottom

        YAxis yAxisRight = lineChartOx.getAxisRight(); //Get right instance of y axis
        yAxisRight.setEnabled(false); //Set to disappear

        YAxis yAxisLeft = lineChartOx.getAxisLeft(); //Get left instance of y axis
        yAxisLeft.setAxisMaximum(111); //Set max range
        yAxisLeft.setAxisMinimum(90); //Set min range

        ArrayList<Integer> time = getArguments().getIntegerArrayList(BUNDLE_TIME_OX);
        ArrayList<Integer> o2sat = getArguments().getIntegerArrayList(BUNDLE_OX);

        xAxis.setAxisMaximum(time.get(time.size()-1)+1); //Set max range
        xAxis.setAxisMinimum(time.get(0)); //Set min range

        List<Entry> data = generateData(time, o2sat);

        LineDataSet dataSet = new LineDataSet(data, "O2 Saturation");
        dataSet.setColor(Color.RED); //Line color


        LineData lineData = new LineData(dataSet);
        lineChartOx.setData(lineData);
        lineChartOx.invalidate();

        return view;
    }

    private List generateData(ArrayList<Integer> timeox, ArrayList<Integer> o2sat){

        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < timeox.size(); i++){
            entries.add(new Entry(timeox.get(i), o2sat.get(i)));
        }

        return entries;
    }

}
