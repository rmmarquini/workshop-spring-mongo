package com.rmmarquini.workshopspring.services;

import com.rmmarquini.workshopspring.domain.User;
import com.rmmarquini.workshopspring.repository.UserRepository;
import com.rmmarquini.workshopspring.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Spring dependency auto-injection
    @Autowired
    private UserRepository repository;

    public List<User> findAll() { return repository.findAll(); }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not founded."));
    }

}
