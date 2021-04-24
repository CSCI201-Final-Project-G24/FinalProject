package com.usc.brainattacker.service.impl;
import com.usc.brainattacker.entity.Question;
import com.usc.brainattacker.mapper.QuestionMapper;
import com.usc.brainattacker.mapper.UserMapper;
import com.usc.brainattacker.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public int[] getRandom(int upperLimit){ // get four different random number for pid
        ArrayList<Integer> randList = new ArrayList<>();
        int[] randomIndex = new int[4];
        for(int i = 0; i < upperLimit; i++){
            randList.add(i,i+1);
        }
        Random rand = new Random();
        for(int i = 0; i < 4; i++){
            int index = rand.nextInt(upperLimit - i);
            randomIndex[i] = randList.get(index);
            randList.remove(index);
        }return randomIndex;
    }

    @Override
    public Question getQuestion(int qid, int index) {
        String question = questionMapper.getQuestion(qid);
        int answerCid = questionMapper.getAnswerIndex(qid);
        String answer = questionMapper.getAnswer(answerCid);
        int answerIndex = 0;
        List<String> choices = questionMapper.getChoice(qid);
        for(int i = 0; i < 4; i ++){
            if (choices.get(i).equals(answer)){
                answerIndex = i;
                break;
            }
        }
        return new Question(index,question,answerIndex,choices);
    }

    @Override
    public Question[] returnStruct() {
        int[] randomIndex = getRandom(10);
        Question[] questions  = new Question[4];
        for(int i = 0; i < 4; i ++) {
            questions[i] = getQuestion(randomIndex[i],i+1);
        }return questions;
    }
}
