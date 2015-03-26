package com.teamwagdin.owner.futureproofrel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;


public class HomePageActivity extends ActionBarActivity
{

    FutureProof theApp;

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        theApp = FutureProof.createInstance();
        //
        addListenerOnButton();


        ((TextView)findViewById(R.id.textView1)).setText("Welcome "+theApp.getCurrentUserAccount().getUsername());
    }

    public void addListenerOnButton()
    {

        final Context context = this;

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                theApp.shiftActivity(context, EntryTrackerActivity.class);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                theApp.shiftActivity(context, WorkLoginValidationActivity.class);
            }
        });
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
