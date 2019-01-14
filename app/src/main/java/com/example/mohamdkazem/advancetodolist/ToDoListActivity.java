package com.example.mohamdkazem.advancetodolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class ToDoListActivity extends AppCompatActivity {
    private static final String USER_ID = "com.example.mohamdkazem.advancetodolist.userId";
    public TabLayout mTabLayout;
    private  ViewPager mViewPager;
    private String[] mTabTitles={"همه","انجام شده"};
    public static int mId;


    public static Intent newIntent(Context context,int userId){
        Intent intent=new Intent(context,ToDoListActivity.class);
        intent.putExtra(USER_ID,userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        mId= (int) getIntent().getExtras().get(USER_ID);
        String s= String.valueOf(mId);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
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
