package com.piyush.hibernateTutorial;

import com.piyush.hibernateTutorial.model.Course;
import com.piyush.hibernateTutorial.model.Passport;
import com.piyush.hibernateTutorial.model.Student;
import com.piyush.hibernateTutorial.repository.CourseRepository;
import com.piyush.hibernateTutorial.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateTutorialApplication implements CommandLineRunner {
	// TODO: search for usage of CommandLineRunner interface online
	public static void main(String[] args) {
		SpringApplication.run(HibernateTutorialApplication.class, args);
	}

	Logger logger = LoggerFactory.getLogger(HibernateTutorialApplication.class);

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		//studentRepository.saveStudentWithPassport(new Student("Daulatram"), new Passport("W3421"));
		studentRepository.fetchStudentWithPassport(40001L);
	}
}
