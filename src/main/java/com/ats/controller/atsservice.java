/*package com.ats.controller;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class atsservice {

    public String calculateScore(String resumeText,
                                 String jobDescription) {

        Set<String> jobWords = new LinkedHashSet<>(
            Arrays.asList(jobDescription.toLowerCase()
                .replaceAll("[^a-z0-9+#.] ", " ")
                .split("\\s+"))
        );

        Set<String> stopWords = Set.of(
            "the", "and", "for", "with", "you",
            "are", "this", "that", "will", "your",
            "have", "has", "from", "our", "their",
            "they", "can", "all", "who", "into",
            "to", "of", "in", "a", "an", "is"
        );

        jobWords.removeIf(word ->
            word.length() < 3 || stopWords.contains(word)
        );

        Set<String> matched = new LinkedHashSet<>();
        Set<String> missing = new LinkedHashSet<>();

        String resume = resumeText.toLowerCase();

        for (String word : jobWords) {
            if (resume.contains(word)) {
                matched.add(word);
            } else {
                missing.add(word);
            }
        }

        double score = jobWords.isEmpty() ? 0 :
            (matched.size() * 100.0) / jobWords.size();

        return "ATS Match Score: " +
            String.format("%.2f", score) + "%" +
            "\nMatched Keywords: " + matched +
            "\nMissing Keywords: " + missing;
    }
}*/

package com.ats.controller;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ats.resumeanalysis;
import com.ats.repository.resumeanalysisrepository;

@Service
public class atsservice {

    private final resumeanalysisrepository repository;

    public atsservice(resumeanalysisrepository repository) {
        this.repository = repository;
    }

    public String calculateScore(String resumeText,
                                 String jobDescription,
                                 String resumeName) {

        Set<String> jobWords = new LinkedHashSet<>(
            Arrays.asList(jobDescription.toLowerCase()
                .replaceAll("[^a-z0-9+#.]+", " ")
                .split("\\s+"))
        );

        Set<String> stopWords = Set.of(
            "the", "and", "for", "with", "you",
            "are", "this", "that", "will", "your",
            "have", "has", "from", "our", "their",
            "they", "can", "all", "who", "into",
            "to", "of", "in", "a", "an", "is"
        );

        jobWords.removeIf(word ->
            word.length() < 3 || stopWords.contains(word)
        );

        Set<String> matched = new LinkedHashSet<>();
        Set<String> missing = new LinkedHashSet<>();

        String resume = resumeText.toLowerCase();

        for (String word : jobWords) {
            if (resume.contains(word)) {
                matched.add(word);
            } else {
                missing.add(word);
            }
        }

        double score = jobWords.isEmpty() ? 0 :
            (matched.size() * 100.0) / jobWords.size();

        // Create an object to store in MySQL
        resumeanalysis analysis = new resumeanalysis();

        analysis.setResumeName(resumeName);
        analysis.setJobDescription(jobDescription);
        analysis.setAtsScore(score);
        analysis.setMatchedKeywords(matched.toString());
        analysis.setMissingKeywords(missing.toString());
        analysis.setCreatedAt(LocalDateTime.now());

        // Save the analysis
        repository.save(analysis);

        return "ATS Match Score: " +
            String.format("%.2f", score) + "%" +
            "\nMatched Keywords: " + matched +
            "\nMissing Keywords: " + missing;
    }
}
