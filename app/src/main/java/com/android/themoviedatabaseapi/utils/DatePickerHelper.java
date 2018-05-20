package com.android.themoviedatabaseapi.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import java.util.TimeZone;


public class DatePickerHelper implements DatePickerDialog.OnDateSetListener {
    private EditText _editText;
    private int _day, _month, _birthYear;
    private Context _context;
    private DatePickerDialog _dialog;
    private Calendar calendar;

    public DatePickerHelper(Context _context, EditText _editText) {
        this._context = _context;
        this._editText = _editText;
        calendar = Calendar.getInstance(TimeZone.getDefault());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;

        updateDisplay();
    }

    private void updateDisplay() {
        String mDay, mMonth;
        mDay = _day >= 10 ? String.valueOf(_day) : "0" + _day;
        mMonth = (_month + 1) >= 10 ? String.valueOf((_month + 1)) : "0" + (_month + 1);
        /*_editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mDay).append("-").append(mMonth).append("-").append(_birthYear).append(" "));*/
        _editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_birthYear).append("-").append(mMonth).append("-").append(mDay));
        _editText.setFocusable(true);
        _editText.setSelection(_editText.getText().length() - 1);
    }

    public void showDialog() {
        _dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        _dialog.setCancelable(false);
        _dialog.show();
    }

}
