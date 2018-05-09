package com.piyush.hibernateTutorial.model.bookStore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

//@Entity
public class Book {

    @Id @GeneratedValue
    private String isbn;
    private String name;
    private Publisher publisher;
    private List<Chapter> chapterList;

    public Book() {
    }

    public Book(String isbn, String name, Publisher publisher) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", chapterList=" + chapterList +
                '}';
    }
}
