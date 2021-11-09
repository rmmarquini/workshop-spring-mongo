package com.rmmarquini.workshopspring.repository;

import com.rmmarquini.workshopspring.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // Simple query using Mongo Query Annotation
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    // Simple query using Mongo Query Methods
    List<Post> findByTitleContainingIgnoreCase(String text);

}
