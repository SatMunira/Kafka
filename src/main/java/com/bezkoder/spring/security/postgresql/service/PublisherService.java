package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.Publisher;
import com.bezkoder.spring.security.postgresql.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public Publisher createPublisher(Publisher publisher) {
        Publisher newPublisher = new Publisher();
        BeanUtils.copyProperties(publisher,newPublisher);
        publisherRepository.save(newPublisher);
        return publisher;
    }

    public List<Publisher> allPublishers() {
        return publisherRepository.findAll();
    }
}
