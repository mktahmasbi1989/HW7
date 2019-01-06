package com.example.mohamdkazem.advancetodolist;


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
 * A simple {@link } subclass.
 */
public class AllTasksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private JobAdaptor mJobAdaptor;
    private FloatingActionButton mFloatingActionButton;
    private TextView mTextViewNoTask;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public AllTasksFragment() {
        // Required empty public constructor
    }

    public static AllTasksFragment newInstance() {

        Bundle args = new Bundle();
        AllTasksFragment fragment = new AllTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_all_tasks, container, false);
        init(view);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainLayout, AddTaskFragment.newInstance()).addToBackStack("add")
                        .commit();
            }
        });
        upDateUI();
        return view;
    }

    private void init(View view) {
        mRecyclerView=view.findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFloatingActionButton=view.findViewById(R.id.btn_action);
        mTextViewNoTask=view.findViewById(R.id.no_task_textview);
    }

    private void upDateUI() {
        List<Task> mListTask = TasksRepository.getInstance(getActivity()).getTaskList();
        if (mListTask.size()==0)
        {
            mTextViewNoTask.setVisibility(View.VISIBLE);
        }
        if (mJobAdaptor==null){
            mJobAdaptor=new JobAdaptor(mListTask);
            mRecyclerView.setAdapter(mJobAdaptor);
        }else
            mJobAdaptor.setTasks(mListTask);
            mJobAdaptor.notifyDataSetChanged();
    }

    private class JobAdaptor extends RecyclerView.Adapter<JobHolder>{

        private List<Task> mTasks;
        JobAdaptor(List<Task> mListTask) {
            mTasks = mListTask;
        }
        public void setTasks(List<Task> tasks) {
            mTasks= tasks;
        }

        @NonNull
        @Override
        public JobHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.task_holder, viewGroup, false);
            return new JobHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull JobHolder jobHolder, int i) {
            Task task = mTasks.get(i);
            jobHolder.bind(task);
            mTextViewNoTask.setVisibility(View.GONE);

        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

    private class JobHolder extends RecyclerView.ViewHolder{
        private TextView mTitle,mDetail,mFirstChar;
        private Task mTask;
        JobHolder(@NonNull final View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.job_title_holder);
            mDetail=itemView.findViewById(R.id.job_detail_holder);
            mFirstChar=itemView.findViewById(R.id.firstChar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().
                            beginTransaction().addToBackStack("detail")
                            .replace(R.id.mainLayout, TaskDetailFragment.newInstance(mTask.getId()))
                            .commit();
                }
            });
        }
        public void bind(Task task){
            mTask = task;
            mTitle.setText(mTask.getTitle());
            mDetail.setText(mTask.getDetail());
            char first= mTask.getTitle().charAt(0);
            String firsChar= String.valueOf(first);
            mFirstChar.setText(firsChar);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0){
            upDateUI();
        }
    }
}
