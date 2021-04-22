package com.usc.brainattacker.entity;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private int index;
    private String question;
    private int answer;
    private List<String> options;

    public Question(int index, String stem, int answerIndex, List<String> choices){
        this.index = index;
        question=stem;
        this.options = choices;
        this.answer = answerIndex;
    }
    public Question(){}

    public void setIndex(int index){
        this.index = index;
    }

    //return answer string
    public int getAnswer(){ return answer;}

    public int getIndex(){return index;}

    public List<String>getOptions(){return options;}

    public String getQuestion(){return question;}

}
