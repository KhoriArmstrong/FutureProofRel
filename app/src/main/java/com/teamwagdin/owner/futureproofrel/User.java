package com.teamwagdin.owner.futureproofrel;

/**
 * Created by Owner on 25/02/2015.
 */
public class User {


    public String myName = "";
    public User(String thisName) {
        myName = thisName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User other = (User) o;

            if (myName.equals(other.myName)) { return true; }
        }


        return false;
    }

}
