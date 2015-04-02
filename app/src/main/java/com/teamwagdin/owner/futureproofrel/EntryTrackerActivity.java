package com.teamwagdin.owner.futureproofrel;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class EntryTrackerActivity extends ActionBarActivity {

    FutureProof theApp;
    private static Activity currentActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_tracker);


        currentActivity = this;
        theApp = FutureProof.createInstance();
        theApp.assignAlertResponder(new FPEventListener() {
            public void onAntiquate() {
                updateEntries();
            }
        });


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


    @Override
    protected void onResume() {
        super.onResume();

        updateEntries();
    }

    public void updateEntries() {
        ListView lvPast = (ListView)this.findViewById(R.id.listView1);
        ListView lvFuture = (ListView)this.findViewById(R.id.listView2);

        // ---------------------------------------------------------------------

        lvPast.setAdapter(new ArrayAdapter<Entry>(this,
            android.R.layout.simple_list_item_1,
                theApp.getCurrentUserAccount().getPastEntries()));

        lvFuture.setAdapter(new ArrayAdapter<Entry>(this,
            android.R.layout.simple_list_item_1,
            theApp.getCurrentUserAccount().getFutureEntries()));

        // ---------------------------------------------------------------------




        lvPast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry e = theApp.getCurrentUserAccount().getPastEntries().get(position);
                //
                theApp.applicationBundle.putInt("editEntry",e.id);
                //
                theApp.shiftActivity(currentActivity, EntryEditActivity.class);
            }
        });

        lvFuture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry e = theApp.getCurrentUserAccount().getFutureEntries().get(position);
                //
                theApp.applicationBundle.putInt("editEntry",e.id);
                //
                theApp.shiftActivity(currentActivity, EntryEditActivity.class);
            }
        });
    }


    public void gotoNewEntry(View view) {
        theApp.shiftActivity(this, CreateEntryActivity.class);
    }

    //see all past entries
    public void seePast(View view) {
        // theApp.shiftActivity(this, SeePastEntriesActivity.class);
    }

    //see all future entries
    public void seeFuture(View view) {
        // theApp.shiftActivity(this, SeeFutureEntriesActivity.class);
    }


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
