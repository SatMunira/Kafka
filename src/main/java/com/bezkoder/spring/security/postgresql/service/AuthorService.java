package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.Author;
import com.bezkoder.spring.security.postgresql.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public Author createAuthor(Author author) {
        Author newAuthor = new Author();
        BeanUtils.copyProperties(author, newAuthor);
        authorRepository.save(newAuthor);
        return author;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
