package com.vlad.java.domain.events.repository;

import com.vlad.java.domain.events.entity.BookSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BookSaleRepository extends JpaRepository<BookSale, BigInteger> {

    public List<BookSale> findAllByBookId(BigInteger bookId);
    public Integer countByBookId(BigInteger bookId);

}
