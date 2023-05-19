package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.models.BookList;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.BookListRepository;
import com.bezkoder.spring.security.postgresql.repository.BookRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booklists")
public class BookListController {

    private final BookListRepository bookListRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @GetMapping("/listtypes")
    public ResponseEntity<List<String>> getListTypes() {
        List<String> listTypes = Arrays.asList("Read", "In Plans", "Currently Reading");
        return ResponseEntity.ok(listTypes);
    }


    @PostMapping("/add")
    public BookList addToBookList(@RequestBody BookList bookList) {

        BookList existingBookList = bookListRepository.findByUserAndBook(bookList.getUser(), bookList.getBook());

        if(existingBookList == null){
            BookList newBookList = new BookList();
            BeanUtils.copyProperties(bookList, newBookList);
            return bookListRepository.save(newBookList);
        }else{
            existingBookList.setListType(bookList.getListType());
            return bookListRepository.save(existingBookList);
        }

    }

    @GetMapping("/getType")
    public String getType(@RequestParam Long bookId, @RequestParam Long userId){
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user != null && book != null) {
            BookList bookList = bookListRepository.findByUserAndBook(user, book);
            if (bookList != null) {
                return bookList.getListType();
            }
        }
        return null;
    }
}
