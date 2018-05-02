package com.piyush.hibernateTutorial.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    public BigDecimal getSalary() {
        return hourlyWage;
    }

    public void setSalary(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
