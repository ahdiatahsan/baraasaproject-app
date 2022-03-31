package com.baraasa.project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.baraasa.project.API.Server;
import com.baraasa.project.Model.CertificateModel;
import com.baraasa.project.Model.DataEvent;
import com.baraasa.project.Sertifikat.Sertifikat_Detail;
import com.bumptech.glide.Glide;
import com.baraasa.project.R;

import java.util.ArrayList;

public class AdapterSertifikat extends RecyclerView.Adapter<AdapterSertifikat.ViewHolder> {

    private ArrayList<CertificateModel> certificateModels;
    Context context;
    DataEvent dataEvent;

    public AdapterSertifikat(ArrayList<CertificateModel> certificateModels, Context context) {
        this.certificateModels = certificateModels;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_sertifikat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.sertifikat_judul.setText(certificateModels.get(position).getEvent().getTitle());
        holder.sertifikat_date.setText("Tahun "+certificateModels.get(position).getEvent().getDate_start().substring(0,4));
        String img1 = Server.URL_GAMBARSERTIFIKAT+certificateModels.get(position).getCertificate();
        Glide.with(context)
                .load(img1)
                .into(holder.sertifikat_cover_img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Sertifikat_Detail.class);
                intent.putExtra("judul", certificateModels.get(position).getEvent().getTitle());
                intent.putExtra("gmbr", Server.URL_GAMBARSERTIFIKAT+certificateModels.get(position).getCertificate());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return certificateModels == null ? 0 : certificateModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView sertifikat_cover_img;
        TextView sertifikat_judul, sertifikat_date;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sertifikat_judul = itemView.findViewById(R.id.sertifikat_judul);
            sertifikat_date = itemView.findViewById(R.id.sertifikat_date);
            sertifikat_cover_img = itemView.findViewById(R.id.sertifikat_cover_img);
            cardView = itemView.findViewById(R.id.cv_sertifikat);
        }
    }
}
