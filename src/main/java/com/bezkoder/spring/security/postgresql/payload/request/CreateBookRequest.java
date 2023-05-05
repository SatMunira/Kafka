package com.bezkoder.spring.security.postgresql.payload.request;

import com.bezkoder.spring.security.postgresql.models.Author;
import com.bezkoder.spring.security.postgresql.models.Genre;
import com.bezkoder.spring.security.postgresql.models.Publisher;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class CreateBookRequest {
    private List<Author> authors;
    private List<Publisher> publishers;
    private List<Genre> genres;
    private String title;
    private String alternativeTitle;
    private Date dateOfManufacture;
    private String description;
}
