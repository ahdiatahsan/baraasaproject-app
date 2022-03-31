package com.baraasa.project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.baraasa.project.API.Server;
import com.baraasa.project.Blog.Blog_Detail;
import com.baraasa.project.Model.DataBlog;
import com.baraasa.project.R;
import com.bumptech.glide.Glide;
import com.baraasa.project.Model.BlogModel;

import java.util.ArrayList;

public class AdapterBlog extends RecyclerView.Adapter<AdapterBlog.ViewHolder> {

    private ArrayList<BlogModel> blogModels;
    private ArrayList<BlogModel> liat;
    Context context;
    DataBlog dataBlog;



    public AdapterBlog(ArrayList<BlogModel> blogModels, Context context) {
        this.blogModels = blogModels;
        this.context = context;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_blog, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.blog_penulis.setText(blogModels.get(position).getUser().getName());
        holder.blog_judul.setText(blogModels.get(position).getTitle());
        holder.blog_date.setText(blogModels.get(position).getCreated_at());
        String img = Server.URL_GAMBARBLOG +blogModels.get(position).getThumbnail();
        Glide.with(context).load(img).into(holder.blog_cover_img);
        String img1 = Server.URL_USER+blogModels.get(position).getUser().getPhoto();
        Glide.with(context).load(img1).into(holder.blog_cover_image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Blog_Detail.class)
                        .putExtra("id", blogModels.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {

        return blogModels == null ? 0 : blogModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView blog_cover_img, blog_cover_image;
        TextView blog_judul, blog_date, blog_penulis;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blog_penulis = itemView.findViewById(R.id.blog_penulis);
            blog_judul = itemView.findViewById(R.id.blog_judul);
            blog_date = itemView.findViewById(R.id.blog_date);
            blog_cover_img = itemView.findViewById(R.id.blog_cover_img);
            blog_cover_image = itemView.findViewById(R.id.blog_cover_image);
            cardView = itemView.findViewById(R.id.cv_blog);
        }
    }

    public Filter getFilter() {
        return new Filter() {

            protected FilterResults performFiltering(CharSequence constraint) {

                String searchString = constraint.toString();
                if (searchString.isEmpty()) {
                    blogModels = liat;
                } else {
                    ArrayList<BlogModel> filteredList = new ArrayList<>();
                    for (BlogModel model : blogModels) {
                        if (model.getTitle().contains(searchString)) {
                            filteredList.add(model);
                        } else if (model.getTitle().toLowerCase().contains(searchString)) {
                            filteredList.add(model);
                        } else if (model.getTitle().toUpperCase().contains(searchString)) {
                            filteredList.add(model);
                        } else if (model.getTitle().contains(searchString)) {
                            filteredList.add(model);
                        } else if (model.getTitle().toLowerCase().contains(searchString)) {
                            filteredList.add(model);
                        } else if (model.getTitle().toUpperCase().contains(searchString)) {
                            filteredList.add(model);
                        }
                    }
                    blogModels = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = blogModels;

                return filterResults;
            }


            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) {
                    blogModels = (ArrayList<BlogModel>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }
}
