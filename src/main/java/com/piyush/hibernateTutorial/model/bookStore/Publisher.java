package com.piyush.hibernateTutorial.model.bookStore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Publisher {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String code;

    public Publisher() {
    }

    public Publisher(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
