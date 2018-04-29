package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
//@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
/**
 * DirtiesContext annotation which indicates that the ApplicationContext associated with a test is dirty and should be closed:
 *
 * after the current test, when declared at the method level
 * after each test method in the current test class, when declared at the class level with class mode set to AFTER_EACH_TEST_METHOD
 * after the current test class, when declared at the class level with class mode set to AFTER_CLASS
 * Use this annotation if a test has modified the context (for example, by replacing a bean definition). Subsequent tests will be supplied a new context.
 *
 */
public class CourseRepositoryTest {

    Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findById() {
        Course course = courseRepository.findById(10001L);
        assertEquals("Data Structures", course.getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void saveOrUpdateForSave() {
        Course data_fustrucres = new Course("Data Fustrucres");;
        courseRepository.saveOrUpdate(data_fustrucres);
        logger.info("{}", data_fustrucres);
        assertNotNull(courseRepository.findById(data_fustrucres.getId()));


    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public  void saveOrUpdateForUpdate() {
        Course data_fustrucres = new Course("Data Frusruts");
        data_fustrucres.setId(10001L);
        courseRepository.saveOrUpdate(data_fustrucres);
        assertNotNull(courseRepository.findById(10001L));

    }

    @Test
    @DirtiesContext(methodMode=DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteById() {
        courseRepository.deleteById(10002L);
        final Course course = courseRepository.findById(10002L);
        assertNull("Course should be null",course);
    }

}