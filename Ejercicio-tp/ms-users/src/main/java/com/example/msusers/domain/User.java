package com.example.msusers.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class User {
    String id;

    String name;

    String lastName;

    String email;

    List<Bill> bills;
}
