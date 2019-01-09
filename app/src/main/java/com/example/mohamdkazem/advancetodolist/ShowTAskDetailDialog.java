package com.example.mohamdkazem.advancetodolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamdkazem.advancetodolist.Model.Task;

import java.io.Serializable;

public class ShowTAskDetailDialog extends DialogFragment {
    private static final String ARG_TASK ="task" ;
    private Task mTask;



    public static ShowTAskDetailDialog newInstance(Task task) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK,  task);
        ShowTAskDetailDialog fragment = new ShowTAskDetailDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask= (Task) getArguments().getSerializable(ARG_TASK);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.task_detail_dialog,container,false);

        return view;


    }
}
