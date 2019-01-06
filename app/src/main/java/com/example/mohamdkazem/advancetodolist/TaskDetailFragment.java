package com.example.mohamdkazem.advancetodolist;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.mohamdkazem.advancetodolist.Model.Task;
import com.example.mohamdkazem.advancetodolist.Model.TasksRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {

    private static final String ARG_JOB_ID = "jobId";

    private Button mBtnEdite,mBtnDelete,mBtnDone;
    private TextView mTextViewDate,mTextViewTime;
    private EditText mTextViewDescribtion;
    private Task mTask;



    public TaskDetailFragment() {
        // Required empty public constructor
    }

    public static TaskDetailFragment newInstance(UUID jobId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID,jobId);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        UUID jobId= (UUID) getArguments().getSerializable(ARG_JOB_ID);
        mTask = TasksRepository.getInstance().getTask(jobId);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_task_detail, container, false);
        init(view);

        mTextViewDescribtion.setText(mTask.getDetail());
        Date date= mTask.getDate();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String formatDate=simpleDateFormat.format(date);
        mTextViewDate.setText(formatDate);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat1=new SimpleDateFormat( "h:mm a");
        String formatTime=simpleDateFormat1.format(date);
        mTextViewTime.setText(formatTime);

        if (mTask.isDone()){
            mBtnDone.setEnabled(false);
        }

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(getActivity()).setTitle("Delete Task")
                        .setMessage("Do You Want to Delete?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TasksRepository.getInstance().removeTask(mTask.getId());
                                TasksRepository.getInstance().removeDoneList(mTask.getId());
                                updateFragments();
                                getActivity().getSupportFragmentManager().popBackStack();

                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create();
                    alertDialog.show();

            }
        });
        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mTask.setDone(true);
                updateFragments();
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        mBtnEdite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.setDetail(mTextViewDescribtion.getText().toString());
                TasksRepository.getInstance().upDateTask(mTask,mTask.getId());
                updateFragments();
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        return view;
    }

    private void updateFragments() {
        getActivity().getSupportFragmentManager().getFragments().get(1).onActivityResult(1, Activity.RESULT_OK,new Intent());
        getActivity().getSupportFragmentManager().getFragments().get(0).onActivityResult(0,Activity.RESULT_OK,new Intent());
    }


    private void init(View view) {
        mBtnDelete=view.findViewById(R.id.btn_delete);
        mBtnDone=view.findViewById(R.id.btn_done);
        mBtnEdite=view.findViewById(R.id.btn_edite);
        mTextViewDescribtion=view.findViewById(R.id.textView_description);
        mTextViewDate=view.findViewById(R.id.textView_date);
        mTextViewTime=view.findViewById(R.id.textView_time);
    }

}
