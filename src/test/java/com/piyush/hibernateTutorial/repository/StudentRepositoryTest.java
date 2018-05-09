package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.studentCourse.Course;
import com.piyush.hibernateTutorial.model.studentCourse.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    EntityManager em;

    @Autowired
    StudentRepository repository;

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void saveStudentWithPassport() {
    }

    @Test
    public void fetchStudentWithPassport() {
    }

    @Test @Transactional
    public void lazyFetchStudentWithPassport() {
        Student student = em.find(Student.class, 40001L);
        logger.info("student --> {}", student);
        logger.info("passport --> {}", student.getPassport());
    }

    @Test @Transactional // many to many lazy fetch by default so @Transactional used here
    public void fetchStudentWithACourse() {
        Course course = em.find(Course.class, 10001L);
        List<Student> students = course.getStudents();
        students.forEach(student -> System.out.println(student));
    }

    @Test
    public void addStudentToACourse() {
        repository.addStudentToACourse();
    }

    @Test
    public void addStudentAndCourse() {
        repository.addStudentAndCourse();
    }


}