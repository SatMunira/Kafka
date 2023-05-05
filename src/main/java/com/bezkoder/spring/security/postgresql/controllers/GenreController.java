package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Genre;
import com.bezkoder.spring.security.postgresql.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    @GetMapping("/genreList")
    public List<Genre> genreList(){
        return genreService.allGenres();
    }
    @PostMapping("/createGenre")
    public Genre createGenre(@RequestBody Genre genre){
        return genreService.createGenre(genre);
    }

}
