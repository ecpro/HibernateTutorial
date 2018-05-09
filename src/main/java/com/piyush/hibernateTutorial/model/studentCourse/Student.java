package com.piyush.hibernateTutorial.model.studentCourse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY) // default is eager fetch in one to one
    private Passport passport;

    /**
     * Below if we don't use @JoinTable then hibernate will automatically name the table as student_courses
     * Similarly for custom column name we needed @JoinColumn
     */
    @ManyToMany
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
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
