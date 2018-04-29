package com.piyush.hibernateTutorial.model;

import javax.persistence.*;


//@Table(name = "CourseDetails") //  required with you need to specify a different table name
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    //@Column(name = "full_name", nullable = false) // required for custom column name in db different than class field here
    // and other contraints in db colunt
    private String name;



    public Course() {
    }

    public Course(String name) {
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
