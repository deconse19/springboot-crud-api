package springboot.crud.practice.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;
    private LocalDate datePublished;


    
    public Book() {
    }

    public Book(Long id, String name, String author, LocalDate datePublished) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.datePublished = datePublished;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public LocalDate getDatePublished() {
        return datePublished;
    }
    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }
}
