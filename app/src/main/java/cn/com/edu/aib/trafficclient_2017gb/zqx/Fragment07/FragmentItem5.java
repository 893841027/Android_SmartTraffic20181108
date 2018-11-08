package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class FragmentItem5 extends Fragment {

    private BarChart barChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment07_item_5, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        View view = getView();
        if (view == null)
            return;

        barChart = view.findViewById(R.id.barchart);

        initBarChartData();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.setMaxVisibleValueCount(12);

        YAxis lyAxis = barChart.getAxisLeft();
        lyAxis.setDrawAxisLine(false);
        lyAxis.setStartAtZero(true);


        YAxis ryAxis = barChart.getAxisRight();
        ryAxis.setEnabled(false);

    }

    private void initBarChartData() {

        List<String> xValues = new ArrayList<>();
        List<BarEntry> yValues = new ArrayList<>();

        List<Integer> colors = new ArrayList<>();

        for (int i = 0; i < 24; i += 2) {
            Integer t = new Random().nextInt(100);

            yValues.add(new BarEntry(t, i));

            xValues.add(i + ":00:01-" + (i + 2) + ":00:00");

            Integer r = new Random().nextInt(255);
            Integer g = new Random().nextInt(255);
            Integer b = new Random().nextInt(255);

            colors.add(Color.rgb(r, g, b));
        }

        BarDataSet barDataSet = new BarDataSet(yValues, "有无车辆违章");

        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });


        barDataSet.setColors(colors);
        BarData barData = new BarData(xValues);
        barData.addDataSet(barDataSet);
        barChart.setData(barData);
    }

}
