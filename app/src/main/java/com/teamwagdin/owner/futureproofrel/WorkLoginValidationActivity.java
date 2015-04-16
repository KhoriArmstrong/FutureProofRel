package com.teamwagdin.owner.futureproofrel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;




public class WorkLoginValidationActivity extends Activity {

    FutureProof theApp;


    private EditText userEditText;
    private EditText passEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_login_validation);

        userEditText = (EditText) findViewById(R.id.txtUsername);
        passEditText = (EditText) findViewById(R.id.txtPassword);


        theApp = FutureProof.createInstance();
        //


        theApp.c = this;
        //
        theApp.getModelHandle().initializeMe(theApp.c);
        // ??? <-- A quick fix. The application should actually START on a "welcome" screen, then perform "shiftActivity" into THIS screen, making this unnecessary.

        //
        theApp.logout();
    }


    public void doRegister(View view) {
        String user = userEditText.getText().toString();
        String pass = passEditText.getText().toString();
        //
        if (theApp.retrieveAccount(user) != null) {
            userEditText.setError("User account already exists.");
            return;
        }
        //
        theApp.createNewAccount(user);
    }

    public void doLogin(View view) {
        String user = userEditText.getText().toString();
        String pass = passEditText.getText().toString();
        //
        SomeAccount sa = theApp.retrieveAccount(user);
        if (sa == null) {
            userEditText.setError("User account not found.");
            return;
        }
        //
        theApp.login(sa);
        if (theApp.isLoggedIn()) {
            theApp.shiftActivity(this, HomePageActivity.class);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_work_login_validation, menu);
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
