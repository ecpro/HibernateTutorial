package com.piyush.hibernateTutorial;

import com.piyush.hibernateTutorial.repository.*;
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

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TouristRepository touristRepository;

	@Autowired
	GuideRepository guideRepository;

	@Override
	public void run(String... args) throws Exception {
		//studentRepository.saveStudentWithPassport(new Student("Daulatram"), new Passport("W3421"));
		//studentRepository.fetchStudentWithPassport(40001L);
		//studentRepository.lazyFetchStudentWithPassport(40001L);
		//courseRepository.addReviewsToCourse(10001L, new Review(5, "updated new review"));
		//studentRepository.addStudentAndCourse();
		//studentRepository.addStudentToACourse();
		//employeeRepository.addEmployee();

		//touristRepository.addGuideToTorist(205L);
		//guideRepository.findGuidesById(102L);
		//guideRepository.findGuidesById(102L);

		//touristRepository.addTouristAndGuideCorrectImplementation();
		touristRepository.addTouristAndGuideWithCascade();

	}
}
