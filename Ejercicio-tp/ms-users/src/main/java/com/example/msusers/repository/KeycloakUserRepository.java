package com.example.msusers.repository;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class KeycloakUserRepository {

    private final Keycloak keycloak;

    public KeycloakUserRepository() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/")
                .realm("Parcial")
                .grantType("client_credentials")
                .clientId("ClientFinal")
                .clientSecret("Wgfr83GzPkc2jxwoREnqGbFoLaSON8eH")
                .build();
    }

    public List<UserRepresentation> getAllUsers(String targetRealm) {
        try {
            System.out.println("Fetching users for realm: " + targetRealm);
            return keycloak.realms().realm(targetRealm).users().list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<RealmRepresentation> getAllRealms() {
        return keycloak.realms().findAll();
    }


    public UserRepresentation getUserById(String targetRealm, String userId) {
        return keycloak.realm(targetRealm).users().get(userId).toRepresentation();
    }
}
