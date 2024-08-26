package com.vlad.java.domain.events.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity(name = "Author")
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @SequenceGenerator(name = "author_generator", sequenceName = "seq_author_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @Id
    @Column(name = "author_id")
    private BigInteger authorId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}
