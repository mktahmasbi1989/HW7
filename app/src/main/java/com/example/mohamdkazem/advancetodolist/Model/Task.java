package com.example.mohamdkazem.advancetodolist.Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task implements Serializable {
    private String mTitle;
    private String mDetail;
    private Date mDate;
    private UUID mId;
    private long mTime;
    private boolean mDone;

    public Task(String title, String detail, UUID uuid) {
        mTitle = title;
        if (detail == null) {
            mDetail = "";
        } else mDetail = detail;
        mId = uuid;
        mDate = new Date();
        mDone = false;
        mTime = new Date().getTime();
    }

    public Task(String mTitle, String mDetail, Date mDate) {
        this.mTitle = mTitle;
        this.mDetail = mDetail;
        this.mDate = mDate;
        this.mId=UUID.randomUUID();
        mDone=false;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public Task(String title, String detail) {
        mTitle = title;
        if (detail == null) {
            mDetail = "";
        } else mDetail = detail;
        mId = UUID.randomUUID();
        mDate = new Date();
        mDone = false;
        mTime = new Date().getTime();
    }


    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public Long getTime() {
        return mTime;
    }
    public void setTime(Long time) {
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
