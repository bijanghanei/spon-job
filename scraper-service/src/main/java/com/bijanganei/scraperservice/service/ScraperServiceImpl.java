package com.bijanganei.scraperservice.service;

import com.bijanganei.scraperservice.entity.JobPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScraperServiceImpl implements ScraperService{
    @Override
    public boolean checkJobDescription(String url) {
        return false;
    }

    @Override
    public List<JobPost> extractJobs(String url) {
        return null;
    }

    @Override
    public List<JobPost> getAllJobs() {
        return null;
    }

    @Override
    public JobPost getJobById(String id) {
        return null;
    }

    @Override
    public void removeJobById(String id) {

    }

    @Override
    public void addJob(JobPost job) {

    }
}
