package com.piyush.hibernateTutorial.model.touristGuide;

import javax.persistence.*;
import java.util.List;

@Entity
public class Guide {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "guide", fetch = FetchType.EAGER)
    private List<Tourist> tourists;

    public Guide() {
    }

    public Guide(String name) {
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

    public List<Tourist> getTourists() {
        return tourists;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
