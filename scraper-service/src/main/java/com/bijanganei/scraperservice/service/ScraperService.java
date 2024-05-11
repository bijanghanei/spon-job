package com.bijanganei.scraperservice.service;

import com.bijanganei.scraperservice.entity.JobPost;

import java.io.IOException;
import java.util.List;

public interface ScraperService {
    JobPost checkJobDescription(String url) throws IOException;
    List<JobPost> extractJobs(String url) throws IOException;
    List<JobPost> getAllJobs();
    JobPost getJobById(String id);
    void removeJobById(String id);
    void addJob(JobPost job);

}
