package com.example.mohamdkazem.advancetodolist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohamdkazem.advancetodolist.Model.TasksRepository;
import com.example.mohamdkazem.advancetodolist.Model.Users;


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

        mBtnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String userName=mEditTextname.getText().toString().trim();
//                String passWord=mEditTextPassWord.getText().toString().trim();
//
//                Users users=TasksRepository.getInstance(getActivity()).getUser(userName,passWord);
//                if (!(users.getName().equals(userName) && users.getPassword().equals(passWord))){
//                    Toast.makeText(getActivity(),"Ok",Toast.LENGTH_SHORT).show();
//                }else Toast.makeText(getActivity(),"Nooo",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getActivity(),ToDoListActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
