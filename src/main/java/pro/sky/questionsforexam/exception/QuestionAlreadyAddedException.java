package pro.sky.questionsforexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.questionsforexam.model.Question;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionAlreadyAddedException extends RuntimeException{
        private final Question question;

        public QuestionAlreadyAddedException(Question question) {
            this.question = question;
        }

        public Question getQuestion() {
            return question;
        }

}
