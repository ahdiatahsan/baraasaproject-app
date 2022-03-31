package com.baraasa.project.Menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baraasa.project.ViewPagerAdapter.ViewPagerAdapterPost;
import com.baraasa.project.R;
import com.google.android.material.tabs.TabLayout;


public class Post_Menu extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabhome;
    ViewPagerAdapterPost viewPagerAdapterPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbar = (Toolbar) view.findViewById(R.id.toolbarpost);
        viewPager = view.findViewById(R.id.viewPagerPost);
        tabhome = view.findViewById(R.id.tabpost);

        viewPagerAdapterPost = new ViewPagerAdapterPost(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapterPost);
        tabhome.setupWithViewPager(viewPager,true);
    }
}