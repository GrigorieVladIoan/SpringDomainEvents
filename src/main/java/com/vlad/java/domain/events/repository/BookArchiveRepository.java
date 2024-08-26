package com.vlad.java.domain.events.repository;

import com.vlad.java.domain.events.entity.BookArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BookArchiveRepository extends JpaRepository<BookArchive, BigInteger> {
}
