package com.baraasa.project.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baraasa.project.ViewPager.Blog;
import com.baraasa.project.ViewPager.Event;
import com.baraasa.project.ViewPager.Forum;

public class ViewPagerAdapterProfile extends FragmentPagerAdapter {

    public ViewPagerAdapterProfile(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0){
            fragment = new Blog();
        } else if (position == 1){
            fragment = new Forum();
        } else if (position == 2){
            fragment = new Event();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == 0){
            title = "Blog";
        } else if (position == 1){
            title = "Thread";
        } else if (position == 2){
            title = "Certificate";
        }
        return title;
    }

}
