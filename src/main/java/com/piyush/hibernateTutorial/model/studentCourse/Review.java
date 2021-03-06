package com.piyush.hibernateTutorial.model.studentCourse;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private int rating;

    private String description;

    /**
     * Many review can be there for a course. Hence course's id is foreign key in Review table
     */

    @ManyToOne

    private Course course;

    public Review(int rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Review() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", course=" + course +
                '}';
    }
}
