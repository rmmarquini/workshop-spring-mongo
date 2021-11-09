package com.rmmarquini.workshopspring.services;

import com.rmmarquini.workshopspring.domain.Post;
import com.rmmarquini.workshopspring.repository.PostRepository;
import com.rmmarquini.workshopspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    // Spring dependency auto-injection
    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Post not founded."));
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        return repository.fullSearch(
                text,
                minDate,
                new Date(maxDate.getTime() + 24 * 60 * 60 * 1000)
        );
    }

}
