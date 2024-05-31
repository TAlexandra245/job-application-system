package com.job_application_system.job_application_system.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")

public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean updateCompany = companyService.updateJob(id, updatedCompany);
        if (updateCompany) {
            return new ResponseEntity<>("Company updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createCompany(@RequestBody Company newCompany) {
        companyService.createJob(newCompany);
        return new ResponseEntity<>("Company created.", HttpStatus.OK);
    }


}
