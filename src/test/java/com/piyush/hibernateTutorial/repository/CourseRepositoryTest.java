package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.studentCourse.Course;
import com.piyush.hibernateTutorial.model.studentCourse.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

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

    @Autowired
    EntityManager em;

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

    @Test
    public void testJPQL_ByType() {
        String query = "select * from Course c";
        em.createQuery(query, Course.class).getResultList().forEach(result -> System.out.println(result));
    }

    @Test
    public void nativeQueryWithParameters() {
        String query = "select * from course where name =  :name";
        Query nativeQuery = em.createNativeQuery(query, Course.class);
        nativeQuery.setParameter("name", "Concurrency");
        nativeQuery.getResultList().forEach(course -> System.out.println(course));

    }

    @Test @Transactional
    public void addReviewsToCourse() {
        Review review = new Review(4, "Nice course - recent review");
        courseRepository.addReviewsToCourse(10001L, review);
        Course course = courseRepository.findById(10001L);
        ///System.out.println(course.getReviews());
        assertEquals(3, course.getReviews().size());
    }

    /**
     * Without @Transactional it will throw LazyInitializationException
     * as one to many by default is lazy fetched and we need transaction/persistanceContext to surview till we get reviews
     */
    @Test @Transactional
    public void fetchReviewsForACourse() {
        Course course = courseRepository.findById(10001L);
        List<Review> reviews = course.getReviews();
        reviews.forEach(review -> System.out.println(review));
    }

    /**
     * add attribute of eager fetch on reviews in course Entity before executing this method
     */
    @Test // no @Transactional required as Eager fetch is added to Reviews in Course Entity
    public void eagerFetchReviewForACourse() {
        Course course = courseRepository.findById(10001L);
        List<Review> reviews = course.getReviews();
        reviews.forEach(review -> System.out.println(review));
    }

    @Test // Try running the below method by changing the fetch type to lazy for course in Review entity: Will throw LazyInitializationException without @Transactional
    public void fetchCourseForAReview() {
        Review review = em.find(Review.class, 30002L);
        Course course = review.getCourse();
        System.out.println(course);
    }
}