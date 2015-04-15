package com.teamwagdin.owner.futureproofrel;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 09/04/2015.
 */
public class FPModel {

    private static FPModel _fpmodel;
    public static FPModel createInstance() {
        if (_fpmodel == null) {
            _fpmodel = new FPModel();
        }

        return _fpmodel;
    }

    private FPModel() { }


    private static final String applicationKey = "i7a29dPzIFJ226QiqswuZsjccEDSbOsq5t6MZ7pJ";
    private static final String clientKey = "BF2SuduN8BOqsgTrLHvJ0coT2hsa50wxlzf8mWAd";

    private boolean hasInited = false;
    public void initializeMe(Context thisContext) {
        if (!hasInited) {
            // Enable Local Datastore.
            Parse.enableLocalDatastore(thisContext);

            Parse.initialize(thisContext, applicationKey, clientKey);


            hasInited = true;
        }
    }

    public void testMe() {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }


    public void dataEntryAdd(User thisUser, Entry thisEntry) throws ParseException {
        ParseObject testObject = new ParseObject("Entry");
        //
        testObject.put("userID", thisUser.myName);
        testObject.put("createDate", thisEntry.createDate.toParsableString());
        testObject.put("targetDate", thisEntry.createDate.toParsableString());
        testObject.put("message", thisEntry.message.toString());
        //
        testObject.save();
    }

    public ArrayList<Entry> dataEntryGetAll(User thisUser) throws ParseException {
        ArrayList<Entry> theList = new ArrayList<Entry>();
        ParseQuery<ParseObject> theQuery = ParseQuery.getQuery("Entry");
        //
        Entry thisEntry;
        String thisName;
        for (ParseObject anEntry : theQuery.find()) {
            thisName = anEntry.getString("userID");
            //
            if (thisName.equals(thisUser.myName)) {
                thisEntry = new Entry(EntryDate.parse(anEntry.getString("createDate")), EntryDate.parse(anEntry.getString("targetDate")), anEntry.getString("message"));
                //
                theList.add(thisEntry);
            }
        }
        //
        return theList;
    }
}
