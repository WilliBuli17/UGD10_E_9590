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
import com.anychart.chart.common.dataentry.CategoryValueDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.TagCloud;
import com.anychart.scales.OrdinalColor;
import com.styephenwilliambuli.ugd10_e_9590.R;
import com.styephenwilliambuli.ugd10_e_9590.databse.AppDatabase;
import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class SecondChartFragment extends Fragment {

    private final List<Mahasiswa> mahasiswaList = new ArrayList<> ();
    private final List<DataEntry> data = new ArrayList<>();
    private static int vHK, vBIO, vARSI, vSIPIL, vTF, vTI, vSI, vAKUN, vMENE, vEP, vILK0M, vSOSIO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chart_common, container, false);

        //mengambil data dari presistent room
        AppDatabase appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "mahasiswa")
                .allowMainThreadQueries().build();
        mahasiswaList.addAll(appDatabase.mahasiswaDAO().getAll());

        vHK = vBIO = vARSI = vSIPIL = vTF = vTI = vSI = vAKUN = vMENE = vEP = vILK0M = vSOSIO = 0;

        getMahasiswaData();//mencari jumlah mahasiswa tiap jurusan beserta ipknya
        setValueRataRataIpk(); //memindahkan data ke array chart

        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.spin_kit));

        OrdinalColor ordinalColor = OrdinalColor.instantiate();
        ordinalColor.colors(new String[] {
                "#26959f", "#f18126", "#3b8ad8", "#60727b", "#e24b26", "#cf40ff"
        });

        TagCloud tagCloud = AnyChart.tagCloud();
        tagCloud.title("Jurusan yang Paling Diminati di UAJY");
        tagCloud.colorScale(ordinalColor);
        tagCloud.angles(new Double[] {-90d, 0d, 90d});
        tagCloud.colorRange().enabled(true);
        tagCloud.colorRange().colorLineSize(15d);
        tagCloud.data(data);

        anyChartView.setChart(tagCloud);
        return view;
    }

    //mencari jumlah mahasiswa tiap jurusan beserta ipknya (perulangan)
    private void getMahasiswaData() {
        for (int i = 0; i < mahasiswaList.size(); i++){
            setValueIpkMhs(mahasiswaList.get(i).getJurusan());
        }
    }

    //mencari jumlah mahasiswa tiap jurusan beserta ipknya
    private void setValueIpkMhs(String s) {
        if (s.equals("Ilmu Hukum")          ){ vHK++;}
        if (s.equals("Biologi")             ){ vBIO++;}
        if (s.equals("Arsitektur")          ){ vARSI++;}
        if (s.equals("Teknik Sipil")        ){ vSIPIL++;}
        if (s.equals("Informatika")         ){ vTF++;}
        if (s.equals("Teknik Industri")     ){ vTI++;}
        if (s.equals("Sistem Informasi")    ){ vSI++;}
        if (s.equals("Akuntansi")           ){ vAKUN++;}
        if (s.equals("Manajemen")           ){ vMENE++;}
        if (s.equals("Ekonomi Pembangunan") ){ vEP++;}
        if (s.equals("Ilmu Komunikasi")     ){ vILK0M++;}
        if (s.equals("Sosiologi")           ){ vSOSIO++;}
    }

    //memindahkan data ke array chart
    private void setValueRataRataIpk() {
        if (vHK    != 0){ data.add(new CategoryValueDataEntry("Hukum", "FH", vHK)); }
        if (vBIO   != 0){ data.add(new CategoryValueDataEntry("Biologi", "FTB", vBIO)); }
        if (vARSI  != 0){ data.add(new CategoryValueDataEntry("Arsitektur", "FT", vARSI)); }
        if (vSIPIL != 0){ data.add(new CategoryValueDataEntry("Teknik Sipil", "FT", vSIPIL)); }
        if (vTF    != 0){ data.add(new CategoryValueDataEntry("Informatika", "FTI", vTF)); }
        if (vTI    != 0){ data.add(new CategoryValueDataEntry("Teknik Industri", "FTI", vTI)); }
        if (vSI    != 0){ data.add(new CategoryValueDataEntry("Sistem Informasi", "FTI", vSI)); }
        if (vAKUN  != 0){ data.add(new CategoryValueDataEntry("Akuntansi", "FBE", vAKUN)); }
        if (vMENE  != 0){ data.add(new CategoryValueDataEntry("Manajemen", "FBE", vMENE)); }
        if (vEP    != 0){ data.add(new CategoryValueDataEntry("Ekonomi Pembangunan", "FBE", vEP)); }
        if (vILK0M != 0){ data.add(new CategoryValueDataEntry("Ilmu Komunikasi", "FISIP", vILK0M)); }
        if (vSOSIO != 0){ data.add(new CategoryValueDataEntry("Sosiologi", "FISIP", vSOSIO)); }
    }
}