package com.teamwagdin.owner.futureproofrel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WorkLoginValidationActivity extends Activity {

    FutureProof theApp;


    private EditText emailEditText;
    private EditText passEditText;
    private Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_login_validation);

        emailEditText = (EditText) findViewById(R.id.txtEmail);
        passEditText = (EditText) findViewById(R.id.txtPassword);
        loginBt = (Button) findViewById(R.id.button);



    }


    public void loginButton(View view) {

            String email = emailEditText.getText().toString();
            String pass = passEditText.getText().toString();



            if (!isValidEmail(email)) {
                emailEditText.setError("Invalid Email");
            }


            else if (!isValidPassword(pass)) {
                passEditText.setError("Invalid Password");
            }

            else {

                theApp.shiftActivity(this ,HomePageActivity.class);
            }


    }


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
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
