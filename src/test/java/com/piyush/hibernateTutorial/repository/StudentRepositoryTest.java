package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    EntityManager em;

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
}