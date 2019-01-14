package com.example.mohamdkazem.advancetodolist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG_WELLCOME="com.example.mohamdkazem.advancetodolist.wellcome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.login_activity,WellcomeFragment.newInstance(),TAG_WELLCOME).commit();
    }

}
