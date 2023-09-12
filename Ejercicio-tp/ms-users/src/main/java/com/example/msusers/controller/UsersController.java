package com.example.msusers.controller;

import com.example.msusers.domain.User;
import com.example.msusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService service;


    @GetMapping("/{realm}/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("realm") String realm, @PathVariable("id") String id) {
        return ResponseEntity.ok().body(service.findById(realm, id));
    }

    @GetMapping("/{realm}")
    public ResponseEntity<List<User>> getAllUsers(@PathVariable("realm") String realm) {
        return ResponseEntity.ok().body(service.findAll(realm));
    }

    @GetMapping("/realms")
    public ResponseEntity<List<RealmRepresentation>> findAllRealms() {
        return ResponseEntity.ok().body(service.findAllRealms());
    }


}
