package com.example;

public class ExtractedInternshipDTO {
    private String company;
    private String title;


    public ExtractedInternshipDTO() {}

    public ExtractedInternshipDTO(String company, String title, String description) {
        this.company = company;
        this.title = title;

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

}