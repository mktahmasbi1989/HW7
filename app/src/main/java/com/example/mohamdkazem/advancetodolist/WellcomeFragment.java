package com.example.mohamdkazem.advancetodolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class WellcomeFragment extends Fragment {
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
                getFragmentManager().beginTransaction().replace(R.id.login_activity,SignUpFragment.newInstance()).commit();
            }
        });

        return  view;
    }

}
