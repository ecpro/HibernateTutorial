package com.piyush.hibernateTutorial;

import com.piyush.hibernateTutorial.model.Course;
import com.piyush.hibernateTutorial.repository.CourseRepository;
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

	@Override
	public void run(String... args) throws Exception {
		courseRepository.entityManagerFlushAndDetach();
	}
}
