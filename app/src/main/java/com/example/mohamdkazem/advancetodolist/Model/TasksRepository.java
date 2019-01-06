package com.example.mohamdkazem.advancetodolist.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TasksRepository {

    private List<Task> mTaskList;
    private List<Task> mDoneTaskList;
    private static TasksRepository tasksRepository;


    private TasksRepository() {
        mTaskList = new ArrayList<Task>() {
        };
        mDoneTaskList = new ArrayList<Task>() {
        };
    }

    public static TasksRepository getInstance() {
        if (tasksRepository == null) {
            tasksRepository = new TasksRepository();
        }
        return tasksRepository;
    }

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public Task getTask(UUID id) {
        List<Task> list = getTaskList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id))
                return list.get(i);
        }
        return null;
    }

    public List<Task> getDoneList(){
        return mTaskList;
    }

    public void upDateTask(Task task,UUID id){
        for (int i = 0; i <mTaskList.size() ; i++) {
            if (mTaskList.get(i).getId().equals(id)){
                mTaskList.remove(mTaskList.get(i));
                mTaskList.add(i,task);
            }

        }
    }

    public void removeTask(UUID id) {
        for (int i = 0; i < mTaskList.size(); i++) {
            if (mTaskList.get(i).getId().equals(id)) {
                mTaskList.remove(mTaskList.get(i));
            }
        }
    }

    public void  removeDoneList(UUID id){
        for (int i = 0; i <mDoneTaskList.size() ; i++) {
            if (mDoneTaskList.get(i).getId().equals(id)){
                mDoneTaskList.remove(mDoneTaskList.get(i));
            }

        }
    }

    public void addToAllList(Task task) {
        mTaskList.add(task);
    }

}

