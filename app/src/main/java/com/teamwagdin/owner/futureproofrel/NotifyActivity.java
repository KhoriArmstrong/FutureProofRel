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
    TimeChecker tc;
    Context c;
    private TextView tvCurrentTime;
    private EditText etMessage;
    MyNotification mn;

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);


        theApp = SomeApplication.createInstance();
        tc = new TimeChecker(SomeApplication.getPresentDateTime(),EntryDate.NEVER);

        c = this;
        //
        mn = new MyNotification(c, new Intent(c,NotifyActivity.class));

        ((Chronometer)findViewById(R.id.chronometer2)).start();
        ((Chronometer)findViewById(R.id.chronometer2)).setOnChronometerTickListener( new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                tc.currentTime = theApp.getPresentDateTime();
                //
                ((TextView)findViewById(R.id.tvCurrentTime)).setText("Current time: " + tc.currentTime);
                ((TextView)findViewById(R.id.tvTargetTime)).setText("Target time: " + tc.targetTime);


                if (tc.timeHasPassed()) {
                    // TODO: Display the notification-- But ONLY once!



                    if(!mn.hasDisplay()) {
                        mn.m = ((EditText)findViewById(R.id.txtMessage)).getText().toString();
                        //
                        mn.Display();
                    }
                }
            }
        });
    }


    public void setNewTime(View view) {
        EntryDate ed;
         // TODO: THIS is what we need to create.
        //
        int day= Integer.parseInt(((TextView) findViewById(R.id.txtDay)).getText().toString());
        int month= Integer.parseInt(((TextView)findViewById(R.id.txtMonth)).getText().toString())-1;
        int hour= Integer.parseInt(((TextView)findViewById(R.id.txtHour)).getText().toString());
        int min= Integer.parseInt(((TextView)findViewById(R.id.txtMinute)).getText().toString());
        int year= Integer.parseInt(((TextView)findViewById(R.id.txtYear)).getText().toString());

        ed = new EntryDate(month,day,year,hour,min);


        tc.targetTime = ed;
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
