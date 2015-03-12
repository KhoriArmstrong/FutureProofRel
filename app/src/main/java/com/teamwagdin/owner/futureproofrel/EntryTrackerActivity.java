package com.teamwagdin.owner.futureproofrel;

import android.database.DataSetObserver;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EntryTrackerActivity extends ActionBarActivity {

    SomeApplication theApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_tracker);


        theApp = SomeApplication.createInstance();
        //
        SomeAccount account = theApp.createNewAccount(new SomeForm(new User("Khori")));
        //
        theApp.login(account.myForm.myUser);


        theApp.sendEntry(new Entry(new EntryDate(EntryDate.FEBRUARY,26,2015,15,58), "Hello World-- From Khori"));
        theApp.sendEntry(new Entry(new EntryDate(EntryDate.MARCH,6,2016,18,10), "Hello World-- From Luke"));


        Chronometer c = (Chronometer)findViewById(R.id.chronometer);
        c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            long lastTime;

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-lastTime > 1000) {
                    TextView tv = (TextView)findViewById(R.id.textView);
                    tv.setText(theApp.getPresentDateTime().toString());

                    lastTime = SystemClock.elapsedRealtime();
                }
            }
        });
        //
        c.start();


        updateEntries();
    }


    public void updateEntries() {
        ListView lvPast = (ListView)this.findViewById(R.id.listView1);
        ListView lvFuture = (ListView)this.findViewById(R.id.listView2);
        //
        lvPast.setAdapter(new ArrayAdapter<Entry>(this,
            android.R.layout.simple_list_item_1,
                theApp.getUserAccount().getPastEntries()));
        lvFuture.setAdapter(new ArrayAdapter<Entry>(this,
            android.R.layout.simple_list_item_1,
            theApp.getUserAccount().getFutureEntries()));
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
        //
        updateEntries();


        ((EditText)findViewById(R.id.txtMonth)).setText("");
        ((EditText)findViewById(R.id.txtDay)).setText("");
        ((EditText)findViewById(R.id.txtYear)).setText("");
        ((EditText)findViewById(R.id.txtHour)).setText("");
        ((EditText)findViewById(R.id.txtMinute)).setText("");
        ((EditText)findViewById(R.id.txtMessage)).setText("");
    }

    public void setToPresent(View view) {
        EntryDate ed = theApp.getPresentDateTime();
        //
        ((EditText)findViewById(R.id.txtMonth)).setText(""+(ed.month+1));
        ((EditText)findViewById(R.id.txtDay)).setText(""+ed.day);
        ((EditText)findViewById(R.id.txtYear)).setText(""+ed.year);
        ((EditText)findViewById(R.id.txtHour)).setText(""+ed.hour);
        ((EditText)findViewById(R.id.txtMinute)).setText(""+ed.minute);
    }
    // !!! <-- A visual of a clock and numbers which represent the date... When a date field is changed, the clock visually "tunes" itself to that time, as well as the month/year/day numbers flipping through to their correct time



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_tracker, menu);
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
