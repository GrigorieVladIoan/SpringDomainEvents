package com.vlad.java.domain.events.service;

import com.vlad.java.domain.events.entity.Author;
import com.vlad.java.domain.events.entity.BookSale;
import com.vlad.java.domain.events.repository.BookRepository;
import com.vlad.java.domain.events.repository.BookSaleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class EmailNotifierBookSaleService implements BookSaleService{

    private final BookSaleService origin;
    private final BookRepository bookRepository;
    private final BookSaleRepository bookSaleRepository;
    private final EmailService emailService;

    public EmailNotifierBookSaleService(@Qualifier("bookSaleServiceImpl") BookSaleService origin, BookRepository bookRepository, BookSaleRepository bookSaleRepository, EmailService emailService){
        this.origin = origin;
        this.bookRepository = bookRepository;
        this.bookSaleRepository = bookSaleRepository;
        this.emailService = emailService;
    }

    public List<BookSale> getAllBookSales(){
        return origin.getAllBookSales();
    }

    @Override
    public List<BookSale> getAllBookSalesByBookId(BigInteger bookId){
        return origin.getAllBookSalesByBookId(bookId);
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
