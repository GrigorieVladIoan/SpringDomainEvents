package com.vlad.java.domain.events.controller;

import com.vlad.java.domain.events.entity.Book;
import com.vlad.java.domain.events.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vlad-api/domain-events/v1/books")
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public List<Book> getAllBooks(){

        List<Book> books = bookRepository.findAll();
        for (Book b: books){
            log.info("book = {}", b.toString());
        }

        return bookRepository.findAll();
    }

}
