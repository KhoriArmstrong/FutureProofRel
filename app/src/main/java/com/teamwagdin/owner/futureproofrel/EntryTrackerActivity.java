package com.teamwagdin.owner.futureproofrel;

import android.database.DataSetObserver;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
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
        theApp.sendEntry(new Entry(new EntryDate(EntryDate.MARCH,6,2015,18,10), "Hello World-- From Luke"));


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
