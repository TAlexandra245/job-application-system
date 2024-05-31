package com.job_application_system.job_application_system.company;

import java.util.List;


public interface CompanyService {
    List<Company> getAllCompanies();

    void createJob(Company company);

    Company findById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Company updatedCompany);
}
