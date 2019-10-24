package com.toru.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPageAdapter extends FragmentPagerAdapter {
     private List<Fragment> fragments = new ArrayList<>();
     private  List<String> title = new ArrayList<>();

    public viewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
    public  void addFregmet(Fragment fragment ,String title)
    {
        this.fragments.add(fragment);
        this.title.add(title);
    }
}
