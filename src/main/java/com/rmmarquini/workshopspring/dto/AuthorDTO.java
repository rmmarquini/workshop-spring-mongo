package com.rmmarquini.workshopspring.dto;

import com.rmmarquini.workshopspring.domain.User;

import java.io.Serializable;

/**
 * AuthorDTO is used to database denormalization
 */
public class AuthorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDTO() {}

    public AuthorDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
