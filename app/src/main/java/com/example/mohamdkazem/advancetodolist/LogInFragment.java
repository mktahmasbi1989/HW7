package com.example.mohamdkazem.advancetodolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {
    private Button mBtnLogIn;
    private EditText mEditTextname,mEditTextPassWord;

    public static LogInFragment newInstance() {

        Bundle args = new Bundle();

        LogInFragment fragment = new LogInFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_log_in, container, false);
        mBtnLogIn=view.findViewById(R.id.sing_up);
        mEditTextname=view.findViewById(R.id.editName);
        mEditTextPassWord=view.findViewById(R.id.editpass);

        return view;
    }

}
