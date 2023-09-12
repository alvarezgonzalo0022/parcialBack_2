package com.example.msusers.service;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import com.example.msusers.repository.BillRepository;
import com.example.msusers.repository.KeycloakUserRepository;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final KeycloakUserRepository userRepository;
    private BillRepository billRepository;

    public UserService(KeycloakUserRepository userRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public User findById(String realm, String id){
        UserRepresentation userRepresentation = userRepository.getUserById(realm, id);
        List<Bill> userBills = billRepository.findByUserId(id);

        User user = new User(userRepresentation.getId(), userRepresentation.getFirstName(), userRepresentation.getLastName(), userRepresentation.getEmail(), userBills);

        return user;
    }

    public List<User> findAll(String realm) {
        List<UserRepresentation> userRepresentations = userRepository.getAllUsers(realm);
        List<User> users = new ArrayList<>();
        for (UserRepresentation userRepresentation : userRepresentations) {
            List<Bill> userBills = billRepository.findByUserId(userRepresentation.getId());
            User user = new User(userRepresentation.getId(), userRepresentation.getFirstName(), userRepresentation.getLastName(), userRepresentation.getEmail(), userBills);
            users.add(user);
        }
        return users;
    }

    public List<RealmRepresentation> findAllRealms() {
        List<RealmRepresentation> realmRepresentations = userRepository.getAllRealms();
        return realmRepresentations;
    }
}
