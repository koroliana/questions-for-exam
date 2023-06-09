package pro.sky.questionsforexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.questionsforexam.repository.JavaQuestionRepository;
import pro.sky.questionsforexam.service.JavaQuestionService;
import pro.sky.questionsforexam.service.QuestionService;

@SpringBootApplication
public class QuestionsForExamApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuestionsForExamApplication.class, args);
		JavaQuestionRepository.init();
		QuestionService questionService = new JavaQuestionService();
		System.out.println(questionService.getRandomQuestion());
		System.out.println(questionService.getRandomQuestion());
		System.out.println(questionService.getRandomQuestion());
		System.out.println(questionService.getRandomQuestion());
		System.out.println(questionService.getRandomQuestion());

	}

}
