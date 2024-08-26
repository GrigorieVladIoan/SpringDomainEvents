package com.vlad.java.domain.events.controller;

import com.vlad.java.domain.events.entity.BookArchive;
import com.vlad.java.domain.events.repository.BookArchiveRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vlad-api/domain-events/v1/books")
public class BookArchiveController {

    private final BookArchiveRepository bookArchiveRepository;

    public BookArchiveController(BookArchiveRepository bookArchiveRepository){
        this.bookArchiveRepository = bookArchiveRepository;
    }

    @GetMapping("/archives")
    public List<BookArchive> getAllBookArchives(){
        return this.bookArchiveRepository.findAll();
    }


}
