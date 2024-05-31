package com.job_application_system.job_application_system.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job_application_system.job_application_system.company.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private String description;
    private double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;
}
