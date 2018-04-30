package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.Passport;
import com.piyush.hibernateTutorial.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
    
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

}
