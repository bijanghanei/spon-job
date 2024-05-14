package com.bijanganei.scraperservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "jobpost")
public class JobPost {
    @Id
    private String id;
    private String title;
    private String url;
    private String introduction;

}
