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

@Service
public class ScraperServiceImpl implements ScraperService{

    private final ScraperRepository scraperRepository;

    public ScraperServiceImpl(ScraperRepository scraperRepository) {
        this.scraperRepository = scraperRepository;
    }

    //    method that searches content of each job post for sponsorship related words
    @Override
    public JobPost checkJobDescription(String url) throws IOException {

        boolean hasRelocationPackage = false;

        // convert HTML in Document
        Document document = Jsoup.connect(url).timeout(0).get();

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


            Element link = document.getElementsByAttributeValue("rel","canonical").first();
            String href = link.attr("href");

            // TODO : find a solution for not saving duplicate job posts

            JobPost job = new JobPost();
            job.setTitle(introText);
            job.setIntroduction(introText);
            job.setUrl(href);
            job.setTitle(titleText);


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return job;
        }


        return null;
    }

    @Override
    public List<JobPost> extractJobs(String url) throws IOException {

        Document document = Jsoup.connect(url).get();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Element jobList = document.getElementsByClass("jobs-search__results-list").first();

        if (jobList != null) {
            Elements jobCards = jobList.getElementsByClass("job-search-card");
            for (Element jobCard : jobCards){
                String href = jobCard.getElementsByTag("a").first().attr("href");
                JobPost job = checkJobDescription(href);
                if (job!= null){
                    scraperRepository.save(job);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return scraperRepository.findAll();
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
