package com.teamwagdin.owner.futureproofrel;

/**
 * Created by Owner on 25/02/2015.
 */
public class LoginValidation {
    User thisGuy;

    public void addUser() {
        thisGuy = new User("James");
    }

    public User findUser() {
        return thisGuy;
    }

    public boolean passwordMatches(User thisUser) {
        if (thisUser == null) { return false; }

        return true;
    }

}
