package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.studentCourse.Course;
import com.piyush.hibernateTutorial.model.studentCourse.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    Logger logger = LoggerFactory.getLogger(CourseRepository.class);

    @Autowired
    private EntityManager entityManager;

    public Course findById(Long id) {
        Course course = entityManager.find(Course.class, id);
        return course;
    }

    public void deleteById(Long id) {
        final Course course = this.findById(id);
        entityManager.remove(course);
    }

    public Course saveOrUpdate(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        //TODO: Find difference between merge() and persists().
        return course;
    }

    public void playWithEntityManager() {

        Course arithmatic = new Course("Arithmatic");
        entityManager.persist(arithmatic);

        /** Below we haven't called persists() method. Still it will get persisted
           because the method playWithEntityManager() is in transaction and entity manager keep tracks
           of state changes of the object it has modified and it will persist the change
           even if persists() method is not called the second time. */

        arithmatic.setName("Algebra"); // this will also be persisted

    }

    // --------  Uses of flush() and detach() method -------------
    // In below example entityManager.merge(course1) was throwing and
    // exception when persists() used instead of merge(). For persists() id cannot be provided from outside

    public void entityManagerFlushAndDetach() {
        Course course1 = new Course("Arithmetic 101");
        entityManager.persist(course1);

        Course course2 = new Course("Geometry 101");
        entityManager.persist(course2);

        entityManager.flush(); // will force hibernate to send the current state to db.
        // If not used then it will throw exception (Error during managed flush )
        // as we are detaching course 2 below so data must be flushed to db before detach operation

        // Now we don't want course2 be tracked by entity manager. In this case we will detach course2 and
        // entity manager won't track it anymore.

        entityManager.detach(course2);

        // entityManager.clear(); // this will detach every object being tracked by entity manager

        course1.setName("Arithmetic 101 - updated"); // this will also be updated even if persists/merge not called

        course2.setName("Geometry 101 - updated"); // this will not be persisted as it has been detached

    }

    /**
        A call to EntityManager.flush() will force the data to be persist
        in the database immediately as EntityManager.persist() will not (
        depending on how the EntityManager is configured : FlushModeType (AUTO or COMMIT)
        by default it's set to AUTO and a flush will be done automatically by if it's set to COMMIT
        the persitence of the data to the underlying database will be delayed when the transaction is commited).
     */

    public void entityManagerRefresh() {
        Course course1 = new Course("Arithmetic 101");
        entityManager.persist(course1);

        Course course2 = new Course("Geometry 101");
        entityManager.persist(course2);

        entityManager.flush(); // saved above data to db

        course1.setName("Arithmetic 101 - updated");
        course2.setName("Geometry 101 - updated");

        entityManager.refresh(course1); // a select query is fired and course1 is
        // update by values returned by that query

        entityManager.flush();

    }

    public void addReviewsToCourse(Long courseId, Review review) {

        //1. find the course for which reviews are to be added.
        Course course = this.findById(courseId);

        //2. add reviews
        review.setCourse(course);
        entityManager.persist(review);

    }


}
