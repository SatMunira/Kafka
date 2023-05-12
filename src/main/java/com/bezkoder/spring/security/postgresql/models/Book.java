package com.bezkoder.spring.security.postgresql.models;

import lombok.Data;

import javax.persistence.*;



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

    private String title;
    private Integer yearOfWriting;
    private Integer quantityOfPages;
    private Double rating;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Author> authors;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Edition> editions;
    private String imageBack;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Genre> genres;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reviews> reviews;



    public Book() {
        this.editions = new ArrayList<>();
    }

    public String getImageUrl() {
        return "/images/imageBack/" + this.getId() + "/" + this.getImageBack();
    }
}
