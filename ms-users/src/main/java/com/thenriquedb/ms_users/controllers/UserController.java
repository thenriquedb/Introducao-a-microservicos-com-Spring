package com.thenriquedb.ms_users.controllers;

import com.thenriquedb.ms_users.domain.User;
import com.thenriquedb.ms_users.dtos.UserRecordDto;
import com.thenriquedb.ms_users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        User createdUser = userService.createUser(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
