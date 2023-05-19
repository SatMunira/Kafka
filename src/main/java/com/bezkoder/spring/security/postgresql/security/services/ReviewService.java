package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.models.Rating;
import com.bezkoder.spring.security.postgresql.models.Reviews;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.BookRepository;
import com.bezkoder.spring.security.postgresql.repository.RatingRepository;
import com.bezkoder.spring.security.postgresql.repository.ReviewRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;
    public Reviews createReview(String review, Integer rating, Long userId, Long bookId, String date) {
        Reviews newReview = new Reviews();
        try{
        newReview.setContent(review);
        newReview.setDatePost(date);
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        System.out.println(bookId);
        System.out.println("the book: "+ book);
        Rating existingRating = ratingRepository.findByUserAndBook(user, book);
        newReview.setBook(book);
        newReview.setUser(user);
        if (existingRating == null) {
            // User has not rated the book, create a new rating

            Rating newRating = new Rating();
            newRating.setValue(rating);
            newRating.setUser(user);
            newRating.setBook(book);
            ratingRepository.save(newRating);
            newReview.setRating(newRating);

        } else {
            // User has already rated the book, update the existing rating
            existingRating.setValue(rating);
            ratingRepository.save(existingRating);
            newReview.setRating(existingRating);
        }

        System.out.println(newReview.getRating().getValue());
        System.out.println(newReview.getBook().getTitle());
        System.out.println(newReview.getContent());


        reviewRepository.save(newReview);
        }catch(Exception e){
            System.out.println("Blyad "+ e);
        }

        return  newReview;
    }

    public List<Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }
}
