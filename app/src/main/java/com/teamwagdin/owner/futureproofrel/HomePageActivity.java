package com.teamwagdin.owner.futureproofrel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class HomePageActivity extends ActionBarActivity
{

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        addListenerOnButton();
    }

    public void addListenerOnButton()
    {

        final Context context = this;

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, EntryTrackerActivity.class);
                startActivity(intent);

            }

        });

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent1 = new Intent(context, LoginValidationTest.class);
                startActivity(intent1);
            }

        });

        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent2 = new Intent(context, EntryAlarm.class);
                startActivity(intent2);
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
