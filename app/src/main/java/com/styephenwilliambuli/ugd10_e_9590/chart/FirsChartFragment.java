package com.styephenwilliambuli.ugd10_e_9590.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pyramid;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.styephenwilliambuli.ugd10_e_9590.R;
import com.styephenwilliambuli.ugd10_e_9590.databse.AppDatabase;
import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;
import com.styephenwilliambuli.ugd10_e_9590.model.MhsPerFak;

import java.util.ArrayList;
import java.util.List;

public class FirsChartFragment extends Fragment {

    private final List<Mahasiswa> mahasiswaList = new ArrayList<> ();
    private final List<DataEntry> data = new ArrayList<>();
    private final List<MhsPerFak> dataSort = new ArrayList<>();
    private static int vFH, vFTB, vFT, vFTI, vFBE, vFISIP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chart_common, container, false);

        //mengambil data dari presistent room
        AppDatabase appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "mahasiswa")
                .allowMainThreadQueries().build();
        mahasiswaList.addAll(appDatabase.mahasiswaDAO().getAll());

        vFH = vFTB = vFT = vFTI = vFBE = vFISIP = 0;

        getMahasiswaData(); //mencari jumlah mahasiswa
        setValueJumlahMahasiswa(); //memasukkan data ke array untuk di sort
        dataSort.sort((o1, o2) -> Integer.compare(o2.getJumlah(), o1.getJumlah())); //sortData
        insertDataToDataEntry(); //memindahkan data ke array chart

        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.spin_kit));

        /*Pie pie = AnyChart.pie();

        pie.data(data);

        pie.title("Presentase Jumlah Mahasiswa UAJY");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Nama Fakultas")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);*/

        Pyramid pyramid = AnyChart.pyramid();
        pyramid.data(data);
        pyramid.title("Perbandingan Jumlah Mahasiswa UAJY Berdasarkan Fakultas");
        pyramid.legend().title().enabled(true);
        pyramid.legend().title()
                .text("Nama Fakultas")
                .padding(0d, 0d, 0d, 0d);
        pyramid.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        pyramid.labels()
                .position("outside-left")
                .format("{%X} : {%Value}");

        anyChartView.setChart(pyramid);
        return view;
    }

    //memindahkan data ke array chart
    private void insertDataToDataEntry() {
        for (int i = 0; i < dataSort.size(); i++){
            data.add(new ValueDataEntry(dataSort.get(i).getFakultas(), dataSort.get(i).getJumlah()));
        }
    }

    //fungsi untuk mencari jumlah mahasiswa (perulangan)
    private void getMahasiswaData() {
        for (int i = 0; i < mahasiswaList.size(); i++){
            setValueJmlhMhs(mahasiswaList.get(i).getFakultas());
        }
    }

    //fungsi untuk mencari jumlah mahasiswa
    private void setValueJmlhMhs(String s) {
        if (s.equals("FH")){ vFH++; }
        if (s.equals("FTB")){ vFTB++; }
        if (s.equals("FT")) { vFT++; }
        if (s.equals("FTI")){ vFTI++; }
        if (s.equals("FBE")){ vFBE++; }
        if (s.equals("FISIP")){ vFISIP++; }
    }

    //memasukkan data ke array untuk di sort
    private void setValueJumlahMahasiswa(){
        if (vFH    != 0){ dataSort.add(new MhsPerFak(vFH, "FH"));}
        if (vFTB   != 0){ dataSort.add(new MhsPerFak(vFTB, "FTB"));}
        if (vFT    != 0){ dataSort.add(new MhsPerFak(vFT, "FT"));}
        if (vFTI   != 0){ dataSort.add(new MhsPerFak(vFTI, "FTI"));}
        if (vFBE   != 0){ dataSort.add(new MhsPerFak(vFBE, "FBE"));}
        if (vFISIP != 0){ dataSort.add(new MhsPerFak(vFISIP, "FISIP"));}
    }
}