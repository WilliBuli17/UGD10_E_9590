package com.styephenwilliambuli.ugd10_e_9590;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.styephenwilliambuli.ugd10_e_9590.adapter.MahasiswaRVA;
import com.styephenwilliambuli.ugd10_e_9590.chart.ChartActivity;
import com.styephenwilliambuli.ugd10_e_9590.databse.DatabaseClient;
import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaRVA adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsers();
        setRecyclerView();
        setRefreshLayout();
        setBtnAdd();
        setBtnChart();
    }

    private void setBtnAdd(){
        FloatingActionButton btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddMahasiswaActivity.class);
            startActivity(i);
        });
    }

    private void setBtnChart(){
        FloatingActionButton btnChart = findViewById(R.id.btn_chart);
        btnChart.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ChartActivity.class);
            startActivity(i);
        });
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.mahasiswa_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setRefreshLayout(){
        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(() -> {
            getUsers();
            refreshLayout.setRefreshing(false);
        });
    }

    private void getUsers(){
        class GetUsers extends AsyncTask<Void, Void, List<Mahasiswa>> {
            @Override
            protected List<Mahasiswa> doInBackground(Void... voids) {
                List<Mahasiswa> mahasiswaList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .mahasiswaDAO()
                        .getAll();
                return mahasiswaList;
            }

            @Override
            protected void onPostExecute(List<Mahasiswa> mahasiswas) {
                super.onPostExecute(mahasiswas);
                adapter = new MahasiswaRVA(MainActivity.this, mahasiswas);
                recyclerView.setAdapter(adapter);
                if (mahasiswas.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }
        GetUsers get = new GetUsers();
        get.execute();
    }
}