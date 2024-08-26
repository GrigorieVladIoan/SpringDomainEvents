package com.vlad.java.domain.events.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Book")
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "seq_book_id")
    @Column(name = "book_id")
    private BigInteger bookId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_date_updated")
    private LocalDateTime lastDateUpdated;

    @Column(name = "price")
    private Integer price;

    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    @OneToOne(targetEntity = Author.class, fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Author author;

    @Column(name = "author_id")
    private BigInteger authorId;

}
