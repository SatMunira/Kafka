package com.bezkoder.spring.security.postgresql.controllers;

/**AZIZ**/

import com.bezkoder.spring.security.postgresql.models.Comment;
import com.bezkoder.spring.security.postgresql.models.Reviews;
import com.bezkoder.spring.security.postgresql.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping("/createComment")
    public Comment createComment(@RequestBody Comment comment) {
        if (comment.getReplyTo() != null) {
            Comment parentComment = commentRepository.findById(comment.getReplyTo())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + comment.getReplyTo()));

            comment.setParentComment(parentComment);
        }

        return commentRepository.save(comment);
    }

    @GetMapping("/getComments")
    public List<Comment> getAllComments(@RequestParam Long bookId){
        System.out.println("bookId" + bookId);

        return commentRepository.findAllByBook_Id(bookId);
    }

    // Other CRUD operations

    // ...
}

