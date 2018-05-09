package com.piyush.hibernateTutorial.model.touristGuide;

import javax.persistence.*;

@Entity
public class Tourist {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING) // default is EnumType.ORDINAL which stores index value enum type
    @Column(name = "tourist_type")
    private TouristTypeEnum touristType;

    /**
     * CascadeType.PERSIST will persist both tourist and guide. We don't need to separately call
     * persist method on guide and tourist. Just a call on tourist will persist guide also.
     * Similarly we can REMOVE both the records using CascadeType.REMOVE
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Guide guide; // many tourist can have one guide

    public Tourist() {
    }

    public Tourist(String name, Guide guide, TouristTypeEnum touristType) {
        this.name = name;
        this.guide = guide;
        this.touristType = touristType;
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

    public TouristTypeEnum getTouristType() {
        return touristType;
    }

    public void setTouristType(TouristTypeEnum touristType) {
        this.touristType = touristType;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", touristType=" + touristType +
                ", guide=" + guide +
                '}';
    }
}
