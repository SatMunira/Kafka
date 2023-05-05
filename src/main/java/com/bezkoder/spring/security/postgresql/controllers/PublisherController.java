package com.bezkoder.spring.security.postgresql.controllers;


import com.bezkoder.spring.security.postgresql.models.Publisher;
import com.bezkoder.spring.security.postgresql.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/createPublisher")
    public Publisher createPublisher(@RequestBody Publisher publisher){
        return publisherService.createPublisher(publisher);
    }

    @GetMapping("/allPublishers")
    public List<Publisher> publisherList(){
        return publisherService.allPublishers();
    }
}
