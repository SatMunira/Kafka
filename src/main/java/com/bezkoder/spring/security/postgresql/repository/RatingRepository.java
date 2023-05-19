package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.models.Rating;
import com.bezkoder.spring.security.postgresql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByUserAndBook(User user, Book book);

    @Query("SELECT AVG(value) FROM Rating WHERE book.id = :bookId")
    Double findAverageRatingByBook(@Param("bookId") Long bookId);

}
