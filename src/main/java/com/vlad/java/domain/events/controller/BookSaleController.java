package com.vlad.java.domain.events.controller;

import com.vlad.java.domain.events.entity.BookSale;
import com.vlad.java.domain.events.repository.BookSaleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/vlad-api/domain-events/v1/books")
public class BookSaleController {

    private final BookSaleRepository bookSaleRepository;

    public BookSaleController(BookSaleRepository bookSaleRepository){
        this.bookSaleRepository = bookSaleRepository;
    }

    @GetMapping("/sales")
    public List<BookSale> getAllBookSales(){
        return bookSaleRepository.findAll();
    }

    @GetMapping("/{bookId}/sales")
    public List<BookSale> getAllBookSales(@PathVariable(name = "bookId", required = true)BigInteger bookId){
        return bookSaleRepository.findAllByBookId(bookId);
    }
}
