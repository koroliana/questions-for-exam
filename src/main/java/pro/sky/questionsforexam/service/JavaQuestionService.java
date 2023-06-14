package pro.sky.questionsforexam.service;

import org.springframework.stereotype.Service;
import pro.sky.questionsforexam.exception.QuestionAlreadyAddedException;
import pro.sky.questionsforexam.exception.QuestionNotFoundException;
import pro.sky.questionsforexam.model.Question;
import pro.sky.questionsforexam.repository.JavaQuestionRepository;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{

    JavaQuestionRepository javaQuestions = new JavaQuestionRepository();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }

    @Override
    public Question add(Question newQuestion) {
        for (Question wholeQuestion: javaQuestions.getAll()) {
            if (wholeQuestion.equals(newQuestion)) {
                throw new QuestionAlreadyAddedException(newQuestion);
            }
        }
        javaQuestions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(String question, String answer){
        Question removableQuestion = new Question(question, answer);
        return remove(removableQuestion);
    }

    @Override
    public Question remove(Question question) {
        if (javaQuestions.getAll().contains(question)) {
            javaQuestions.remove(question);
            return question;
        }
        else throw new QuestionNotFoundException(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestions.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> allQuestions = new ArrayList<>(javaQuestions.getAll());
        Random random = new Random();
        int temp = random.nextInt(allQuestions.size());
        return allQuestions.get(temp);
    }
}
