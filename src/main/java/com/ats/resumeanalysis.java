package com.ats;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resume_analysis")
public class resumeanalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeName;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private Double atsScore;

    @Column(columnDefinition = "TEXT")
    private String matchedKeywords;

    @Column(columnDefinition = "TEXT")
    private String missingKeywords;

    private LocalDateTime createdAt;

    public resumeanalysis() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(Double atsScore) {
        this.atsScore = atsScore;
    }

    public String getMatchedKeywords() {
        return matchedKeywords;
    }

    public void setMatchedKeywords(String matchedKeywords) {
        this.matchedKeywords = matchedKeywords;
    }

    public String getMissingKeywords() {
        return missingKeywords;
    }

    public void setMissingKeywords(String missingKeywords) {
        this.missingKeywords = missingKeywords;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
