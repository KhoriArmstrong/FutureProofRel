package com.teamwagdin.owner.futureproofrel;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class CreateEntryActivity extends ActionBarActivity {


    FutureProof theApp;
    private static Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);


        currentActivity = this;
        theApp = FutureProof.createInstance();


        resetFields();
    }



    public void resetFields() {
        EntryDate ed = FutureProof.getPresentDateTime();

        ((EditText)findViewById(R.id.txtMonth)).setText(""+(ed.month+1));
        ((EditText)findViewById(R.id.txtDay)).setText(""+ed.day);
        ((EditText)findViewById(R.id.txtYear)).setText(""+ed.year);
        ((EditText)findViewById(R.id.txtHour)).setText(""+ed.hour);
        ((EditText)findViewById(R.id.txtMinute)).setText(""+ed.minute);
        ((EditText)findViewById(R.id.txtMessage)).setText("");
    }
    public void performSend(View view) {
        int month = Integer.parseInt(((EditText)findViewById(R.id.txtMonth)).getText().toString());
        int day = Integer.parseInt(((EditText)findViewById(R.id.txtDay)).getText().toString());
        int year = Integer.parseInt(((EditText)findViewById(R.id.txtYear)).getText().toString());
        int hour = Integer.parseInt(((EditText)findViewById(R.id.txtHour)).getText().toString());
        int minute = Integer.parseInt(((EditText)findViewById(R.id.txtMinute)).getText().toString());
        //
        EntryDate newTime = new EntryDate(month-1,day,year,hour,minute);
        //
        Entry e = new Entry(newTime,((EditText)findViewById(R.id.txtMessage)).getText().toString());


        theApp.sendEntry(e);


        this.finish();
    }

    public void setToPresent(View view) {
        DialogFragment newFragment;
        newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

        newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    // !!! <-- A visual of a clock and numbers which represent the date... When a date field is changed, the clock visually "tunes" itself to that time, as well as the month/year/day numbers flipping through to their correct time




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user


            ((EditText)currentActivity.findViewById(R.id.txtHour)).setText(""+hourOfDay);
            ((EditText)currentActivity.findViewById(R.id.txtMinute)).setText(""+minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user


            ((EditText)currentActivity.findViewById(R.id.txtMonth)).setText(""+(month+1));
            ((EditText)currentActivity.findViewById(R.id.txtDay)).setText(""+day);
            ((EditText)currentActivity.findViewById(R.id.txtYear)).setText(""+year);
        }
    }
}
