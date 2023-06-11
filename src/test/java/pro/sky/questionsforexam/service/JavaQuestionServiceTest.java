package pro.sky.questionsforexam.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.questionsforexam.exception.QuestionAlreadyAddedException;
import pro.sky.questionsforexam.exception.QuestionNotFoundException;
import pro.sky.questionsforexam.model.Question;


import java.util.HashSet;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add("Перечисли принципы ООП","Наследование, инкапсуляция, полиморфизм, абстракция.");
        questionService.add("Что такое наследование?","Один класс может наследовать другой класс.");
        questionService.add("Что такое инкапсуляция?","Сокрытие реализации.");
        questionService.add("Что такое полиморфизм?","Способность идентично использовать объекты с одинаковым интерфейсом.");
    }

    @AfterEach
    public void afterEach() {
        new HashSet<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void addFirstTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("New question", "New answer");

        assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void addSecondTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("New question", "New answer");

        assertThat(questionService.add("New question", "New answer"))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void addFirstNegativeTest() {
        Question question = new Question("Что такое инкапсуляция?","Сокрытие реализации.");

        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void addSecondNegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add("Что такое инкапсуляция?","Сокрытие реализации."));
    }

    @Test
    public void removeFirstTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Что такое инкапсуляция?","Сокрытие реализации.");

        assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeSecondTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Что такое инкапсуляция?","Сокрытие реализации.");

        assertThat(questionService.remove("Что такое инкапсуляция?","Сокрытие реализации."))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeFirstNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Какие классы не наследуются от Object?","Все классы прямо или через предков наследуются от класса Object!")));
    }

    @Test
    public void removeSecondNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove("Какие классы не наследуются от Object?","Все классы прямо или через предков наследуются от класса Object!"));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrder(
                        new Question("Перечисли принципы ООП","Наследование, инкапсуляция, полиморфизм, абстракция."),
                        new Question("Что такое наследование?","Один класс может наследовать другой класс."),
                        new Question("Что такое инкапсуляция?","Сокрытие реализации."),
                        new Question("Что такое полиморфизм?","Способность идентично использовать объекты с одинаковым интерфейсом.")
                );
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }

}
