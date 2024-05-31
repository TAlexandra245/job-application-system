package com.job_application_system.job_application_system.company.impl;

import com.job_application_system.job_application_system.company.Company;
import com.job_application_system.job_application_system.company.CompanyRepository;
import com.job_application_system.job_application_system.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createJob(Company company) {
        Optional<Company> companyOptional = companyRepository.findCompanyByName(company.getName());
        if (companyOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Company %s already exists", company.getName()));
        }

        companyRepository.save(company);

    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Company does not exist"));
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Company> companyToBeDeleted = companyRepository.findById(id);
        if (companyToBeDeleted.isPresent()) {
            companyRepository.deleteById(companyToBeDeleted.get().getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToBeUpdated = companyOptional.get();
            companyToBeUpdated.setDescription(updatedCompany.getDescription());
            companyToBeUpdated.setName(updatedCompany.getName());
            companyToBeUpdated.setJobList(updatedCompany.getJobList());
            return true;
        }
        return false;
    }
}
