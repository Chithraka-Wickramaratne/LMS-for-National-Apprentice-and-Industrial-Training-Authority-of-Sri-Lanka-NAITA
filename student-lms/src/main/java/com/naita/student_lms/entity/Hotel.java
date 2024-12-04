package com.naita.student_lms.entity;

public class Hotel {
    private String id;
    private String title;
    private String pdfString;
    private String description;
    private String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdfString() {
        return pdfString;
    }

    public void setPdfString(String pdfString) {
        this.pdfString = pdfString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
