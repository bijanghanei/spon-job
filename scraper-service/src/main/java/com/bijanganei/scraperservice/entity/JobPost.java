package com.bijanganei.scraperservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class JobPost {
    @Id
    private String id;
    private String title;
    private String url;
}
