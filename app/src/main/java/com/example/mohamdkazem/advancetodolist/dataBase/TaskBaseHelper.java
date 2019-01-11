package com.example.mohamdkazem.advancetodolist.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskBaseHelper extends SQLiteOpenHelper {

    public TaskBaseHelper(Context context) {
        super(context, TaskDbSchema.NAME, null, TaskDbSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" Create table "+ TaskDbSchema.TasksTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                TaskDbSchema.TasksTable.tasksCols.UUID + ", " +
                TaskDbSchema.TasksTable.tasksCols.TITLE + ", " +
                TaskDbSchema.TasksTable.tasksCols.DETAIL + ", " +
                TaskDbSchema.TasksTable.tasksCols.DATE + ", " +
                TaskDbSchema.TasksTable.tasksCols.TIME + ", " +
                TaskDbSchema.TasksTable.tasksCols.DONE + ")"
        );

        db.execSQL(" Create table "+ TaskDbSchema.UsersTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                TaskDbSchema.UsersTable.usersCols.USERNAME + ", " +
                TaskDbSchema.UsersTable.usersCols.PASSWORD + ", " +
                TaskDbSchema.UsersTable.usersCols.EMAIL + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
