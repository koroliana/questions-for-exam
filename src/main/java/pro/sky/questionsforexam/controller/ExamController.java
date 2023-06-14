package pro.sky.questionsforexam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.questionsforexam.exception.QuestionAmountInvalidException;
import pro.sky.questionsforexam.exception.QuestionNumberExceededException;
import pro.sky.questionsforexam.model.Question;
import pro.sky.questionsforexam.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    private boolean isValid(Integer a){
        return (a == null)||(a<=0);
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable Integer amount) {
        if (isValid(amount)) {
            throw new QuestionAmountInvalidException();
        }
        else {
            return examinerService.getQuestions(amount);
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionNumberExceededException.class)
    public String questionNumberExceededExceptionHandler(QuestionNumberExceededException e) {
        return "Запрошенное количество вопросов превышает доступный объем";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionAmountInvalidException.class)
    public String questionAmountInvalidExceptionHandler(QuestionAmountInvalidException e) {
        return "Введите требуемое количество вопросов в экзамене";
    }
}
