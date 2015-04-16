package com.teamwagdin.owner.futureproofrel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class HomePageActivity extends ActionBarActivity
{

    FutureProof theApp;
    FPModel theModel;

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        theApp = FutureProof.createInstance();
        theModel = theApp.getModelHandle();


        ((TextView)findViewById(R.id.textView1)).setText("Welcome "+theApp.getCurrentUserAccount().getUsername());
    }

    public void doTracker(View view) {
        theApp.shiftActivity(this, EntryTrackerActivity.class);
    }

    public void doLogOut(View view) {
        theApp.shiftActivity(this, WorkLoginValidationActivity.class);
    }


    //@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
