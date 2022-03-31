
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
import com.baraasa.project.Event.Event;
import com.baraasa.project.Model.DataEvent;
import com.bumptech.glide.Glide;
import com.baraasa.project.Model.EventModel;
import com.baraasa.project.R;

import java.util.ArrayList;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ViewHolder> {

    private ArrayList<EventModel> eventModels;
    Context context;
    DataEvent dataEvent;

    public AdapterEvent(ArrayList<EventModel> eventModels, Context context) {
        this.eventModels = eventModels;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.event_lokasi.setText(eventModels.get(position).getLocation());
        holder.event_judul.setText(eventModels.get(position).getTitle());
        holder.event_date.setText(eventModels.get(position).getDate_start()+" - "+eventModels.get(position).getDate_end());
        String img1 = Server.URL_GAMBAREVENT +eventModels.get(position).getThumbnail();
        Glide.with(context)
                .load(img1)
                .into(holder.event_cover_img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Event.class);
                intent.putExtra("id", eventModels.get(position).getId());
                intent.putExtra("judul", eventModels.get(position).getTitle());
                intent.putExtra("status", eventModels.get(position).getEvent_status());
//                intent.putExtra("tgl", materiList.get(position).getDate());
//                intent.putExtra("gambar", materiList.get(position).getLogo());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels == null ? 0 : eventModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView event_cover_img;
        TextView event_judul, event_date, event_lokasi;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event_lokasi = itemView.findViewById(R.id.event_lokasi);
            event_judul = itemView.findViewById(R.id.event_judul);
            event_date = itemView.findViewById(R.id.event_date);
            event_cover_img = itemView.findViewById(R.id.event_cover_img);
            cardView = itemView.findViewById(R.id.cv_event);
        }
    }

//    private Cursor getCursor(Cursor cursor) {
//        if (Mcursor == cursor) {
//            return null;
//        }
//        Cursor oldCursor = Mcursor;
//        this.Mcursor = cursor;
//        if (cursor != null) {
//            this.notifyDataSetChanged();
//        }
//        return oldCursor;
//    }
//
//    private void changeCursor(Cursor cursor) {
//        Cursor oldCursor = getCursor(cursor);
//        if (oldCursor != null) {
//            oldCursor.close();
//        }
//    }
//
//    private Bitmap bitmap(int position) {
//        int idIndex = Mcursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
//        int mediaTypeIndex = Mcursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE);
//        Mcursor.moveToPosition(position);
//        switch (Mcursor.getInt(mediaTypeIndex)) {
//            case MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE:
//                return MediaStore.Images.Thumbnails.getThumbnail(
//                        activity.getContentResolver(), Mcursor.getLong(idIndex),
//                        MediaStore.Images.Thumbnails.MICRO_KIND,
//                        null
//                );
//            case MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO:
//                return MediaStore.Video.Thumbnails.getThumbnail(
//                        activity.getContentResolver(), Mcursor.getLong(idIndex),
//                        MediaStore.Video.Thumbnails.MICRO_KIND,
//                        null
//                );
//            default:
//                return null;
//
//        }
//
//    }


}
