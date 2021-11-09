package com.rmmarquini.workshopspring.resources;

import com.rmmarquini.workshopspring.domain.User;
import com.rmmarquini.workshopspring.dto.UserDTO;
import com.rmmarquini.workshopspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    // Spring dependency auto-injection
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> lstUser = service.findAll();
        List<UserDTO> lstDTO = lstUser.stream()
                .distinct()
                .parallel()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(lstDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

}
