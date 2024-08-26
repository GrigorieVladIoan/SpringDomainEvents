package com.vlad.java.domain.events.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity(name = "BookSale")
@Table(name = "book_sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSale {

    @SequenceGenerator(name = "bookSale_generator", sequenceName = "seq_book_sale_id")
    @GeneratedValue(generator = "bookSale_generator")
    @Id
    @Column(name = "book_sale_id")
    private BigInteger bookSaleId;

    @Column(name = "price_sold")
    private Integer priceSold;

    @Column(name = "date_sold")
    private LocalDateTime dateSold;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", insertable=false, updatable=false)
    @JsonIgnore
    private Book book;

    @Column(name = "book_id")
    private BigInteger bookId;
}
