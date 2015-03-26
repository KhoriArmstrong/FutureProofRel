package com.teamwagdin.owner.futureproofrel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class WorkLoginValidationActivity extends ActionBarActivity {


    FutureProof theApp;
    EditText etUser;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_login_validation);

        theApp = FutureProof.createInstance();
        etUser = (EditText)findViewById(R.id.txtUsername);
        etPass = (EditText)findViewById(R.id.txtPassword);




    }

    SomeAccount sa;
    public void registerButton(View v) {
        sa = theApp.createNewAccount(etUser.getText().toString());


        Toast.makeText(this, "New account: "+sa.getUsername(), Toast.LENGTH_SHORT).show();
    }
    public void loginButton(View v) {
        try {
            theApp.login(sa);
        }
        catch (Exception e) {}


        if (theApp.isLoggedIn()) {
            Toast.makeText(this, "User is logged in", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User DIDN'T LOG IN!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void runButton(View v) {

        theApp.Run();

        if (theApp.isRunning()) {
            Toast.makeText(this, "App is running", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "App ISN'T running RIGHT NOW", Toast.LENGTH_SHORT).show();
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
