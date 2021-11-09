package com.rmmarquini.workshopspring.repository;

import com.rmmarquini.workshopspring.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    /**
     * ?number, on @Query annotation, references to the parameter position on the method
     *
     * @param text
     * @param minDate
     * @param maxDate
     * @return
     */
    @Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);

    // Simple query using Mongo Query Annotation
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    // Simple query using Mongo Query Methods
    List<Post> findByTitleContainingIgnoreCase(String text);

}
