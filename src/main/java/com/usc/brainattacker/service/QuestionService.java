package com.usc.brainattacker.service;

import com.usc.brainattacker.entity.Question;

public interface QuestionService {
    int[] getRandom(int upperLimit);
    Question getQuestion(int qid, int index);
    Question[] returnStruct();
}
