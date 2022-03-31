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

import com.bumptech.glide.Glide;
import com.baraasa.project.API.Server;
import com.baraasa.project.Forum.Forum_Detail_Saya;
import com.baraasa.project.Model.DataThread;
import com.baraasa.project.Model.ThreadModel;
import com.baraasa.project.R;

import java.util.ArrayList;

public class AdapterForumDetailSaya extends RecyclerView.Adapter<AdapterForumDetailSaya.ViewHolder> {

    private ArrayList<ThreadModel> threadModels;
    Context context;
    DataThread dataThread;

    public AdapterForumDetailSaya(ArrayList<ThreadModel> threadModels, Context context) {
        this.threadModels = threadModels;
        this.context = context;
        notifyDataSetChanged();
        notifyItemInserted(threadModels.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_forum, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.forum_penulis.setText(threadModels.get(position).getUser().getName());
        holder.forum_petanyaan.setText(threadModels.get(position).getTitle());
        holder.forum_date.setText(threadModels.get(position).getCreated_at());
        String img = Server.URL_USER +threadModels.get(position).getUser().getPhoto();
        Glide.with(context)
                .load(img)
                .into(holder.forum_cover_img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Forum_Detail_Saya.class);
                intent.putExtra("id",threadModels.get(position).getId());
                intent.putExtra("nama",threadModels.get(position).getUser().getName());
                intent.putExtra("pertanyaan",threadModels.get(position).getTitle());
                intent.putExtra("deskripsi",threadModels.get(position).getBody());
                intent.putExtra("tgl",threadModels.get(position).getCreated_at());
                intent.putExtra("gambar",Server.URL_USER +threadModels.get(position).getUser().getPhoto());
                view.getContext().startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return threadModels == null ? 0 : threadModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView forum_cover_img;
        TextView forum_petanyaan, forum_date,forum_penulis;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            forum_penulis = itemView.findViewById(R.id.forum_penulis);
            forum_petanyaan = itemView.findViewById(R.id.forum_pertanyaan);
            forum_date = itemView.findViewById(R.id.forum_date);
            forum_cover_img = itemView.findViewById(R.id.forum_cover_img);
            cardView = itemView.findViewById(R.id.cv_forum);
        }
    }

//    public Filter getFilter() {
//        return new Filter() {
//
//            protected FilterResults performFiltering(CharSequence constraint) {
//
//                String searchString = constraint.toString();
//                ArrayList<Materi1> filteredList = new ArrayList<>();
//                for (Materi1 model : materiList1) {
//                    if (model.getJudul1().contains(searchString)) {
//                        filteredList.add(model);
//                    } else if (model.getJudul1().toLowerCase().contains(searchString)) {
//                        filteredList.add(model);
//                    } else if (model.getJudul1().toUpperCase().contains(searchString)) {
//                        filteredList.add(model);
//                    } else if (model.getJudul1().contains(searchString)) {
//                        filteredList.add(model);
//                    }
//
//                }
//                materiList1 = filteredList;
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = materiList1;
//
//                return filterResults;
//            }
//
//
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                if (results.count == 0) {
//                    materiList1 = (ArrayList<Materi1>) results.values;
//                    notifyDataSetChanged();
//                }
//            }
//        };
//    }


}
