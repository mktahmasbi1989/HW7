package com.example.mohamdkazem.advancetodolist;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamdkazem.advancetodolist.Model.Task;
import com.example.mohamdkazem.advancetodolist.Model.TasksRepository;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTasksFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DonJobAdaptor mDonJobAdaptor;
    private FloatingActionButton floatingActionButton;

    public DoneTasksFragment() {
        // Required empty public constructor
    }

    public static DoneTasksFragment newInstance() {

        Bundle args = new Bundle();
        DoneTasksFragment fragment = new DoneTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        mRecyclerView = view.findViewById(R.id.recycleView);
        floatingActionButton=view.findViewById(R.id.btn_action);
        floatingActionButton.setVisibility(View.INVISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        upDateUI();
        return view;
    }


    private void upDateUI() {
        if (ToDoListActivity.mId>0) {
            List<Task> mListTask = TasksRepository.getInstance(getActivity()).getTaskList();
            if (mDonJobAdaptor == null) {
                mDonJobAdaptor = new DonJobAdaptor(mListTask);
                mRecyclerView.setAdapter(mDonJobAdaptor);
            } else
                mDonJobAdaptor.setTasks(mListTask);
            mDonJobAdaptor.notifyDataSetChanged();

        }else {
            List<Task> mListTask = TasksRepository.getInstance(getActivity()).getTaskListGuest();
            if (mDonJobAdaptor == null) {
                mDonJobAdaptor = new DonJobAdaptor(mListTask);
                mRecyclerView.setAdapter(mDonJobAdaptor);
            } else
                mDonJobAdaptor.setTasks(mListTask);
            mDonJobAdaptor.notifyDataSetChanged();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        upDateUI();
    }

    private class DonJobAdaptor extends RecyclerView.Adapter<DonJobHolder> {
        private List<Task> mTasks;

        DonJobAdaptor(List<Task> tasks) {
            mTasks = tasks;
        }
        public void setTasks(List<Task> tasks) {
            mTasks= tasks;
        }

        @NonNull
        @Override
        public DonJobHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.task_holder, viewGroup, false);
            DonJobHolder donJobHolder = new DonJobHolder(view);
            return donJobHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DonJobHolder donJobHolder, int i) {
            Task task = mTasks.get(i);
            donJobHolder.bind(task);

        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

    private class DonJobHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDetail;
        private TextView mFirstChar;
        private Task mTask;

        DonJobHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.job_title_holder);
            mDetail = itemView.findViewById(R.id.job_detail_holder);
            mFirstChar = itemView.findViewById(R.id.firstChar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TaskDetailFragment taskDetailFragment = TaskDetailFragment.newInstance(mTask.getId());
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().addToBackStack("detail")
                            .replace(R.id.mainLayout, taskDetailFragment).commit();
                }
            });
        }

        public void bind(Task task) {
            mTask = task;
            mTitle.setText(mTask.getTitle());
            mDetail.setText(mTask.getDetail());
            char first = mTask.getTitle().charAt(0);
            String firsChar = String.valueOf(first);
            mFirstChar.setText(firsChar);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)
                upDateUI();
        }
    }

}
