package com.example.to_dolist;

import com.example.to_dolist.Interfaces.DateValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements DateValidator {

    private String dateFormat;
    @Override
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public DateFormatValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
