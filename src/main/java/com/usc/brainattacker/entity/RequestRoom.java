package com.usc.brainattacker.entity;

import java.io.Serializable;

public class RequestRoom {
    public int roomNumber;
    public String opponent;
    public Question[] questions = new Question[4];

    public RequestRoom(int roomNumber, String opponent){
        this.roomNumber = roomNumber;
        this.opponent = opponent;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
