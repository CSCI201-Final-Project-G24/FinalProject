package com.usc.brainattacker.entity;

import java.util.ArrayList;

public class BattleRoom extends Thread{
    //Private player class takes name, and has: addScore(), getScore(), getName(), finishedGame();
    private class player{
        int score;
        String name;
        boolean isFinished;
        private player(String name){
            this.score = 0;
            this.name = name;
            this.isFinished = false;
        }
        void addScore(){
            this.score += 1;
        }
        int getScore(){
            return this.score;
        };
        String getName() {
            return this.name;
        }
        /*boolean finishedGame() {
            return this.isFinished;
        }*/
        ;
    }
    //超时handle一下
    private int roomNumber;
    private boolean valid;
    private static int roomSize = 2;
    private int userOneScore, userTwoScore;
    private ArrayList<String> userList = new ArrayList<String>();
    private ArrayList<player> playerList = new ArrayList<player>();
    private player p1, p2;
    public boolean stillValid(){
        return valid;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public BattleRoom(String username, int roomNumber){
        userList.add(username);
        this.playerList.add(new player(username));
        this.roomNumber = roomNumber;
        valid = userList.size() < roomSize;
    }

    public BattleRoom(String u1, String u2, int roomNumber){
        userList.add(u1);
        userList.add(u2);
        this.roomNumber = roomNumber;
        this.p1 = new player(u1);
        this.p2 = new player(u2);
        playerList.add(this.p2);
        playerList.add(this.p1);


    }

    public void addUser(String username){
        if(this.valid) {
            userList.add(username);
            this.playerList.add(new player(username));
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

    public boolean gameComplete(){
        boolean temp = true;
        for (int i = 0; i < playerList.size(); ++i) temp = temp && playerList.get(i).isFinished;

        //if true: update
        return temp;
    }

    public int findOpponentScore(String username){
        int score;
        if (username == this.p1.getName())score = this.p2.getScore();
        else score = this.p1.getScore();
        return score;
    }

    public void run(){
        while(!gameComplete()){

        }
    }

    public String getP1Name(){ return this.p1.getName(); }
    public String getP2Name(){return this.p2.getName();}
    public boolean getP1Condition(){return this.p1.isFinished;}
    public boolean getP2Condition(){return this.p2.isFinished;}

}
