package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Genre;
import com.bezkoder.spring.security.postgresql.models.Tag;
import com.bezkoder.spring.security.postgresql.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagRepository tagRepository;

    @GetMapping("/all")
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    @PostMapping("/createTag")
    public Tag createTag(@RequestBody Tag tag){
        Tag newTag = new Tag();
        BeanUtils.copyProperties(tag, newTag);
        tagRepository.save(newTag);
        return tag;
    }
}
