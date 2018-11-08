package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment06;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class WenDuFragment extends Fragment {

    private LineChart lineChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wendu, container, false);
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

        lineChart = view.findViewById(R.id.linechart);

        initLineChartData();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis lyAxis = lineChart.getAxisLeft();
        lyAxis.setDrawAxisLine(false);
        lyAxis.setStartAtZero(true);

        YAxis ryAxis = lineChart.getAxisRight();
        ryAxis.setEnabled(false);

    }

    private void initLineChartData() {

        List<String> xValues = new ArrayList<>();
        List<Entry> yValues = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Integer t = new Random().nextInt(100);
            yValues.add(new Entry(t, i));
            xValues.add(i + "");
        }

        LineDataSet lineDataSet = new LineDataSet(yValues, "温度");

        LineData lineData = new LineData(xValues);

        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);
    }

}
