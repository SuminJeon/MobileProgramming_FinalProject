package com.sopt25.sunnni.mobileprogramming_finalproject.feature.pedometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sopt25.sunnni.mobileprogramming_finalproject.PagerAdapter;
import com.sopt25.sunnni.mobileprogramming_finalproject.R;
import com.sopt25.sunnni.mobileprogramming_finalproject.feature.record.RecordFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private BottomNavigationView mBNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.ctn_fragment);
        mBNV = (BottomNavigationView) findViewById(R.id.nv_main);

        setViewPager(mViewPager);
        mBNV.setOnNavigationItemSelectedListener(this);

        onNavigationItemSelected(mBNV.getMenu().getItem(0));
        mBNV.getMenu().getItem(0).setChecked(true);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem menuItem = mBNV.getMenu().getItem(position);
                setPage(menuItem, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                setPage(menuItem, 0);
                break;
            case R.id.navigation_my_total:
                setPage(menuItem, 1);
                break;
        }

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setPage(MenuItem menuItem, int position){
        menuItem.setChecked(true);
        mViewPager.setCurrentItem(position);
    }

    private void setViewPager(ViewPager _viewPager){
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 2);
        pagerAdapter.addFragment(new PedometerFragment(this));
        pagerAdapter.addFragment(new RecordFragment(this));
        _viewPager.setAdapter(pagerAdapter);
    }
}
