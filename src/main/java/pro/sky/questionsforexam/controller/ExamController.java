package pro.sky.questionsforexam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.questionsforexam.exception.QuestionNumberExceededException;
import pro.sky.questionsforexam.model.Question;
import pro.sky.questionsforexam.service.ExaminerService;
import pro.sky.questionsforexam.service.ExaminerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("")
public class ExamController {

    private final ExaminerService examinerService = new ExaminerServiceImpl();

    /*
    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

     */

    private boolean isNull(Integer a){
        return a == null;
    }

    @GetMapping()
    public String getQuestions(@RequestParam(required = false) Integer amount) {
        if (isNull(amount)) {
            return "Введите требуемое количество вопросов в экзамене";
        }
        else {
            Collection<Question> examQuestions = examinerService.getQuestions(amount);
            StringBuilder examQuestionsString = new StringBuilder();
            for (Question question: examQuestions){
                examQuestionsString.append("\n").append(question.toString());
            }
            return "Ваши вопросы к экзамену: \n" + "вот: \n" + examQuestionsString;
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionNumberExceededException.class)
    public String questionNumberExceededExceptionHandler(QuestionNumberExceededException e) {
        return "Запрошенное количество вопросов превышает доступный объем";
    }
}
