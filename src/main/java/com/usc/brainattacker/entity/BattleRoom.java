package com.usc.brainattacker.entity;

import java.util.ArrayList;

public class BattleRoom {
    
    private int roomNumber;
    private boolean valid;
    private static int roomSize = 2;
    private ArrayList<User> userList = new ArrayList<User>();

    public boolean stillValid(){
        return valid;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public BattleRoom(User user, int roomNumber){
        userList.add(user);
        this.roomNumber = roomNumber;
        valid = userList.size() < roomSize;
    }

    public void addUser(User user){
        if(this.valid){
            userList.add(user);
            valid = userList.size() < roomSize;
        }
    }
}
