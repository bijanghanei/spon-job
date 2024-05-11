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
            List<JobPost> test = scraperService.extractJobs("https://www.linkedin.com/jobs/search/?currentJobId=3909631467&geoId=102890719&keywords=java%20developer&location=Netherlands&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true");
//            boolean test = scraperService.checkJobDescription("https://www.linkedin.com/jobs/view/3881531273/?eBP=NOT_ELIGIBLE_FOR_CHARGING&refId=yFLl%2BS%2FMAX2Kz4qBEIELsQ%3D%3D&trackingId=c4QhioqAttrp3u3ewi%2Bcaw%3D%3D&trk=flagship3_search_srp_jobs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
