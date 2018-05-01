package com.piyush.hibernateTutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


//@Table(name = "CourseDetails") //  required when you need to specify a different table name
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    //@Column(name = "full_name", nullable = false) // required for custom column name in db different than class field here
    // and other constraints in db table column
    private String name;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "last_updated_date")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;


    /**
     * One course can have many reviews. Hence oneToMany relationship here.
     * Course's id is foreign key in review table.
     */

    @OneToMany(mappedBy = "course") // course is defined in Review Entity
    private List<Review> reviews; // by default one to many is Lazy fetched as opposed to one to one which is eager fetch

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        if(review != null) this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
