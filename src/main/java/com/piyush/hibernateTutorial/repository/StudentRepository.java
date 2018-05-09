package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.studentCourse.Course;
import com.piyush.hibernateTutorial.model.studentCourse.Passport;
import com.piyush.hibernateTutorial.model.studentCourse.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StudentRepository {

    Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    @Autowired
    private EntityManager entityManager;

    public Student findById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    public void deleteById(Long id) {
        final Student student = this.findById(id);
        entityManager.remove(student);
    }

    public Student saveOrUpdate(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    // unidirectional one to one mapping example
    public void saveStudentWithPassport(Student student, Passport passport) {
        entityManager.persist(passport);
        student.setPassport(passport);
        entityManager.persist(student);
    }

    // fetch with one to one is by default eager fetch
    public Student fetchStudentWithPassport(Long id) {
        Student student = entityManager.find(Student.class, id);
        // here we haven't fetched the passport details. Still it gets fetched.
        // hibernate by default used left outer join on passport and populated the student
        // with passport. This is called eager fetch.
        logger.info("Student details with passport {}", student);
        return student;
    }

    public Student lazyFetchStudentWithPassport(Long id) {
        Student student = entityManager.find(Student.class, id);
        logger.info("student --> {} ", student);
        logger.info("student passport --> {}", student.getPassport());
        return student;
    }

    public void addStudentToACourse() {
        Course course = entityManager.find(Course.class, 10004L);
        Student student = entityManager.find(Student.class, 40003L);
        course.addStudent(student);
        student.addCourse(course);
        //entityManager.persist(student);
        course.getStudents().forEach(student1 -> System.out.println(student1));
    }

    public void addStudentAndCourse() {
        Course course = new Course("Bakloli 101");
        Student student = new Student("Chatur");

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
    }

}
