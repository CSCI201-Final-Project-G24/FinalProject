package com.usc.brainattacker.entity;

import java.io.Serializable;

public class RequestRoom {
    public int roomNumber;
    public String opponent;
    public Question[] questions;

    public RequestRoom(int roomNumber, String opponent, Question[] questions){
        this.roomNumber = roomNumber;
        this.opponent = opponent;
        this.questions = questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
