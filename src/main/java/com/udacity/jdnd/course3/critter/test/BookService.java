package com.udacity.jdnd.course3.critter.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    }
}
