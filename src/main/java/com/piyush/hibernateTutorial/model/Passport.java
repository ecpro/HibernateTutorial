package com.piyush.hibernateTutorial.model;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number")
    private String passportNumber;

    // student table is the owning side of relationship as it has passport_id as foreign key. Hence mappedby used here.
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport") // mapped by passport field in Student class.
    private Student student;

    public Passport() {

    }

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }


}
