package com.teamwagdin.owner.futureproofrel;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
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
import android.widget.Toast;

import java.util.Calendar;


public class EntryTrackerActivity extends ActionBarActivity {

    FutureProof theApp;
    Chronometer c;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_tracker);



        theApp = FutureProof.createInstance();
        theApp.assignAlertResponder(new FPEventListener() {
            public void onAntiquate() {
                updateEntries();
            }
        });


        c = (Chronometer)findViewById(R.id.chronometer);
        c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            long lastTime;

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-lastTime > 1000) {
                    tv = (TextView)findViewById(R.id.textView);
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

    ListView lvPast,lvFuture;
    public void updateEntries() {
        lvPast = (ListView)this.findViewById(R.id.lstPast);
        lvFuture = (ListView)this.findViewById(R.id.lstFuture);

        // ---------------------------------------------------------------------

        registerForContextMenu(lvPast);
        registerForContextMenu(lvFuture);

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
                editEntry( theApp.getCurrentUserAccount().getPastEntries().get(position) );
            }
        });

        lvFuture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editEntry( theApp.getCurrentUserAccount().getFutureEntries().get(position) );
            }
        });
    }

    public void editEntry(Entry e) {
        theApp.applicationBundle.putInt("editEntry",e.id);
        //
        theApp.shiftActivity(this, EntryEditActivity.class);
    }
    public void deleteEntry(Entry e) {
        // TODO: DELETE THE ENTRY
    }




    final int CONTEXT_EDIT = 100;
    final int CONTEXT_DELETE = 200;

    boolean checkFuture; // ??? <-- ... This is just so bad...
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("For this entry...");
        if (v.getId() == R.id.lstPast) {
            checkFuture = false;
        } else if (v.getId() == R.id.lstFuture) {
            checkFuture = true;
        }
        //
        menu.add(0, CONTEXT_EDIT, 0, "Edit");
        menu.add(1, CONTEXT_DELETE, 1, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // Toast.makeText(this,""+item.getItemId(),Toast.LENGTH_SHORT).show();
        // Toast.makeText(this,""+info.position,Toast.LENGTH_SHORT).show();

        Entry e;
        if (checkFuture) {
            e = theApp.getCurrentUserAccount().getFutureEntries().get(info.position);
        } else {
            e = theApp.getCurrentUserAccount().getPastEntries().get(info.position);
        }
        //
        if (item.getItemId() == CONTEXT_EDIT) {
            editEntry( e );
        }
        else if (item.getItemId() == CONTEXT_DELETE) {
            deleteEntry( e );
        }
        //
        return false;
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
