package pro.sky.questionsforexam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.questionsforexam.exception.QuestionNumberExceededException;
import pro.sky.questionsforexam.model.Question;
import pro.sky.questionsforexam.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    private boolean isValid(Integer a){
        return (a == null)||(a<=0);
    }

    @GetMapping()
    public String getQuestions(@RequestParam(required = false) Integer amount) {
        if (isValid(amount)) {
            return "Введите требуемое количество вопросов в экзамене";
        }
        else {
            Collection<Question> examQuestions = examinerService.getQuestions(amount);
            StringBuilder examQuestionsString = new StringBuilder();
            int counter = amount-1;
            for (Question question: examQuestions){
                int id = amount-counter;
                examQuestionsString.append(" "+ id + ") ").append(question.toString());
                counter--;
            }
            return "Ваши вопросы к экзамену: \n" + examQuestionsString;
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionNumberExceededException.class)
    public String questionNumberExceededExceptionHandler(QuestionNumberExceededException e) {
        return "Запрошенное количество вопросов превышает доступный объем";
    }
}
