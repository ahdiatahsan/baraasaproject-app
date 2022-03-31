package com.baraasa.project.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baraasa.project.ViewPager.AddBlog;
import com.baraasa.project.ViewPager.AddThread;

public class ViewPagerAdapterPost extends FragmentPagerAdapter {

    public ViewPagerAdapterPost(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0){
            fragment = new AddBlog();
        } else if (position == 1){
            fragment = new AddThread();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == 0){
            title = "Add Blog";
        } else if (position == 1){
            title = "Add Thread";
        }
        return title;
    }

}
