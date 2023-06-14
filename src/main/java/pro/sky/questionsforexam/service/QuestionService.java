package pro.sky.questionsforexam.service;

import pro.sky.questionsforexam.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(String question, String answer);
    Question remove(Question guestion);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
