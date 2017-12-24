package by.xo.egorp.finance.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

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

    CalendarView calendarView;
    Button btnYesterday;
    Button btnToday;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                checkClick();
            }
        });

        v.findViewById(R.id.btn_OK).setOnClickListener(this);
        v.findViewById(R.id.btn_CANCEL).setOnClickListener(this);
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
            //These buttons do not need a double tap, to immediately exit.
            case R.id.btn_CANCEL:
                calendar.setTime(beforeCalendar);
            case R.id.btn_OK:
                exitCalendarDialog();
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
        calendarView.setDate(getDate().getTime(), true, true);
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
        btnToday.setBackgroundColor(0x00000000);
        btnYesterday.setBackgroundColor(0x00000000);
        if (getDateToString().equals(convertDataToString(today().getTime()))) {
            btnToday.setBackgroundResource(R.color.colorBackgroundTabLayout);
        } else if (getDateToString().equals(convertDataToString(yesterday().getTime()))) {
            btnYesterday.setBackgroundResource(R.color.colorBackgroundTabLayout);
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
