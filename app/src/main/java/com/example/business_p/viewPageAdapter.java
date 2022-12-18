package com.example.business_p;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.business_p.fragments.categories;
import com.example.business_p.fragments.messages;
import com.example.business_p.fragments.newsfeed;

public class viewPageAdapter extends FragmentStateAdapter {
    public viewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new categories();
            case 2:
                return new messages();
            case 0:
            default:
                return new newsfeed();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
