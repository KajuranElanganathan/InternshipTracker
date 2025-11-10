package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

public class InternshipResponseDTO {

    private UUID id;
    private String company;
    private String title;
    private InternshipStatus status;
    private LocalDateTime createdAt;

    public InternshipResponseDTO(UUID id, String company, String title, InternshipStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InternshipStatus getStatus() {
        return status;
    }

    public void setStatus(InternshipStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
