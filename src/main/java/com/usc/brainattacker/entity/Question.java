package com.usc.brainattacker.entity;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionStem;
    private String answer;
    private int answerIndx;
    private List<String> questionOption= new ArrayList<>();

    public Question(String stem, String answer, String option1,String option2, String option3, String option4){
        questionStem=stem;
        questionOption.add(option1);
        questionOption.add(option2);
        questionOption.add(option3);
        questionOption.add(option4);
    }
    public Question(){}

    //set answer for this question
    public void setAnswer(String answer){ this.answer=answer;}

    //return answer string
    public String getAnswer(){ return answer;}

    //return answer index
    public int getAnswerIndx(){return answerIndx;}

    //add an option to the option list
    public void addOption(String option){  questionOption.add(option); }

    //set question id by placing question at specific index
    public void setAnswerID(int id){
        answerIndx=id;
        for(int i=questionOption.size()-1; i>id+1; i--){
            questionOption.set(i, questionOption.get(i-1));
        }
        questionOption.set(id, answer);
    }
}
