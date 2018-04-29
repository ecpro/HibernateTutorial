package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    private EntityManager entityManager;

    public Course findById(Long id) {
        Course course = entityManager.find(Course.class, id);
        return course;
    }

    public Course saveOrUpdate(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        final Course course = this.findById(id);
        entityManager.remove(course);
    }

}
