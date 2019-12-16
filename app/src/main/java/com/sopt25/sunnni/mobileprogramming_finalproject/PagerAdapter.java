package com.sopt25.sunnni.mobileprogramming_finalproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;
    public ArrayList<Fragment> fragmentLists = new ArrayList<Fragment>();

    public PagerAdapter(FragmentManager fm, int _numTabs) {
        super(fm);
        this.numTabs = _numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        /*switch (position) {
            case 0:
                PedometerFragment homeFragment = new PedometerFragment();
                return homeFragment;
            case 1:
                RecordFragment recordFragment = new RecordFragment();
                return recordFragment;
            default:
                return null;
        }*/
        // acitivity에서 추가한 fragment 리턴
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    public void addFragment(Fragment fragment){
        fragmentLists.add(fragment);
    }
}
