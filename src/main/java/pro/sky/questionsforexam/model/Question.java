package pro.sky.questionsforexam.model;

import java.util.Objects;

public class Question {
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return  "Вопрос: " + question + "\n" + "Ответ: " + answer ;
    }
    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Question otherQuestion = (Question) other;
        return (question.equals(otherQuestion.getQuestion())&&answer.equals(otherQuestion.getAnswer()));
    }
    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }



}
