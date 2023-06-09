package pro.sky.questionsforexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.questionsforexam.model.Question;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException {
        private final Question question;

    public QuestionNotFoundException(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }


}
