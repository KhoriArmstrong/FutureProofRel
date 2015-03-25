package com.teamwagdin.owner.futureproofrel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class NotifyActivity extends ActionBarActivity {

    SomeApplication theApp;
    private TextView tvCurrentTime;
    private EditText etMessage;

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);


        theApp = SomeApplication.createInstance();
        //
        ((Chronometer)findViewById(R.id.chronometer2)).start();
        ((Chronometer)findViewById(R.id.chronometer2)).setOnChronometerTickListener( new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                ((TextView)findViewById(R.id.tvCurrentTime)).setText("Current time: " + theApp.getPresentDateTime());
                // ((TextView)findViewById(R.id.tvTargetTime)).setText("Target time: " + tc.targetTime);
            }
        });
    }


    public void setNewTime(View view) {
        String mes;
        EntryDate ed;
        //
        int day= Integer.parseInt(((TextView) findViewById(R.id.txtDay)).getText().toString());
        int month= Integer.parseInt(((TextView)findViewById(R.id.txtMonth)).getText().toString())-1;
        int hour= Integer.parseInt(((TextView)findViewById(R.id.txtHour)).getText().toString());
        int min= Integer.parseInt(((TextView)findViewById(R.id.txtMinute)).getText().toString());
        int year= Integer.parseInt(((TextView)findViewById(R.id.txtYear)).getText().toString());
        //
        ed = new EntryDate(month,day,year,hour,min);
        mes = ((EditText)findViewById(R.id.txtMessage)).getText().toString();
        //
        Entry e = new Entry(ed,mes);
        //
        theApp.createEntryAlarm(e);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notify, menu);
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
}
