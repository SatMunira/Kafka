package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.Genre;
import com.bezkoder.spring.security.postgresql.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    public List<Genre> allGenres(){
        return genreRepository.findAll();
    }

    public Genre createGenre(Genre genre) {
        Genre newGenre = new Genre();
        BeanUtils.copyProperties(genre, newGenre);
        genreRepository.save(newGenre);
        return genre;
    }
}
