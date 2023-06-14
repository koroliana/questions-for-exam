package pro.sky.questionsforexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.questionsforexam.repository.JavaQuestionRepository;

@SpringBootApplication
public class QuestionsForExamApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuestionsForExamApplication.class, args);
		JavaQuestionRepository.init();
	}

}
