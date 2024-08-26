package com.vlad.java.domain.events.controller;

import com.vlad.java.domain.events.entity.Author;
import com.vlad.java.domain.events.repository.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vlad-api/domain-events/v1/books")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

}
