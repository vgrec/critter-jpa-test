package com.udacity.jdnd.course3.critter.test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        if (books == null){
            books = new ArrayList<>();
        }
        books.add(book);
    }
}
