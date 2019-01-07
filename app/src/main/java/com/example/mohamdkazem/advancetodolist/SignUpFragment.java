package com.example.mohamdkazem.advancetodolist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    private EditText mEditTextName,mEditTextEmail,mEditTextPass;
    private Button btnSingUp;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();
        
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        btnSingUp=view.findViewById(R.id.sing_up);
        mEditTextName=view.findViewById(R.id.editTextUserName);
        mEditTextPass=view.findViewById(R.id.editTextPassword);
        mEditTextEmail=view.findViewById(R.id.editTextEmail);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });


        return  view;
    }

    private void validation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String userName=mEditTextName.getText().toString().trim();
        String passWord=mEditTextPass.getText().toString().trim();
        String email=mEditTextEmail.getText().toString().trim();

        if (userName.length()==0){
            Toast.makeText(getActivity(),"Wrong Name",Toast.LENGTH_SHORT).show();
        }
        if (passWord.length()==0){
            Toast.makeText(getActivity(),"Wrong  PassWord",Toast.LENGTH_SHORT).show();
        }
        if (!email.matches(emailPattern)){
            Toast.makeText(getActivity(),"Wrong Email",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(),"Your UserName :"+ userName +"\n"+ "Your PassWord : "+ passWord,Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().getFragments().get(0).onActivityResult(3,Activity.RESULT_OK,new Intent());
            getFragmentManager().beginTransaction().replace(R.id.login_activity,WellcomeFragment.newInstance()).commit();
        }
    }

}
