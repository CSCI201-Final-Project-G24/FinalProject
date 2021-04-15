package com.usc.brainattacker.entity;

import java.util.ArrayList;

public class BattleRoom {
    
    private int roomNumber;
    private boolean valid;
    private static int roomSize = 2;
    private ArrayList<String> userList = new ArrayList<String>();

    public boolean stillValid(){
        return valid;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public BattleRoom(String username, int roomNumber){
        userList.add(username);
        this.roomNumber = roomNumber;
        valid = userList.size() < roomSize;
    }

    public void addUser(String username){
        if(this.valid){
            userList.add(username);
            valid = userList.size() < roomSize;
        }
    }

    public String findOpponent(String username){
        if(this.valid) return "false";
        else{
            if(userList.get(0) == username) return userList.get(1);
            else return userList.get(0); // is whether 1 or 0
        }
    }
}
