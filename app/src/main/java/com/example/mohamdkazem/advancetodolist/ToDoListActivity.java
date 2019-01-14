package com.example.mohamdkazem.advancetodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mohamdkazem.advancetodolist.Model.Task;
import com.example.mohamdkazem.advancetodolist.Model.Users;

import java.io.Serializable;

public class ToDoListActivity extends AppCompatActivity {
    private static final String USER_TAG = "com.example.mohamdkazem.advancetodolist.user";
    public TabLayout mTabLayout;
    private  ViewPager mViewPager;
    private int mId;


    public static Intent newIntent(Context context, int id){
        Intent intent=new Intent(context,ToDoListActivity.class);
        intent.putExtra(USER_TAG, id);
        return intent;
    }

    private String[] mTabTitles={"همه","انجام شده"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        mId= (int) getIntent().getExtras().get(USER_TAG);
        String s= String.valueOf(mId);
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

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
