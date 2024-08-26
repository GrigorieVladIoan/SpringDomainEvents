package com.vlad.java.domain.events.service;

import com.vlad.java.domain.events.entity.Author;
import com.vlad.java.domain.events.entity.BookSale;
import com.vlad.java.domain.events.repository.BookRepository;
import com.vlad.java.domain.events.repository.BookSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
    @Transactional
    public void sellBook(BigInteger bookId) {
        final var book =
                bookRepository.findById(bookId)
                        .orElseThrow(() -> new NoSuchElementException(
                                "Book is not found"
                        ));
        BookSale bookSale = new BookSale();
        bookSale.setBook(book);
        bookSale.setDateSold(LocalDateTime.now());
        bookSale.setPriceSold(book.getPrice());
        bookSaleRepository.save(bookSale);
    }
}
