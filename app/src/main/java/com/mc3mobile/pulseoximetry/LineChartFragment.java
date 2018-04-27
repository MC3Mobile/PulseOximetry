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

public class LineChartFragment extends Fragment {

    private static final String BUNDLE_TIME = "bundle_time";
    private static final String BUNDLE_HR = "bundle_hr";

    LineChart lineChart;

    public static LineChartFragment newInstance(ArrayList<Integer> time, ArrayList<Integer> hr) {

        Bundle bundle = new Bundle();

        bundle.putIntegerArrayList(BUNDLE_TIME, time);
        bundle.putIntegerArrayList(BUNDLE_HR, hr);

        LineChartFragment fragment = new LineChartFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public LineChartFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        lineChart = (LineChart) view.findViewById(R.id.line_chart);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //Set x axis only to appear at bottom

        YAxis yAxisRight = lineChart.getAxisRight(); //Get right instance of y axis
        yAxisRight.setEnabled(false); //Set to disappear

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Get left instance of y axis
        yAxisLeft.setAxisMaximum(120); //Set max range
        yAxisLeft.setAxisMinimum(70); //Set min range

        ArrayList<Integer> time = getArguments().getIntegerArrayList(BUNDLE_TIME);
        ArrayList<Integer> hr = getArguments().getIntegerArrayList(BUNDLE_HR);

        xAxis.setAxisMaximum(time.get(time.size()-1)+1); //Set max range
        xAxis.setAxisMinimum(time.get(0)); //Set min range

        List<Entry> data = generateData(time, hr);

        LineDataSet dataSet = new LineDataSet(data, "Pulse Rate");
        dataSet.setColor(Color.RED); //Line color


        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

        return view;
    }

    private List generateData(ArrayList<Integer> time, ArrayList<Integer> hr){

        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < time.size(); i++){
            entries.add(new Entry(time.get(i), hr.get(i)));
        }

        return entries;
    }

}
