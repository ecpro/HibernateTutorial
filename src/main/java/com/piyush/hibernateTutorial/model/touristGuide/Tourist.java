package com.piyush.hibernateTutorial.model.touristGuide;

import javax.persistence.*;

@Entity
public class Tourist {

    @Id @GeneratedValue
    private Long id;
    private String name;

    /**
     * CascadeType.PERSIST will persist both tourist and guide. We don't need to separately call
     * persist method on guide and tourist. Just a call on tourist will persist guide also.
     * Similarly we can REMOVE both the records using CascadeType.REMOVE
     */
    @ManyToOne(cascade = CascadeType.PERSIST)

    private Guide guide; // many tourist can have one guide

    public Tourist() {
    }

    public Tourist(String name, Guide guide) {
        this.name = name;
        this.guide = guide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
