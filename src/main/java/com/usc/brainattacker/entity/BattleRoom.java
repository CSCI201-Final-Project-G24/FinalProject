package com.usc.brainattacker.entity;

import java.util.ArrayList;

public class BattleRoom {
    
    private boolean valid;
    private static int roomSize = 2;
    private ArrayList<User> userList = new ArrayList<User>();

    public boolean stillValid(){
        return valid;
    }

    public BattleRoom(User user){
        userList.add(user);
        valid = userList.size() < roomSize;
    }

    public void addUser(User user){
        if(this.valid){
            userList.add(user);
            valid = userList.size() < roomSize;
        }
    }
}
