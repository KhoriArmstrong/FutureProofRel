package com.teamwagdin.owner.futureproofrel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EntryEditActivity extends ActionBarActivity {

    FutureProof theApp;
    Entry workingEntry = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_edit);


        theApp = FutureProof.createInstance();


        int blah = this.getIntent().getExtras().getInt("editEntry");
        //
        for (Entry anEntry : theApp.getAllEntries()) {
            if (anEntry.id == blah) {
                workingEntry = anEntry;
                break;
            }
        }
        //
        if (workingEntry == null) {
            // TODO: THROW AN EXCEPTION
            this.finish();
        }
        //

        populateFields();
        defaultFields();
    }


    public void populateFields() {
        ((EditText) findViewById(R.id.txtOldMessage)).setText(workingEntry.message);
        ((EditText) findViewById(R.id.txtOldHour)).setText(""+workingEntry.targetDate.hour);
        ((EditText) findViewById(R.id.txtOldMinute)).setText(""+workingEntry.targetDate.minute);
        ((EditText) findViewById(R.id.txtOldDay)).setText(""+workingEntry.targetDate.day);
        ((EditText) findViewById(R.id.txtOldMonth)).setText(""+workingEntry.targetDate.month);
        ((EditText) findViewById(R.id.txtOldYear)).setText(""+workingEntry.targetDate.year);
    }
    public void defaultFields() {
        ((EditText) findViewById(R.id.txtNewMessage)).setText(workingEntry.message);
        ((EditText) findViewById(R.id.txtNewHour)).setText(""+workingEntry.targetDate.hour);
        ((EditText) findViewById(R.id.txtNewMinute)).setText(""+workingEntry.targetDate.minute);
        ((EditText) findViewById(R.id.txtNewDay)).setText(""+workingEntry.targetDate.day);
        ((EditText) findViewById(R.id.txtNewMonth)).setText(""+workingEntry.targetDate.month);
        ((EditText) findViewById(R.id.txtNewYear)).setText(""+workingEntry.targetDate.year);
    }


    public void doAccept(View view) {
        String newMessage = ((EditText) findViewById(R.id.txtNewMessage)).getText().toString();
        int newHour,newMinute,newDay,newMonth,newYear;
        newHour = Integer.parseInt( ((EditText) findViewById(R.id.txtNewHour)).getText().toString() );
        newMinute = Integer.parseInt( ((EditText) findViewById(R.id.txtNewMinute)).getText().toString() );
        newDay = Integer.parseInt( ((EditText) findViewById(R.id.txtNewDay)).getText().toString() );
        newMonth = Integer.parseInt( ((EditText) findViewById(R.id.txtNewMonth)).getText().toString() );
        newYear = Integer.parseInt( ((EditText) findViewById(R.id.txtNewYear)).getText().toString() );
        //
        EntryDate newDate = new EntryDate(newMonth,newDay,newYear,newHour,newMinute);
        //
        workingEntry.modifyMessage(newMessage);
        workingEntry.modifyDate(newDate);


        this.finish();
    }
    public void doDecline(View view) {
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_edit, menu);
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
