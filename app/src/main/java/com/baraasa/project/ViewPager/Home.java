package com.baraasa.project.ViewPager;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.R;


public class Home extends Fragment {

    CardView IG, SP;
    TextView Nama;
    LocalStorage localStorage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IG = view.findViewById(R.id.ig);
        SP = view.findViewById(R.id.sp);
        Nama = view.findViewById(R.id.namauser);
        localStorage = new LocalStorage(getActivity());
        String user = localStorage.getStringValue(LocalStorage.NAME);
        Nama.setText(user+",");
        IG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://instagram.com/baraasa.project?utm_medium=copy_link"));
                startActivity(intent);
            }
        });
        SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://open.spotify.com/show/7ill8y7DsenVbgk41o6Oi5?si=6PL8nqxKSqigZXhXHSQeeQ&utm_source=whatsapp&nd=1"));
                    startActivity(intent);

            }
        });
    }
}