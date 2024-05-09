package com.bijanganei.scraperservice.service;

import com.bijanganei.scraperservice.entity.JobPost;
import com.bijanganei.scraperservice.repository.ScraperRepository;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScraperServiceImpl implements ScraperService{

    private final ScraperRepository scraperRepository;

    public ScraperServiceImpl(ScraperRepository scraperRepository) {
        this.scraperRepository = scraperRepository;
    }

    //    method that searches content of each job post for sponsorship related words
    @Override
    public boolean checkJobDescription(String url) {
        // convert HTML in Document

        // select the element that contains job content

        return false;
    }

    @Override
    public List<JobPost> extractJobs(String url) {
        // convert HTML of job postings list page to Document

        // select the element that contains the list

        // create a list of job posts in order to check each of them for sponsorship related words
        // and adding them to our database if they offered sponsorship

        return null;
    }

    // method to get list of all jobs from database
    @Override
    public List<JobPost> getAllJobs() {
        return null;
    }


    // method to get job by ID from our db
    @Override
    public JobPost getJobById(String id) {
        return null;
    }

    // remove specific job from our db
    @Override
    public void removeJobById(String id) {

    }

    @Override
    public void addJob(JobPost job) {
        if (job != null){
            scraperRepository.save(job);
        }
    }
}
