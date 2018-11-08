package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class AirFragment extends Fragment {

    private BarChart barChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_air, container, false);
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

        YAxis lyAxis = barChart.getAxisLeft();
        lyAxis.setDrawAxisLine(false);
        lyAxis.setStartAtZero(true);

        YAxis ryAxis = barChart.getAxisRight();
        ryAxis.setEnabled(false);

    }

    private void initBarChartData() {

        List<String> xValues = new ArrayList<>();
        List<BarEntry> yValues = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Integer t = new Random().nextInt(100);
            yValues.add(new BarEntry(t, i));
            xValues.add(i + "");
        }

        BarDataSet barDataSet = new BarDataSet(yValues, "空气质量");

        BarData barData = new BarData(xValues);

        barData.addDataSet(barDataSet);
        barChart.setData(barData);
    }

}
