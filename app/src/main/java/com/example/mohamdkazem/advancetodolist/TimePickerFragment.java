package com.example.mohamdkazem.advancetodolist;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment {


    private static final String ARG_TIME ="com.example.mohamdkazem.advancetodolist.time" ;
    public static final String EXTRA_TIME_TAG ="com.example.mohamdkazem.advancetodolist.time_Tag" ;
    private Date mDate;
    private TimePicker mTimePicker;


    public TimePickerFragment() {
        // Required empty public constructor
    }

    public static TimePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME,date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDate= (Date) getArguments().getSerializable(ARG_TIME);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_time_picker, null);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final int second = calendar.get(Calendar.SECOND);

        mTimePicker = view.findViewById(R.id.time_picker);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Time Picker")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = 0;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            hour = mTimePicker.getHour();
                        } else {
                            hour = mTimePicker.getCurrentHour();
                        }
                        int minute = 0;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            minute = mTimePicker.getMinute();
                        } else {
                            minute = mTimePicker.getCurrentMinute();
                        }

                        Date date = new GregorianCalendar(year, month, day, hour, minute, second)
                                .getTime();

                        sendResult(date);
                    }
                })
                .create();
    }
    private void sendResult(Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME_TAG, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        getTargetFragment().onResume();
    }
}
