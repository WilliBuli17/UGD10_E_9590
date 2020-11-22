package com.styephenwilliambuli.ugd10_e_9590.chart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.styephenwilliambuli.ugd10_e_9590.R;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        FragmentManager manager = getSupportFragmentManager();

        FirsChartFragment firsChartFragment = new FirsChartFragment();
        manager.beginTransaction()
                .replace(R.id.relativeLayout, firsChartFragment, firsChartFragment.getTag())
                .commit();

        SecondChartFragment secondChartFragment = new SecondChartFragment();
        manager.beginTransaction()
                .replace(R.id.relativeLayout2, secondChartFragment, secondChartFragment.getTag())
                .commit();
    }
}