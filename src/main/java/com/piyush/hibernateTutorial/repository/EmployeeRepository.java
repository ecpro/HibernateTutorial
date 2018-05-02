package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.Employee;
import com.piyush.hibernateTutorial.model.FullTimeEmployee;
import com.piyush.hibernateTutorial.model.PartTimeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    EntityManager em;

    public void addEmployee() {
        Employee em1 = new FullTimeEmployee("ABC", new BigDecimal(10000));
        Employee em2 = new PartTimeEmployee("PQR", new BigDecimal(20));
        em.persist(em1);
        em.persist(em2);
    }
}
