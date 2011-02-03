package com.rapidftr.utilities;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

public class DateParser {

    // "01/02/2011 21:58"
    private static final int[] FMT1_DAY = {0,2};
    private static final int[] FMT1_MONTH = {3,5};
    private static final int[] FMT1_YEAR = {6,10};
    private static final int[] FMT1_HOUR = {11,13};
    private static final int[] FMT1_MIN = {14,16};

    // Wed Feb 02 03:38:57 GMT 2011
    private static final int[] FMT2_DAY = {8,10};
    private static final int[] FMT2_MONTH = {4,7};
    private static final int[] FMT2_YEAR = {24,28};
    private static final int[] FMT2_HOUR = {11,13};
    private static final int[] FMT2_MIN = {14,16};

    private static Vector MONTHS;
    static {
        MONTHS = new Vector();
        MONTHS.addElement("Jan");
        MONTHS.addElement("Feb");
        MONTHS.addElement("Mar");
        MONTHS.addElement("Apr");
        MONTHS.addElement("May");
        MONTHS.addElement("Jun");
        MONTHS.addElement("Jul");
        MONTHS.addElement("Aug");
        MONTHS.addElement("Sep");
        MONTHS.addElement("Oct");
        MONTHS.addElement("Nov");
        MONTHS.addElement("Dec");
    }

    public Calendar parse(String date) {
        Calendar result = parseFirstFormat(date);
        if (result != null) {
            return result;
        }
        return parseSecondFormat(date);
    }

    public Calendar parseFirstFormat(String date) {

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, Integer.parseInt(substring(date, FMT1_DAY)));
            calendar.set(Calendar.MONTH, Integer.parseInt(substring(date, FMT1_MONTH)) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(substring(date, FMT1_YEAR)));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(substring(date, FMT1_HOUR)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(substring(date, FMT1_MIN)));
            calendar.getTime(); // forces validation of fields
            return calendar;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Calendar parseSecondFormat(String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, Integer.parseInt(substring(date, FMT2_DAY)));
            calendar.set(Calendar.MONTH, MONTHS.indexOf(substring(date, FMT2_MONTH)));
            calendar.set(Calendar.YEAR, Integer.parseInt(substring(date, FMT2_YEAR)));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(substring(date, FMT2_HOUR)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(substring(date, FMT2_MIN)));
            calendar.getTime(); // forces validation of fields
            return calendar;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String substring(String aString, int[] startEnd) {
        return aString.substring(startEnd[0], startEnd[1]);
    }

}
