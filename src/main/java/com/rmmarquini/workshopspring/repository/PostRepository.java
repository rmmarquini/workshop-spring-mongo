package com.rmmarquini.workshopspring.repository;

import com.rmmarquini.workshopspring.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {}
