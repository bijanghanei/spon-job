package com.bijanganei.scraperservice.service;

import com.bijanganei.scraperservice.entity.JobPost;
import com.bijanganei.scraperservice.repository.ScraperRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class ScraperServiceImpl implements ScraperService{

    private final ScraperRepository scraperRepository;

    public ScraperServiceImpl(ScraperRepository scraperRepository) {
        this.scraperRepository = scraperRepository;
    }

    //    method that searches content of each job post for sponsorship related words
    @Override
    public boolean checkJobDescription(String url) throws IOException {

        boolean hasRelocationPackage = false;

        // convert HTML in Document
        Document document = Jsoup.connect(url).get();

        // select the element that contains job content
        Elements descriptionDoc = document.getElementsByClass("decorated-job-posting__details");
        Element descriptionElement = descriptionDoc.getFirst();
        String descriptionText = descriptionElement.text().toLowerCase();

        Elements introDoc = document.getElementsByTag("title");
        Element introElement = introDoc.getFirst();
        String introText = introElement.text().toLowerCase();

        Elements titleDoc = document.getElementsByClass("topcard__title");
        Element titleElement = titleDoc.getFirst();
        String titleText = titleElement.text().toLowerCase();

        if (descriptionText.contains("relocation") || descriptionText.contains("sponsorship")
            || introText.contains("relocation") || introText.contains("sponsorship")
            && !descriptionText.contains("no relocation") && !descriptionText.contains("no visa sponsorship")){
            hasRelocationPackage = true;

            // TODO : find a solution for not saving duplicate job posts

            JobPost job = new JobPost();
            job.setTitle(introText);
            job.setIntroduction(introText);
            job.setUrl(url);
            job.setTitle(titleText);


            scraperRepository.save(job);
        }

        return hasRelocationPackage;
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
        return scraperRepository.findAll();
    }


    // method to get job by ID from our db
    @Override
    public JobPost getJobById(String id) {
        return scraperRepository.findById(id).orElse(null);
    }



    // remove specific job from our db
    @Override
    public void removeJobById(String id) {
        scraperRepository.deleteById(id);
    }

    @Override
    public void addJob(JobPost job) {
        if (job != null){
            scraperRepository.save(job);
        }
    }
}
