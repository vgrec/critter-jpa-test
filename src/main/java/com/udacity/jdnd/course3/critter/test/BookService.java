package com.udacity.jdnd.course3.critter.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    void test() {
        Author author = new Author();
        author.setName("Lollipop");
        Author savedAuthor = authorRepository.save(author);

        Book book = new Book();
        book.setName("Alice in the wonderland");
        book.setYear(1840);
        Book savedBook = bookRepository.save(book);

        savedBook.setAuthor(savedAuthor);
        bookRepository.save(savedBook);

        // ---- get author of a book
        Optional<Book> optional = bookRepository.findById(2L);
        Book b = optional.orElse(null);
        if (b != null) {
            System.out.println("Book: " + b.getName());
            System.out.println("Book author: " + b.getAuthor().getName());
        } else {
            System.out.println("Nothing found");
        }

    }

    void test2(){

    }
}
