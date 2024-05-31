package com.job_application_system.job_application_system.job;

import com.job_application_system.job_application_system.company.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
//@Table(name = "job_table")
@Data
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne
    private Company company;
}
