package pro.sky.questionsforexam.service;

import pro.sky.questionsforexam.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
