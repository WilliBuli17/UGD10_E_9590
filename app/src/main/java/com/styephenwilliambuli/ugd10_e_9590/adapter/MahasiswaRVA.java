package com.styephenwilliambuli.ugd10_e_9590.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.styephenwilliambuli.ugd10_e_9590.R;
import com.styephenwilliambuli.ugd10_e_9590.model.Mahasiswa;

import java.util.List;

public class MahasiswaRVA extends RecyclerView.Adapter<MahasiswaRVA.MahasiswaViewHolder>{

    private final Context context;
    private final List<Mahasiswa> mahasiswaList;

    public MahasiswaRVA(Context context, List<Mahasiswa> mahasiswaList){
        this.context=context;
        this.mahasiswaList=mahasiswaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList.get(position);
        holder.txtNpm.setText(mahasiswa.getNpm());
        holder.txtNama.setText(mahasiswa.getNama());
        holder.txtFakultas.setText(mahasiswa.getFakultas());
        holder.txtJurusan.setText(mahasiswa.getJurusan());
        holder.txtIpk.setText(mahasiswa.getStringIpk());
        Glide.with(context)
                .load(mahasiswa.getUrlPhotos())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(holder.ivGambar);
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView txtNpm;
        private final TextView txtNama;
        private final TextView txtFakultas;
        private final TextView txtJurusan;
        private final TextView txtIpk;
        private final ImageView ivGambar;

        public MahasiswaViewHolder(@NonNull View itemView){
            super(itemView);
            txtNpm          = itemView.findViewById(R.id.txtNpm);
            txtNama         = itemView.findViewById(R.id.txtNama);
            txtFakultas     = itemView.findViewById(R.id.txtFakultas);
            txtJurusan      = itemView.findViewById(R.id.txtJurusan);
            txtIpk          = itemView.findViewById(R.id.txtIpk);
            ivGambar        = itemView.findViewById(R.id.ivGambar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Sorry Fungsi DELETE Dimatiin", Toast.LENGTH_SHORT).show();

            /*Mahasiswa employee = mahasiswaList.get((getAdapterPosition()));

            class DeleteUser extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {

                    DatabaseClient.getInstance(context).getDatabase()
                            .mahasiswaDAO()
                            .delete(employee);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(context, "User deleted", Toast.LENGTH_SHORT).show();
                }
            }

            DeleteUser delete = new DeleteUser();
            delete.execute();*/
        }
    }
}
