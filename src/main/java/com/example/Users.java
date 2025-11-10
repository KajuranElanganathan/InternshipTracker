package com.example;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
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

    public Users(UUID id, String firstName, String lastName, String email, String providerId, String pictureUrl, AuthProvider authProvider) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.providerId = providerId;
        this.pictureUrl = pictureUrl;
        this.authProvider = authProvider;
    }

    public Users() {}


    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }
}
