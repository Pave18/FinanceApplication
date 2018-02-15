package by.xo.egorp.finance.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import by.xo.egorp.finance.R;

public class CalendarDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG_DATE_SELECTED = "date";

    Date beforeCalendar;
    Calendar calendar;

    public CalendarDialog() {
        calendar = Calendar.getInstance();
    }

    MaterialCalendarView calendarView;
    Button btnYesterday;
    Button btnToday;

    public static CalendarDialog newInstance() {

        Bundle args = new Bundle();

        CalendarDialog fragment = new CalendarDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setView(onCreateView());

        builder.setPositiveButton(R.string.title_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitCalendarDialog();
            }
        });

        builder.setNegativeButton(R.string.title_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                calendar.setTime(beforeCalendar);
            }
        });


        return builder.create();
    }

    public View onCreateView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_calendar, null);

        beforeCalendar = getDate();

        btnYesterday = v.findViewById(R.id.btn_yesterday);
        btnYesterday.setOnClickListener(this);
        btnToday = v.findViewById(R.id.btn_today);
        btnToday.setOnClickListener(this);
        checkClick();

        calendarView = v.findViewById(R.id.calendarView);
        v.findViewById(R.id.calendarView).setOnClickListener(this);
        setDateOnCalendarView();


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                calendar.set(Calendar.YEAR, calendarDay.getYear());
                calendar.set(Calendar.MONTH, calendarDay.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, calendarDay.getDay());
                checkClick();
            }
        });


        return v;
    }

    private static long time_pressed;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yesterday:
                calendar = yesterday();
                break;
            case R.id.btn_today:
                calendar = today();
                break;
        }

        checkClick();
        setDateOnCalendarView();

        if (time_pressed + 500 > System.currentTimeMillis()) {
            exitCalendarDialog();
        }
        time_pressed = System.currentTimeMillis();
    }

    // Passing the value to the IncomeAndExpenditureFragment. (onActivityResult)
    private void exitCalendarDialog() {
        Intent intent = new Intent();
        intent.putExtra(TAG_DATE_SELECTED, getDateToString());
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }

    void setDateOnCalendarView() {
        calendarView.clearSelection();
        calendarView.setDateSelected(calendar, true);
    }

    public Date getDate() {
        return calendar.getTime();
    }

    public String getDateToString() {
        return convertDataToString(getDate());
    }

    private String convertDataToString(Date date) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
    }

    void checkClick() {
        //dell background
        btnToday.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        btnYesterday.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        if (getDateToString().equals(convertDataToString(today().getTime()))) {
            btnToday.setBackgroundResource(R.color.colorPrimaryDark);
        } else if (getDateToString().equals(convertDataToString(yesterday().getTime()))) {
            btnYesterday.setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    Calendar today() {
        return Calendar.getInstance();
    }

    Calendar yesterday() {
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.add(Calendar.DATE, -1);
        return tempCalendar;
    }
}
