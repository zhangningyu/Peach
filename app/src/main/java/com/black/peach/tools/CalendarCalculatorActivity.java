package com.black.peach.tools;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.black.peach.R;
import com.black.peach.utils.CalendarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarCalculatorActivity extends Activity implements View.OnClickListener {

    private TextView mDateStart;
    private TextView mDateEnd;
    private TextView mDateResult;
    private Button mButtonStart;
    private int mSunDates;
    private int mMonday;
    private int mTuesday;
    private int mWednesday;
    private int mThursday;
    private int mFriday;
    private int mSaturday;
    private int mSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canlendar_cal);
        mDateStart = (TextView) findViewById(R.id.date_start);
        mDateStart.setOnClickListener(this);
        mDateEnd = (TextView) findViewById(R.id.date_end);
        mDateEnd.setOnClickListener(this);
        mDateResult = (TextView) findViewById(R.id.date_result);
        mButtonStart = (Button) findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.date_start:
                showDatePickerDialog(1);
                break;
            case R.id.date_end:
                showDatePickerDialog(2);
                break;
            case R.id.button_start:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                StringBuffer stringBuffer = new StringBuffer();
                String day = getString(R.string.day);
                try {
                    Date dateStart = sdf.parse(mDateStart.getText().toString());
                    Date dateEnd = sdf.parse(mDateEnd.getText().toString());
                    mSunDates = getGapCount(dateStart, dateEnd);
                    if (mSunDates <= 0) {
                        Toast.makeText(this, getString(R.string.error_date_tips), Toast.LENGTH_SHORT).show();
                    }
                    stringBuffer.append(getString(R.string.total_days) + " : " + mSunDates + day + "\n\n");
                    initDay();
                    int i;
                    for (i = 0; i < mSunDates; i++) {
                        int week = CalendarUtils.getWeekByDate(getDistanceDate(dateStart, i));
                        switch (week) {
                            case 1:
                                mMonday++;
                                break;
                            case 2:
                                mTuesday++;
                                break;
                            case 3:
                                mWednesday++;
                                break;
                            case 4:
                                mThursday++;
                                break;
                            case 5:
                                mFriday++;
                                break;
                            case 6:
                                mSaturday++;
                                break;
                            case 7:
                                mSunday++;
                                break;
                            default:
                                break;
                        }
                    }
                    stringBuffer.append(getString(R.string.monday) + " : " + mMonday + day + "\n"
                            + getString(R.string.tuesday) + " : " + mTuesday + day + "\n"
                            + getString(R.string.wednesday) + " : " + mWednesday + day + "\n"
                            + getString(R.string.thursday) + " : " + mThursday + day + "\n"
                            + getString(R.string.friday) + " : " + mFriday + day + "\n"
                            + getString(R.string.saturday) + " : " + mSaturday + day + "\n"
                            + getString(R.string.sunday) + " : " + mSunday + day + "\n");
                    mDateResult.setText(stringBuffer.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void showDatePickerDialog(final int value) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(CalendarCalculatorActivity.this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (value == 1) {
                    mDateStart.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                } else if (value == 2) {
                    mDateEnd.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                }
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        int result =  (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime())
                / (1000 * 60 * 60 * 24)) + 1;
        return result;
    }

    public String getDistanceDate(Date dateFrom, int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(dateFrom);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    private void initDay() {
        mMonday = 0;
        mTuesday = 0;
        mWednesday = 0;
        mThursday = 0;
        mFriday = 0;
        mSaturday = 0;
        mSunday = 0;
    }
}
