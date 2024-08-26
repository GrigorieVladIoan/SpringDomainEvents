package com.vlad.java.domain.events.service;

import com.vlad.java.domain.events.entity.Author;
import com.vlad.java.domain.events.repository.BookRepository;
import com.vlad.java.domain.events.repository.BookSaleRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigInteger;

public class EmailNotifierBookSaleService implements BookSaleService{

    private final BookSaleService origin;
    private final BookRepository bookRepository;
    private final BookSaleRepository bookSaleRepository;
    private final EmailService emailService;

    public EmailNotifierBookSaleService(@Qualifier("BookSaleServiceImpl") BookSaleService origin, BookRepository bookRepository, BookSaleRepository bookSaleRepository, EmailService emailService){
        this.origin = origin;
        this.bookRepository = bookRepository;
        this.bookSaleRepository = bookSaleRepository;
        this.emailService = emailService;
    }


    @Override
    public void sellBook(BigInteger bookId) {
        origin.sellBook(bookId);
        final var savedBook = bookRepository.findById(bookId).orElseThrow();
        Integer totalSoldBooks = bookSaleRepository.countByBookId(bookId);
        if (totalSoldBooks % 10 == 0) {
            Author author = savedBook.getAuthor();
            emailService.send(author.getEmail(), "Another 10 books of yours have been sold!");
        }
    }

}
