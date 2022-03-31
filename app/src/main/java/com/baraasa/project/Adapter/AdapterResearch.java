package com.baraasa.project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.baraasa.project.API.Server;
import com.baraasa.project.Model.DataResearch;
import com.baraasa.project.Jurnal;
import com.baraasa.project.Model.ResearchModel;
import com.baraasa.project.R;

import java.util.ArrayList;

public class AdapterResearch extends RecyclerView.Adapter<AdapterResearch.ViewHolder> {

    private ArrayList<ResearchModel> researchModels;
    Context context;
    DataResearch dataResearch;

    public AdapterResearch(ArrayList<ResearchModel> researchModels, Context context) {
        this.researchModels = researchModels;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_research, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.research_judul.setText(researchModels.get(position).getTitle());
        holder.research_date.setText("Terbit Pada "+researchModels.get(position).getDate_of_publish());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Jurnal.class);
                intent.putExtra("judul",researchModels.get(position).getTitle());
                intent.putExtra("file", Server.URL_PDF+researchModels.get(position).getFile());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount()
    {
        return researchModels == null ? 0 : researchModels.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView research_judul, research_date,research_penulis;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            research_judul = itemView.findViewById(R.id.research_judul);
            research_date = itemView.findViewById(R.id.research_date);
            cardView = itemView.findViewById(R.id.cv_research);
        }
    }
}
