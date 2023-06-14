package pro.sky.questionsforexam.service;

import org.springframework.stereotype.Service;
import pro.sky.questionsforexam.exception.QuestionNumberExceededException;
import pro.sky.questionsforexam.model.Question;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final JavaQuestionService javaQuestionService;


    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(amount > javaQuestionService.getAll().size()) {
            throw new QuestionNumberExceededException();
        }
        Collection<Question> examineQuestions = new ArrayList<>();
        while(examineQuestions.size() < amount) {
            Question question = javaQuestionService.getRandomQuestion();
            if (!examineQuestions.contains(question)) {
                examineQuestions.add(question);
            }
        }
        return examineQuestions;
    }
}
