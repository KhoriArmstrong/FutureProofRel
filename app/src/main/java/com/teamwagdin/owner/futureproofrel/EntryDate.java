package com.teamwagdin.owner.futureproofrel;

import java.util.ArrayList;
import java.util.Date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yangjiachang on 15-02-05.
 */
public class EntryDate {
    int month = 0;
    public static final int JANUARY=0,FEBRUARY=1,MARCH=2,APRIL=3,MAY=4,JUNE=5,JULY=6,AUGUST=7,SEPTEMBER=8,OCTOBER=9,NOVEMBER=10,DECEMBER=11;
    int day = 0;
    int year = 0;
    int hour = 0;
    int minute = 0;


    public static final EntryDate NEVER = new EntryDate(DECEMBER, 31, 9999, 23, 50);


    public EntryDate() {
        Date d = new Date();
        //
        month = d.getMonth();
        day = d.getDate();
        year = 1900+d.getYear();
        hour = d.getHours();
        minute = d.getMinutes();
    }

    /**
     *
     * @param thisMonth
     * @param thisDay
     * @param thisYear
     * @param thisHour
     * @param thisMinute
     */
    public EntryDate(int thisMonth, int thisDay, int thisYear, int thisHour, int thisMinute) {
        month = thisMonth;
        day = thisDay;
        year = thisYear;
        hour = thisHour;
        minute = thisMinute;
    }


    public boolean After(EntryDate that) {
        if (this.year < that.year) { return false; }
        else if (this.year > that.year) { return true; }
        else {
            if (this.month < that.month) { return false; }
            else if (this.month > that.month) { return true; }
            else {
                if (this.day < that.day) { return false; }
                else if (this.day > that.day) { return true; }
                else {
                    if (this.hour < that.hour) { return false; }
                    else if (this.hour > that.hour) { return true; }
                    else {
                        if (this.minute < that.minute) { return false; }
                        else if (this.minute > that.minute) { return true; }
                        else {
                            return false;
                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof EntryDate) {
            EntryDate ed = (EntryDate) o;

            if (month == ed.month) {
                if (day == ed.day) {
                    if (year == ed.year) {
                        if (hour == ed.hour) {
                            if (minute == ed.minute) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "("+hour+":"+String.format("%02d",minute)+")["+String.format("%02d",day)+"/"+String.format("%02d",(month+1))+"/"+year+"]";
    }
}
