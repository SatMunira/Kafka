package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Rating;
import com.bezkoder.spring.security.postgresql.models.Reviews;
import com.bezkoder.spring.security.postgresql.repository.ReviewRepository;
import com.bezkoder.spring.security.postgresql.security.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

        @PostMapping("/newReview")
        public ResponseEntity<?> createReview(@RequestBody Map<String, Object> requestMap,
                                              @RequestParam("userId") Long userId, @RequestParam("bookId") Long bookId){
            String content = (String) requestMap.get("content");
            int ratingValue = (Integer) requestMap.get("value");
            String date = (String) requestMap.get("datePost");
            reviewService.createReview(content, ratingValue, userId, bookId, date);
            return ResponseEntity.ok("Success");
        }
        @GetMapping("/getReviews")
        public List<Reviews> getAllReviews(@RequestParam Long bookId){
            return reviewRepository.findAllByBook_Id(bookId);
        }
}
