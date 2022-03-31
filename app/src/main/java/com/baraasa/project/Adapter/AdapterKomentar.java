package com.baraasa.project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.baraasa.project.API.Client;
import com.baraasa.project.API.Server;
import com.baraasa.project.Model.DataHapusForum;
import com.baraasa.project.Model.KomentarModel;
import com.baraasa.project.Response.LocalStorage;
import com.bumptech.glide.Glide;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKomentar extends RecyclerView.Adapter<AdapterKomentar.ViewHolder> {

    private ArrayList<KomentarModel> komentarModels;
    Context context;
    LocalStorage localStorage;
    Login_response login_response;

    public AdapterKomentar(ArrayList<KomentarModel> komentarModels, Context context) {
        this.komentarModels = komentarModels;
        this.context = context;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_komentar, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.komentar_penulis.setText(komentarModels.get(position).getUser().getName());
        holder.komentar_petanyaan.setText(komentarModels.get(position).getBody());
        holder.komentar_date.setText(komentarModels.get(position).getCreated_at());
        String img1 = Server.URL_USER + komentarModels.get(position).getUser().getPhoto();
        Glide.with(context).load(img1).into(holder.komentar_cover_img);
        localStorage = new LocalStorage(context);
        login_response = new Login_response();
        if (komentarModels.get(position).getUser().getId().equals(localStorage.getStringValue(LocalStorage.ID))) {
            holder.hapus.setVisibility(View.VISIBLE);
            holder.hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                    Call<DataHapusForum> call = apiInterface.hapuscommentmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), komentarModels.get(position).getId());
                    call.enqueue(new Callback<DataHapusForum>() {
                        @Override
                        public void onResponse(Call<DataHapusForum> call, Response<DataHapusForum> response) {
                            if (response.isSuccessful()) {
                                DataHapusForum dataKomen = response.body();
                                Toast.makeText(context, dataKomen.getMessage(), Toast.LENGTH_SHORT).show();
                                int newPosition = holder.getAdapterPosition();
                                komentarModels.remove(newPosition);
                                notifyItemRemoved(newPosition);
                                notifyItemRangeChanged(newPosition, komentarModels.size());
                            }
                        }

                        @Override
                        public void onFailure(Call<DataHapusForum> call, Throwable t) {

                        }
                    });

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return komentarModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView komentar_cover_img;
        TextView komentar_petanyaan, komentar_date, komentar_penulis;
        CardView cardView;
        Button hapus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            komentar_penulis = itemView.findViewById(R.id.komentar_penulis);
            komentar_petanyaan = itemView.findViewById(R.id.komentar_pertanyaan);
            komentar_date = itemView.findViewById(R.id.komentar_date);
            komentar_cover_img = itemView.findViewById(R.id.komentar_cover_img);
            hapus = itemView.findViewById(R.id.hapuskomen);
            cardView = itemView.findViewById(R.id.cv_komentar);
        }
    }
}
