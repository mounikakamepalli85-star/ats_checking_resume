package com.ats.controller;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

@Service
public class pdfservice {

    public String extractText(MultipartFile file) throws IOException {

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            return text;
        }
    }
}
