package com.vlad.java.domain.events.service;

import com.vlad.java.domain.events.entity.Author;
import com.vlad.java.domain.events.entity.BookSale;
import com.vlad.java.domain.events.repository.BookRepository;
import com.vlad.java.domain.events.repository.BookSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookSaleServiceImpl implements BookSaleService{
    private final BookRepository bookRepository;
    private final BookSaleRepository bookSaleRepository;

    public BookSaleServiceImpl(BookRepository bookRepository, BookSaleRepository bookSaleRepository){
        this.bookRepository = bookRepository;
        this.bookSaleRepository = bookSaleRepository;
    }

    @Override
    public List<BookSale> getAllBookSales(){
        return bookSaleRepository.findAll();
    }

    @Override
    public List<BookSale> getAllBookSalesByBookId(BigInteger bookId) {
        return bookSaleRepository.findAllByBookId(bookId);
    }

    @Override
    public Integer getBookSalesCountByBookId(BigInteger bookId) {
        return bookSaleRepository.countByBookId(bookId);
    }

    @Override
    public void sellBook(BigInteger bookId) {
        final var book =
                bookRepository.findById(bookId)
                        .orElseThrow(() -> new NoSuchElementException(
                                "Book is not found"
                        ));
        BookSale bookSale = new BookSale();
        bookSale.setBook(book);
        bookSale.setBookId(bookId);
        bookSale.setDateSold(LocalDateTime.now());
        bookSale.setPriceSold(book.getPrice());
        bookSaleRepository.save(bookSale);
    }

    @Override
    @Transactional
    public void sellBooks(BigInteger bookId, Integer noOfBooksSold) {
        final var book =
                bookRepository.findById(bookId)
                        .orElseThrow(() -> new NoSuchElementException(
                                "Book is not found"
                        ));

        List<BookSale> newBookSales = new ArrayList<>();
        for(int i = 0; i<noOfBooksSold; i++){
            BookSale bookSale = new BookSale();
            bookSale.setBook(book);
            bookSale.setBookId(bookId);
            bookSale.setDateSold(LocalDateTime.now());
            bookSale.setPriceSold(book.getPrice());
            newBookSales.add(bookSale);
        }
        bookSaleRepository.saveAll(newBookSales);

    }
}
