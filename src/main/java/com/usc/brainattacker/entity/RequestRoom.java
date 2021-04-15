package com.usc.brainattacker.entity;

public class RequestRoom {
    private int roomNumber;
    private String opponent;
    private Question[] questions = new Question[4];

    public RequestRoom(int roomNumber, String opponent){
        this.roomNumber = roomNumber;
        this.opponent = opponent;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
