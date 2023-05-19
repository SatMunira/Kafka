package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.models.Edition;
import com.bezkoder.spring.security.postgresql.models.Tag;
import com.bezkoder.spring.security.postgresql.repository.BookRepository;
import com.bezkoder.spring.security.postgresql.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;


    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/createBook", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})

    public ResponseEntity<?> createBook(

            @RequestParam("imageQwe") MultipartFile image,
            @RequestParam("BackQwe") MultipartFile BackQwe,
            @RequestParam String book, @RequestParam String editionQwe

    ) throws IOException {
        Book newBook = new Book();
        Edition editionq = new Edition();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            newBook = objectMapper.readValue(book, Book.class);
            editionq = objectMapper.readValue(editionQwe, Edition.class);
        } catch (Error e) {
            System.out.println(e);
        }
        System.out.println(newBook);
        System.out.println(editionq);

        bookService.createBook(newBook, image, BackQwe,  editionq);

        return ResponseEntity.ok("Success");
    }
    @GetMapping("/latest")
    public List<Book> getLatestBooks(){
        return bookRepository.findTop7ByOrderByYearOfWritingDesc()  ;
    }

    @GetMapping("/all")
    public List<Book> getAllEditions(){
        return  bookRepository.findAll();
    }

    @PostMapping("/setTag")
    public Book setTags(@RequestBody Book book, @RequestBody List<Tag> tags) {
        try {
            if (book != null) {
                book.setTags(tags);
                bookRepository.save(book);
            }
            return book;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }





}
