package com.rmmarquini.workshopspring.resources;

import com.rmmarquini.workshopspring.domain.Post;
import com.rmmarquini.workshopspring.resources.util.URL;
import com.rmmarquini.workshopspring.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    // Spring dependency auto-injection
    @Autowired
    private PostService service;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text)
            throws UnsupportedEncodingException {

        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);

    }

}
