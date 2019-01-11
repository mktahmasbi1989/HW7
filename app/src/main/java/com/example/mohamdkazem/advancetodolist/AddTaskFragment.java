package com.example.mohamdkazem.advancetodolist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamdkazem.advancetodolist.Model.Task;
import com.example.mohamdkazem.advancetodolist.Model.TasksRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends DialogFragment {
    private static final String TAG_DATE_PICKER ="com.example.mohamdkazem.advancetodolist.date_picker" ;
    private static final int REQ_ADD_TASK_DATE = 14;
    private static final int REQ_ADD_TASK_TIME = 15;
    private static final String TAG_TIME_PICKER ="com.example.mohamdkazem.advancetodolist.time_picker" ;
    private EditText mEditTextJobTitle, mEditTextDetail;
    private TextView mTextDate,mTextTime;
    private List<Task> mTasks;
    private Task mTask;
    private Date mDate;


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
        mDate=new Date();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_task, container, false);
        Button buttonAdd = view.findViewById(R.id.btnAdd);
        mEditTextJobTitle=view.findViewById(R.id.editTextTitle);
        mEditTextDetail =view.findViewById(R.id.editTextDetail);
        mTextDate=view.findViewById(R.id.text_date_add);
        mTextTime=view.findViewById(R.id.text_time_add);

//        set Date and Time To now

        setTextViewTime();

        setTextViewDate();

        mTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(mDate);
                datePickerDialog.setTargetFragment(AddTaskFragment.this,REQ_ADD_TASK_DATE);
                datePickerDialog.show(getFragmentManager(),TAG_DATE_PICKER);
            }
        });

        mTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment=TimePickerFragment.newInstance(mDate);
                timePickerFragment.setTargetFragment(AddTaskFragment.this,REQ_ADD_TASK_TIME);
                timePickerFragment.show(getFragmentManager(),TAG_TIME_PICKER);
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextJobTitle.getText().length()==0){
                    Toast.makeText(getActivity(),getString(R.string.musttAddTitle),Toast.LENGTH_SHORT).show();
                }else
                {
                    String title=mEditTextJobTitle.getText().toString();
                    String detail= mEditTextDetail.getText().toString();
                    mTask =new Task(title,detail,mDate);
                    TasksRepository.getInstance(getActivity()).addToAllList(mTask);
                    getActivity().getSupportFragmentManager().getFragments().get(1).onActivityResult(1, Activity.RESULT_OK,new Intent());
                    getActivity().getSupportFragmentManager().getFragments().get(0).onActivityResult(0,Activity.RESULT_OK,new Intent());
                    getActivity().getSupportFragmentManager().popBackStack();
                    dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQ_ADD_TASK_DATE){
            mDate= (Date) data.getSerializableExtra(DatePickerDialog.EXTRA_DATE);
            setTextViewDate();
        }
        if (requestCode==REQ_ADD_TASK_TIME){
            mDate= (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME_TAG);
            setTextViewTime();
        }

    }

    private void setTextViewTime() {
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat( "h:mm a");
        String formatTime=simpleDateFormat1.format(mDate);
        mTextTime.setText(formatTime);
    }

    private void setTextViewDate() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String formatDate=simpleDateFormat.format(mDate);
        mTextDate.setText(formatDate);
    }
}
