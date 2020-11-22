package com.styephenwilliambuli.ugd10_e_9590;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.styephenwilliambuli.ugd10_e_9590.databse.DatabaseClient;
import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;

public class AddMahasiswaActivity extends AppCompatActivity {

    private EditText etNpm, etNama, etIpk, etUrlPhotos;
    private AutoCompleteTextView exposedDropdownFakultas, exposedDropdownJurusan;
    private String sJurusan = "", sFakultas = "";
    private final String[] saJurusan = new String[] {"Informatika", "Teknik Industri", "Sistem Informasi",
            "Manajemen", "Akuntansi", "Ekonomi Pembangunan", "Ilmu Komunikasi", "Sosiologi",
            "Ilmu Hukum", "Biologi", "Arsitektur", "Teknik Sipil"};
    private final String[] saFakultas = new String[] {"FTI", "FBE" , "FISIP", "FH", "FTB", "FT"};
    private ProgressDialog progressDialog;
    private Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        progressDialog = new ProgressDialog(this);

        etNpm = findViewById(R.id.etNpm);
        etNama = findViewById(R.id.etNama);
        exposedDropdownFakultas = findViewById(R.id.edFakultas);
        exposedDropdownJurusan = findViewById(R.id.edJurusan);
        etIpk = findViewById(R.id.etIpk);
        etUrlPhotos = findViewById(R.id.etUrlPhotos);
        MaterialButton btnadd = findViewById(R.id.btnadd);

        setArrayAdapter();

        btnadd.setOnClickListener(v -> {
            if(etNpm.getText().toString().isEmpty() ||
                    etNpm.getText().length() != 9){
                etNpm.setError("Isikan Dengan Benar");
                etNpm.requestFocus();
            }
            else if(etNama.getText().toString().isEmpty()){
                etNama.setError("Isikan Dengan Benar");
                etNama.requestFocus();
            }
            else if(sFakultas.isEmpty()){
                exposedDropdownFakultas.setError("Isikan Dengan Benar", null);
                exposedDropdownFakultas.requestFocus();
            }
            else if(sJurusan.isEmpty()){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(sFakultas.equals("FTI") && (!sJurusan.equals("Informatika")
                    && !sJurusan.equals("Teknik Industri") && !sJurusan.equals("Sistem Informasi"))){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(sFakultas.equals("FBE") && (!sJurusan.equals("Manajemen")
                    && !sJurusan.equals("Akuntansi") && !sJurusan.equals("Ekonomi Pembangunan"))){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(sFakultas.equals("FISIP") && (!sJurusan.equals("Ilmu Komunikasi")
                    && !sJurusan.equals("Sosiologi"))){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(sFakultas.equals("FH") && !sJurusan.equals("Ilmu Hukum")){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(sFakultas.equals("FTB") && !sJurusan.equals("Biologi")){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(sFakultas.equals("FT") && (!sJurusan.equals("Arsitektur")
                    && !sJurusan.equals("Teknik Sipil"))){
                exposedDropdownJurusan.setError("Isikan Dengan Benar", null);
                exposedDropdownJurusan.requestFocus();
            }
            else if(etIpk.getText().toString().isEmpty() ||
                    Double.parseDouble(etIpk.getText().toString()) > 4.0 ||
                    Double.parseDouble(etIpk.getText().toString()) < 0.0){
                etIpk.setError("Isikan Dengan Benar");
                etIpk.requestFocus();
            }
            else if(etUrlPhotos.getText().toString().isEmpty()){
                etUrlPhotos.setError("Isikan Dengan Benar");
                etUrlPhotos.requestFocus();
            }
            else{
                progressDialog.show();
                saveMahasiswa();
            }
        });
    }

    private void setArrayAdapter() {
        ArrayAdapter<String> adapterFakultas = new ArrayAdapter<>(this,
                R.layout.list_item, R.id.item_list, saFakultas);
        exposedDropdownFakultas.setAdapter(adapterFakultas);
        exposedDropdownFakultas
                .setOnItemClickListener((parent, view, position, id)
                        -> sFakultas = saFakultas[position]);

        ArrayAdapter<String> adapterProdi = new ArrayAdapter<>(this,
                R.layout.list_item, R.id.item_list, saJurusan);
        exposedDropdownJurusan.setAdapter(adapterProdi);
        exposedDropdownJurusan
                .setOnItemClickListener((parent, view, position, id)
                        -> sJurusan = saJurusan[position]);
    }

    private void saveMahasiswa(){
        class AddUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                mahasiswa = new Mahasiswa();
                mahasiswa.setNpm(etNpm.getText().toString());
                mahasiswa.setNama(etNama.getText().toString());
                mahasiswa.setFakultas(sFakultas);
                mahasiswa.setJurusan(sJurusan);
                mahasiswa.setIpk(Double.parseDouble(etIpk.getText().toString()));
                mahasiswa.setUrlPhotos(etUrlPhotos.getText().toString());

                DatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .mahasiswaDAO()
                        .insert(mahasiswa);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "User saved", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }
        }

        AddUser add = new AddUser();
        add.execute();
    }
}