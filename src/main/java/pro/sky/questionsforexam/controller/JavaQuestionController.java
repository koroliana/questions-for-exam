package pro.sky.questionsforexam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.questionsforexam.exception.BlankQuestionOrAnswerException;
import pro.sky.questionsforexam.exception.QuestionAlreadyAddedException;
import pro.sky.questionsforexam.exception.QuestionNotFoundException;
import pro.sky.questionsforexam.model.Question;
import pro.sky.questionsforexam.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    private boolean isNull(String a, String b){
        return a == null || b == null;
    }

    @GetMapping ()
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam(required = false) String question, @RequestParam(required = false) String answer) {
        if (isNull(question, answer)) {
            throw new BlankQuestionOrAnswerException();
        }
        else {
            return questionService.add(question, answer);
        }
    }
    @GetMapping (path = "/remove")
    public Question removeQuestion(@RequestParam(required = false) String question, @RequestParam(required = false) String answer) {
        if (isNull(question, answer)) {
            throw new BlankQuestionOrAnswerException();
        }
        else {
            return questionService.remove(question, answer);
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(QuestionNotFoundException.class)
    public String employeeNotFoundExceptionHandler(QuestionNotFoundException e) {
        return "Не найден \"" + e.getQuestion() + "\"";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionAlreadyAddedException.class)
    public String employeeAlreadyAddedExceptionHandler(QuestionAlreadyAddedException e) {
        return "Уже добавлен \""+ e.getQuestion() + "\"";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BlankQuestionOrAnswerException.class)
    public String blankQuestionOrAnswerExceptionHandler(BlankQuestionOrAnswerException e) {
        return "Добавьте вопрос и ответ";
    }


}
