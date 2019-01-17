package com.example.mohamdkazem.advancetodolist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mohamdkazem.advancetodolist.Model.TasksRepository;
import com.example.mohamdkazem.advancetodolist.Model.Users;


/**
 * A simple {@link Fragment} subclass.
 */
public class WellcomeFragment extends Fragment {
    private static final String TAG_SIGN_UP = "com.example.mohamdkazem.advancetodolistsign_up";
    private Button btnSignIn,btnLogIn,btnSkip;

    public static WellcomeFragment newInstance() {

        Bundle args = new Bundle();
        
        WellcomeFragment fragment = new WellcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public WellcomeFragment() {
        // Required empty public constructor
    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_wellcome, container, false);
        btnSkip=view.findViewById(R.id.btn_skip);
        btnLogIn=view.findViewById(R.id.btn_log_ig);
        btnSignIn=view.findViewById(R.id.btn_sing_in);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.login_activity,SignUpFragment.newInstance(),TAG_SIGN_UP).commit();

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users=new Users("fakeName","fakePass","fakeEmail");
                TasksRepository.getInstance(getActivity()).addUsers(users);
                Users user = TasksRepository.getInstance(getActivity()).getUser("fakeName", "fakePass");
                Intent intent=new Intent(ToDoListActivity.newIntent(getActivity(),user.getUserId()));
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.login_activity,LogInFragment.newInstance()).commit();
            }
        });

        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==3 && resultCode==Activity.RESULT_OK){
            btnSignIn.setVisibility(View.INVISIBLE);
        }
    }
}
