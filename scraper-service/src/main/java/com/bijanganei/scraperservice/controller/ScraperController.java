package com.bijanganei.scraperservice.controller;

import com.bijanganei.scraperservice.entity.JobPost;
import com.bijanganei.scraperservice.service.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/start")
    public List<JobPost> scrape(@RequestBody String url){
        try {
            return scraperService.extractJobs(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/all")
    public List<JobPost> getAllJobs(){
        return scraperService.getAllJobs();
    }

    @PostMapping("/remove/{id}")
    public void removeJob(@PathVariable String id){
        scraperService.removeJobById(id);
    }

    @GetMapping
    public void test(){
        scraperService.checkJobDescription()
    }
}
