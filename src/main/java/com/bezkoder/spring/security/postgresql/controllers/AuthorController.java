package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Author;
import com.bezkoder.spring.security.postgresql.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final  AuthorService authorService;

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/createAuthor")
    public Author createAuthor(@RequestBody Author author){
        return authorService.createAuthor(author);
    }

    @GetMapping("/listAuthors")
    public List<Author> allAuthors(){
        return authorService.getAllAuthors();
    }
}
