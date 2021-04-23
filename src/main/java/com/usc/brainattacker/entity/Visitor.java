package com.usc.brainattacker.entity;

public class Visitor {
    private int token;
    private String username;

    public Visitor(int token, String username){
        this.token =token;
        this.username = username;
    }

    public int getToken() {
        return token;
    }

    public String getVisitorName() {
        return username;
    }
}
