package cn.com.edu.aib.trafficclient_2017gb.zqx.Fragment07;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.edu.aib.trafficclient_2017gb.R;

public class FragmentItem2 extends Fragment {

    private HorizontalBarChart barChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment07_item_2, container, false);
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

        xValues.add("1——2条违章");
        xValues.add("3——5条违章");
        xValues.add("5条以上违章");

        List<BarEntry> yValues = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Integer t = new Random().nextInt(100);
            yValues.add(new BarEntry(t, i));
        }

        BarDataSet barDataSet = new BarDataSet(yValues, "违章车辆的违章次数占比分布图统计");
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return v + "%";
            }
        });

        int[] colors = new int[]{Color.GREEN, Color.BLUE, Color.RED};
        barDataSet.setColors(colors);

        BarData barData = new BarData(xValues);

        barData.addDataSet(barDataSet);
        barChart.setData(barData);
    }


}
