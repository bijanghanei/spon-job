package com.bijanganei.scraperservice.controller;

import com.bijanganei.scraperservice.entity.JobPost;
import com.bijanganei.scraperservice.service.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/scraper")
public class ScraperController {
    private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping("/job/{id}")
    public JobPost getJob(@PathVariable String id){
        return scraperService.getJobById(id);
    }
    @GetMapping("/test")
    public void Test(){
        System.out.println("controller is alright");
//        try {
//            boolean test = scraperService.checkJobDescription("https://www.linkedin.com/jobs/view/3917137008");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
            List<JobPost> test = scraperService.extractJobs("https://www.linkedin.com/jobs/search?keywords=Java%20Developer%20&location=Netherlands&geoId=102890719&f_TPR=r86400&position=1&pageNum=0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
