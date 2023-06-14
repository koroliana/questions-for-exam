package pro.sky.questionsforexam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.questionsforexam.exception.QuestionNumberExceededException;
import pro.sky.questionsforexam.model.Question;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Collection<Question> questions = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        questions.clear();
        questions =  List.of(
                new Question("Перечисли принципы ООП","Наследование, инкапсуляция, полиморфизм, абстракция."),
                new Question("Что такое наследование?","Один класс может наследовать другой класс."),
                new Question("Что такое инкапсуляция?","Сокрытие реализации."),
                new Question("Что такое полиморфизм?","Способность идентично использовать объекты с одинаковым интерфейсом.")
        );

        when(javaQuestionService.getAll()).thenReturn(questions);

    }


    @Test
    public void getQuestionsTest() {
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("Что такое наследование?","Один класс может наследовать другой класс."),
                new Question("Что такое инкапсуляция?","Сокрытие реализации."),
                new Question("Что такое полиморфизм?","Способность идентично использовать объекты с одинаковым интерфейсом.")
        );

        assertThat(examinerService.getQuestions(2))
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        new Question("Что такое наследование?","Один класс может наследовать другой класс."),
                        new Question("Что такое инкапсуляция?","Сокрытие реализации.")
                );
    }

    @Test
    public void getQuestionsNegativeTest() {
        when(javaQuestionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(QuestionNumberExceededException.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
    }

}
