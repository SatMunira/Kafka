package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.models.Rating;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.BookRepository;
import com.bezkoder.spring.security.postgresql.repository.RatingRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    // ...

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    @PostMapping("/newRating")
    public Rating createOrUpdateRating(@RequestBody Rating rating, @RequestParam Long userId, @RequestParam Long bookId, HttpServletRequest request) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        // Check if user has already rated the book
        Rating existingRating = ratingRepository.findByUserAndBook(user, book);

        if (existingRating == null) {
            // User has not rated the book, create a new rating
            Rating newRating = new Rating();
            BeanUtils.copyProperties(rating, newRating);
            newRating.setUser(user);
            newRating.setBook(book);
            return ratingRepository.save(newRating);
        } else {
            // User has already rated the book, update the existing rating
            existingRating.setValue(rating.getValue());
            return ratingRepository.save(existingRating);
        }
    }

    @GetMapping("/getRating")
    public Integer getRating(@RequestParam Long userId, @RequestParam Long bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (user != null && book != null) {
            Rating rating = ratingRepository.findByUserAndBook(user, book);
            if (rating != null) {
                System.out.println(rating.getValue());
                return rating.getValue();
            }
        }
        return null;
    }

    @GetMapping("/getAvgRating")
    public Double getAvgRating(@RequestParam Long bookId){
        return ratingRepository.findAverageRatingByBook(bookId);
    }

}
