package pro.sky.questionsforexam.service;

import org.springframework.stereotype.Service;
import pro.sky.questionsforexam.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService{

    Set<Question> javaQuestions = new HashSet<>();
    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        javaQuestions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        return null;
    }

    @Override
    public Question remove(Question guestion) {
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return null;
    }

    @Override
    public Question getRandomQuestion() {
        return null;
    }
}
