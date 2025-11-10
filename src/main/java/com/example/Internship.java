package com.example;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
import com.example.Users;


@Entity
@Table(name = "internship")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String company;
    private String title;

    @Enumerated(EnumType.STRING)
    private InternshipStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public InternshipStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Users getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(InternshipStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
