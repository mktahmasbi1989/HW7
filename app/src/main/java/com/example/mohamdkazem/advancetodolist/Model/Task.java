package com.example.mohamdkazem.advancetodolist.Model;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private String mTitle;
    private String mDetail;
    private Date mDate;
    private UUID mId;
    private Time mTime;
    private boolean mDone;

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public Task(String title, String detail) {
        mTitle = title;
        if (detail==null) {
            mDetail = "";
        }else mDetail=detail;
        mId = UUID.randomUUID();
        mDate = new Date();
        mDone=false;
    }


    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public Time getTime() {
        return mTime;
    }
    public void setTime(Time time) {
        mTime = time;
    }
    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public String getDetail() {
        return mDetail;
    }
    public void setDetail(String detail) {
        mDetail = detail;
    }


}
