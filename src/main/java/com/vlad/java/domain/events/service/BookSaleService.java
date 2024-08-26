package com.vlad.java.domain.events.service;

import com.vlad.java.domain.events.entity.BookSale;

import java.math.BigInteger;
import java.util.List;

public interface BookSaleService {
    public void sellBook(BigInteger bookId);
    public void sellBooks(BigInteger bookId, Integer noOfBooksSold);
    public List<BookSale> getAllBookSales();
    public List<BookSale> getAllBookSalesByBookId(BigInteger bookId);
    public Integer getBookSalesCountByBookId(BigInteger bookId);
}
