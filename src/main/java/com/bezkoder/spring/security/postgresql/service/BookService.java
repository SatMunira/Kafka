package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.*;
import com.bezkoder.spring.security.postgresql.repository.AuthorRepository;
import com.bezkoder.spring.security.postgresql.repository.BookRepository;
import com.bezkoder.spring.security.postgresql.repository.EditionRepository;
import com.bezkoder.spring.security.postgresql.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Transactional
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Author> authorList() {
        return authorRepository.findAll();
    }

    private final EditionRepository editionRepository;

    public Book createBook(Book newBook, MultipartFile image,
                           MultipartFile imageBack,  Edition edition) throws IOException {


        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String fileBackName = StringUtils.cleanPath(imageBack.getOriginalFilename());

        newBook.setImageBack(fileBackName);


        edition.setImage(fileName);
        editionRepository.save(edition);
        newBook.getEditions().add(edition);
        bookRepository.save(newBook);

        String uploadDir = "images/imagePreview/" + edition.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, image);

        String uploadBackDir = "images/imageBack/" + newBook.getId();
        FileUploadUtil.saveFile(uploadBackDir, fileBackName, imageBack);

        return newBook;
    }


}
