package com.example.mohamdkazem.advancetodolist.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mohamdkazem.advancetodolist.ToDoListActivity;
import com.example.mohamdkazem.advancetodolist.dataBase.TaskBaseHelper;
import com.example.mohamdkazem.advancetodolist.dataBase.TaskDbSchema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TasksRepository {

    private SQLiteDatabase mDataBase;
    private static TasksRepository tasksRepository;
    private Context mContext;

    private TasksRepository(Context context) {
        mContext = context.getApplicationContext();
        mDataBase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public static TasksRepository getInstance(Context context) {
        if (tasksRepository == null) {
            tasksRepository = new TasksRepository(context);
        }
        return tasksRepository;
    }

    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();
        String whereClause = TaskDbSchema.TasksTable.tasksCols.USER_ID + " = " + ToDoListActivity.mId;
        Cursor cursor = mDataBase.query(TaskDbSchema.TasksTable.NAME, null, whereClause, null, null, null, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.UUID)));
                String title = cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.TITLE));
                String detail = cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DETAIL));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DATE)));
                long time = cursor.getLong(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.TIME));
                boolean done = cursor.getInt(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DONE)) != 0;

                Task task = new Task(title, detail, uuid);
                task.setDone(done);
                task.setDate(date);
                task.setTime(time);
                taskList.add(task);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return taskList;
    }
    public List<Task> getDoneTaskList(){
        List<Task> taskList = new ArrayList<>();
        String whereClause = TaskDbSchema.TasksTable.tasksCols.DONE + " = " + 1 + " AND " + TaskDbSchema.TasksTable.tasksCols.USER_ID + " = " + ToDoListActivity.mId;
        Cursor cursor = mDataBase.query(TaskDbSchema.TasksTable.NAME, null, whereClause, null, null, null, null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.UUID)));
                String title = cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.TITLE));
                String detail = cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DETAIL));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DATE)));
                long time = cursor.getLong(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.TIME));
                boolean done = cursor.getInt(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DONE)) != 0;

                Task task = new Task(title, detail, uuid);
                task.setDone(done);
                task.setDate(date);
                task.setTime(time);
                taskList.add(task);

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return taskList;
    }
    public Task getTask(UUID id) {

        String whereClause = TaskDbSchema.TasksTable.tasksCols.UUID + " = ?" ;
        String[] whereArgs = new String[]{id.toString()};
        Cursor cursor = mDataBase.query(TaskDbSchema.TasksTable.NAME, null, whereClause, whereArgs, null, null, null, null);

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();

            UUID uuid = UUID.fromString(cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.UUID)));
            String title = cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.TITLE));
            String detail = cursor.getString(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DETAIL));
            Date date = new Date(cursor.getLong(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DATE)));
            long time = cursor.getLong(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.TIME));
            boolean done = cursor.getInt(cursor.getColumnIndex(TaskDbSchema.TasksTable.tasksCols.DONE)) != 0;

            Task task = new Task(title, detail, uuid);
            task.setDone(done);
            task.setDate(date);
            task.setTime(time);

            return task;
        } finally {
            cursor.close();
        }

    }
    public void delete(Task task) {
        String whereClause = TaskDbSchema.TasksTable.tasksCols.UUID + " = ? ";
        mDataBase.delete(TaskDbSchema.TasksTable.NAME, whereClause, new String[]{task.getId().toString()});
    }
    public void deleteAllTasks(){
        String whereClause = TaskDbSchema.TasksTable.tasksCols.USER_ID + " = " + ToDoListActivity.mId ;
        mDataBase.delete(TaskDbSchema.TasksTable.NAME, whereClause, null);
    }
    public void upDate(Task task){
        ContentValues values=getContentValuesTasks(task);
        String whereClause = TaskDbSchema.TasksTable.tasksCols.UUID + " = ? ";
        mDataBase.update(TaskDbSchema.TasksTable.NAME,values,whereClause, new String[]{task.getId().toString()});
    }
    public void setDoneTask(Task task){
        ContentValues values=getContentValuesTasks(task);
        String whereClause = TaskDbSchema.TasksTable.tasksCols.UUID + " = ? ";
        mDataBase.update(TaskDbSchema.TasksTable.NAME,values,whereClause, new String[]{task.getId().toString()});
    }
    public Users getUser(String userName, String passWord) {

        String whereClause = TaskDbSchema.UsersTable.usersCols.USERNAME + " = ? AND " + TaskDbSchema.UsersTable.usersCols.PASSWORD + " = ?";
        String[] whereArgs = new String[]{userName, passWord};
        Cursor cursor = mDataBase.query(TaskDbSchema.UsersTable.NAME, null, whereClause, whereArgs, null, null, null, null);


        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();

            String name = (cursor.getString(cursor.getColumnIndex(TaskDbSchema.UsersTable.usersCols.USERNAME)));
            String pass = cursor.getString(cursor.getColumnIndex(TaskDbSchema.UsersTable.usersCols.PASSWORD));
            String email=cursor.getString(cursor.getColumnIndex(TaskDbSchema.UsersTable.usersCols.EMAIL));
            int id=cursor.getInt(0);
            Users user = new Users(name, pass,email,id);
            return user;

        } finally {
            cursor.close();
        }
    }

    public ContentValues getContentValuesTasks(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.UUID, task.getId().toString());
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.TITLE, task.getTitle());
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.DETAIL, task.getDetail());
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.DATE, task.getDate().getTime());
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.TIME, task.getTime());
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.DONE, task.isDone() ? 1 : 0);
        contentValues.put(TaskDbSchema.TasksTable.tasksCols.USER_ID,ToDoListActivity.mId);
        return contentValues;
    }
    public ContentValues getContentValuesUsers(Users users) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDbSchema.UsersTable.usersCols.USERNAME, users.getName().toString());
        contentValues.put(TaskDbSchema.UsersTable.usersCols.EMAIL, users.getEmail().toString());
        contentValues.put(TaskDbSchema.UsersTable.usersCols.PASSWORD, users.getPassword().toString());
        return contentValues;
    }
    public void addUsers(Users user) {
        ContentValues values = getContentValuesUsers(user);
        mDataBase.insert(TaskDbSchema.UsersTable.NAME, null, values);
    }

    public void addToAllList(Task task) {
        ContentValues values = getContentValuesTasks(task);
        mDataBase.insert(TaskDbSchema.TasksTable.NAME, null, values);

    }




}

