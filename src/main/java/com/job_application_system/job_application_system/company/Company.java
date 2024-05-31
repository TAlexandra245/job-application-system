package com.job_application_system.job_application_system.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job_application_system.job_application_system.job.Job;
import com.job_application_system.job_application_system.review.Review;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobList;

    @OneToMany(mappedBy = "company")
    private List<Review> reviewList;

}
