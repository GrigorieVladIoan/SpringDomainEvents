package com.vlad.java.domain.events.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "BookArchive")
@Table(name = "book_archives")
public class BookArchive {

    @Id
    @SequenceGenerator(name = "bookArchiveSeqGenerator", sequenceName = "SEQ_BOOK_ARCHIVE_ID")
    @GeneratedValue(generator = "bookArchiveSeqGenerator")
    @Column(name = "book_archive_id")
    private BigInteger bookArchiveId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sold_books")
    private Integer soldBooks;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_date_updated")
    private LocalDateTime lastDateUpdated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", insertable=false, updatable=false)
    @JsonIgnore
    private Book book;

    @Column(name = "book_id")
    private BigInteger bookId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", insertable=false, updatable=false)
    @JsonIgnore
    private Author author;

    @Column(name = "author_id")
    private BigInteger authorId;

}
