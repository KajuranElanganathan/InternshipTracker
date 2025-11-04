package com.example;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    private String providerId;    // Google sub id
    private String pictureUrl;    // optional nice touch

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;


}
