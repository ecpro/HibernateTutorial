package com.piyush.hibernateTutorial.model;

import javax.persistence.*;

//@MappedSuperclass // Read about it on http://www.baeldung.com/hibernate-inheritance
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "EMPLOYEE_TYPE") // THIS COLUMN WILL TELL WHETHER PART TIME OR FULL TIME
public abstract class Employee {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
