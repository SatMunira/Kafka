package com.bezkoder.spring.security.postgresql.models;

import lombok.Data;

import javax.persistence.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Author> authors;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Publisher> publishers;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Edition> editions;
    private String imageBack;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Genre> genres;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Tag> tags;



    public Book() {
        this.editions = new ArrayList<>();
    }
}
