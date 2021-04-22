package com.usc.brainattacker.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Select("SELECT question from question where qid = #{qid}")
    String getQuestion(int qid);

    @Select("SELECT choice from choice where qid = #{qid}")
    List<String> getChoice(int qid);

    @Select("Select correct from question where qid = #{qid}")
    int getAnswerIndex(int qid);

    @Select("SELECT choice from choice where cid = #{cid}")
    String getAnswer(int cid);

}
