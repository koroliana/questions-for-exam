package pro.sky.questionsforexam.repository;

import pro.sky.questionsforexam.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();


}
