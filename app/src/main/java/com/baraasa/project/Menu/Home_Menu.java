package com.baraasa.project.Menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.baraasa.project.Forum.Forum_Tambah;
import com.baraasa.project.ViewPagerAdapter.ViewPagerAdapterHome;
import com.baraasa.project.R;
import com.google.android.material.tabs.TabLayout;

public class Home_Menu extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabhome;
    ViewPagerAdapterHome viewPagerAdapterHome;
    MenuBuilder menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_menu, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbar = (Toolbar) view.findViewById(R.id.toolbarhome);
        toolbar.inflateMenu(R.menu.tambah);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.tambahblog) {
//                    startActivity(new Intent(getContext(), Blog_Tambah.class));
//                } else
//
                if (item.getItemId() == R.id.tambahforum) {
                    startActivity(new Intent(getContext(), Forum_Tambah.class));
                }
                return false;
            }
        });
        viewPager = view.findViewById(R.id.viewPagerHome);
        tabhome = view.findViewById(R.id.tabhome);

        viewPagerAdapterHome = new ViewPagerAdapterHome(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapterHome);
        tabhome.setupWithViewPager(viewPager, true);
    }
}