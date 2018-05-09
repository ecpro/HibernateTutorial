package com.piyush.hibernateTutorial.model.employee;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    public BigDecimal getHourlyWage() {
        return salary;
    }

    public void setHourlyWage(BigDecimal salary) {
        this.salary = salary;
    }

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
