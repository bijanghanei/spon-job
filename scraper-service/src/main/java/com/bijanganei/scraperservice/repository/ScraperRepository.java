package com.bijanganei.scraperservice.repository;

import com.bijanganei.scraperservice.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScraperRepository extends JpaRepository<String, JobPost> {
}
