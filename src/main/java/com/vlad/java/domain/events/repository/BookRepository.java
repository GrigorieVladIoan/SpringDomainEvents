package com.vlad.java.domain.events.repository;

import com.vlad.java.domain.events.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, BigInteger> {

    @Query(value = "SELECT b FROM Book b JOIN Author a ON b.authorId = a.authorId")
    public List<Book> findAllBooksDetail();


}
