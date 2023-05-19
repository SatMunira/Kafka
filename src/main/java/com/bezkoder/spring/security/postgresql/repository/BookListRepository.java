package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.models.BookList;
import com.bezkoder.spring.security.postgresql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends JpaRepository<BookList, Long> {
    BookList findByUserAndBook(User user, Book book);

    BookList findByBook_IdAndUser_Id(Long userId, Long bookId);

}
