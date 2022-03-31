package com.baraasa.project.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baraasa.project.ViewPager.BlogSearch;
import com.baraasa.project.ViewPager.EventSearch;
import com.baraasa.project.ViewPager.ForumSearch;
import com.baraasa.project.ViewPager.Home;
import com.baraasa.project.ViewPager.JurnalSearch;
import com.baraasa.project.ViewPager.Recruitment;

public class ViewPagerAdapterHome extends FragmentPagerAdapter {

    public ViewPagerAdapterHome(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new Home();
        } else if (position == 1) {
            fragment = new BlogSearch();
        } else if (position == 2) {
            fragment = new ForumSearch();
        } else if (position == 3) {
            fragment = new EventSearch();
        } else if (position == 4) {
            fragment = new JurnalSearch();
        } else if (position == 5) {
            fragment = new Recruitment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Beranda";
        } else if (position == 1) {
            title = "Blog";
        } else if (position == 2) {
            title = "Forum";
        } else if (position == 3) {
            title = "Acara";
        } else if (position == 4) {
            title = "Jurnal";
        } else if (position == 5) {
            title = "Perekrutan";
        }
        return title;
    }
}
