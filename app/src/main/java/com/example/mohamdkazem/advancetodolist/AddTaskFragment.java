package com.example.mohamdkazem.advancetodolist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohamdkazem.advancetodolist.Model.Task;
import com.example.mohamdkazem.advancetodolist.Model.TasksRepository;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {
    private EditText mEditTextJobTitle, mEditTextDetail;
    private List<Task> mTasks;
    private Task mTask;


    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance() {
        Bundle args = new Bundle();
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTasks = TasksRepository.getInstance(getActivity()).getTaskList();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_task, container, false);
        Button buttonAdd = view.findViewById(R.id.btnAdd);
        mEditTextJobTitle=view.findViewById(R.id.editTextTitle);
        mEditTextDetail =view.findViewById(R.id.editTextDetail);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextJobTitle.getText().length()==0){
                    Toast.makeText(getActivity(),getString(R.string.musttAddTitle),Toast.LENGTH_SHORT).show();
                }else
                {
                    String title=mEditTextJobTitle.getText().toString();
                    String detail= mEditTextDetail.getText().toString();
                    mTask =new Task(title,detail);
                    TasksRepository.getInstance(getActivity()).addToAllList(mTask);

                    getActivity().getSupportFragmentManager().getFragments().get(1).onActivityResult(1, Activity.RESULT_OK,new Intent());
                    getActivity().getSupportFragmentManager().getFragments().get(0).onActivityResult(0,Activity.RESULT_OK,new Intent());

                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        return view;
    }

}
