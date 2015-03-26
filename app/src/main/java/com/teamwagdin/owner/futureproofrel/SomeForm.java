package com.teamwagdin.owner.futureproofrel;

/**
 * Created by Owner on 25/02/2015.
 */
public class SomeForm {


    public User myUser = null;

    public SomeForm(User thisUser) {
        myUser = thisUser;
    }


    private boolean _filledOut = false;
    public void fillOut() {
        _filledOut = true;
    }

    public boolean isFilledOut() {
        return _filledOut;
    }
    // A form that is "filled out" becomes locked, such that you can't change any of its values without throwing an exception.
}
