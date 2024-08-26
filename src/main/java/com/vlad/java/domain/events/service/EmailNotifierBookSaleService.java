package com.vlad.java.domain.events.service;

import com.vlad.java.domain.events.entity.Author;
import com.vlad.java.domain.events.entity.BookSale;
import com.vlad.java.domain.events.repository.BookRepository;
import com.vlad.java.domain.events.repository.BookSaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
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
    public Integer getBookSalesCountByBookId(BigInteger bookId) {
       return origin.getBookSalesCountByBookId(bookId);
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

    @Override
    public void sellBooks(BigInteger bookId, Integer noOfBooksSold) {
        origin.sellBooks(bookId, noOfBooksSold);
        final var savedBook = bookRepository.findById(bookId).orElseThrow();

        Author author = savedBook.getAuthor();
        String emailMessage = String.format("Another %s books of yours have been sold!", noOfBooksSold);
        emailService.send(author.getEmail(), emailMessage);
    }

}
