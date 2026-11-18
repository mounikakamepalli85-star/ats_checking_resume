

package com.ats;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.controller.atsservice;
import com.ats.controller.pdfservice;
import java.util.List;
import com.ats.repository.resumeanalysisrepository;
@RestController
public class hellocontroller {

    private final pdfservice pdfService;
     private final atsservice atsService;    
      private final resumeanalysisrepository repository;
    public hellocontroller(pdfservice pdfService,  atsservice atsService, resumeanalysisrepository repository) {
        this.pdfService = pdfService;
         this.atsService = atsService;
         this.repository = repository;
    }    

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to Resume ATS Checker!";
    }

    @GetMapping("/history")
public List<resumeanalysis> getHistory() {
    return repository.findAll();
}
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
             @RequestParam("jobDescription") String jobDescription)
            throws java.io.IOException {

        if (file.isEmpty()) {
            return "Please select a resume!";
        }
        if(jobDescription.isBlank())
        
            {
                return "please enter a job description";
            }
        String resumeText = pdfService.extractText(file);
        String result = atsService.calculateScore(
                resumeText, jobDescription,file.getOriginalFilename());
        return result;
    }
}
