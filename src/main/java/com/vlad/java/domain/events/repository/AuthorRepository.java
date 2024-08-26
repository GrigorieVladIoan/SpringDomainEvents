package com.vlad.java.domain.events.repository;

import com.vlad.java.domain.events.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AuthorRepository extends JpaRepository<Author, BigInteger> {
}
