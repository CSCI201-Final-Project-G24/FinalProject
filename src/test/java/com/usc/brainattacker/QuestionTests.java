package com.usc.brainattacker;

import com.usc.brainattacker.service.QuestionService;

public class QuestionTests {
    QuestionService questionService;

    void testRand(){
        int[] rands = questionService.getRandom(10);
        for(int i = 0; i < 4; i++){
            System.out.println(rands[i]);
        }
    }
}
