package com.vlad.java.domain.events.controller;

import com.vlad.java.domain.events.entity.BookSale;
import com.vlad.java.domain.events.service.BookSaleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/vlad-api/domain-events/v1/books")
public class BookSaleController {

    private final BookSaleService bookSaleService;

    public BookSaleController(@Qualifier("emailNotifierBookSaleService") BookSaleService bookSaleService){
        this.bookSaleService = bookSaleService;
    }

    @GetMapping("/sales")
    public List<BookSale> getAllBookSales(){
        return bookSaleService.getAllBookSales();
    }

    @GetMapping("/{bookId}/sales")
    public List<BookSale> getAllBookSalesByBookId(@PathVariable(name = "bookId", required = true)BigInteger bookId){
        return bookSaleService.getAllBookSalesByBookId(bookId);
    }

    @GetMapping("/{bookId}/sales/count")
    public Integer getCountBookSalesByBookId(@PathVariable(name = "bookId", required = true)BigInteger bookId){
        return bookSaleService.getBookSalesCountByBookId(bookId);
    }

    @PostMapping("/{bookId}/sales")
    public void sellBook(@PathVariable(name = "bookId", required = true) BigInteger bookId){
        bookSaleService.sellBook(bookId);
    }
    @PostMapping("/{bookId}/sales/count/{noOfBooksSold}")
    public void sellBook(@PathVariable(name = "bookId", required = true) BigInteger bookId,
                         @PathVariable(name = "noOfBooksSold", required = true) Integer noOfBooksSold){
        bookSaleService.sellBooks(bookId, noOfBooksSold);
    }
}
