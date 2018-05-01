package com.piyush.hibernateTutorial.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /**
     * Here we are storing passport number in student table as foreign key.
     * The other way around is also correct where we can store student id as foreign key in passport table.
     * In our case Student is owning the relationship.
     */

    @OneToOne(fetch = FetchType.LAZY) // default is eager fetch
    private Passport passport;

    public Student() {
    }

    public Student(String name) {
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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                '}';
    }
}
