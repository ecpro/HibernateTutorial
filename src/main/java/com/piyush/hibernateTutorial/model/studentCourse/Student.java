package com.piyush.hibernateTutorial.model.studentCourse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "nickname", joinColumns = @JoinColumn(name = "student_id"))
    //@AttributeOverrides()
    @Column(name = "nick_name")
    private Set<String> nickNames = new HashSet<>(); // we don't have any entity class for nickname as
    // nickNames is of value type (important). However a table is still created as its a set which can have multiple values.
    // If nickNames was of Entity type then it would be annotated with @OneToMany which is usually
    // what we do while mapping tables.

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

    public Set<String> getNickNames() {
        return nickNames;
    }

    public void addNickName(String nickName) {
        this.nickNames.add(nickName);
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
