package com.example.mohamdkazem.advancetodolist;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ToDoListActivity extends AppCompatActivity {
    public TabLayout mTabLayout;
    private  ViewPager mViewPager;

    private String[] mTabTitles={"همه","انجام شده"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        init();


        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if (i % 2 == 0) {
                    return AllTasksFragment.newInstance();
                } else return DoneTasksFragment.newInstance();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitles[position];
            }

            @Override
            public int getCount() {
                return mTabTitles.length;
            }

        });
    }
    private void init() {
        mTabLayout=findViewById(R.id.tab);
        mViewPager=findViewById(R.id.viewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
