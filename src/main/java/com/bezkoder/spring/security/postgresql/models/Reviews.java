package com.bezkoder.spring.security.postgresql.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Rating rating;
    @Column(length = 10000000)
    private String content;
    private String datePost;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}

