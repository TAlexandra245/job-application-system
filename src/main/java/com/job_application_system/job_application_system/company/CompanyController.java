package com.job_application_system.job_application_system.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @GetMapping("all")
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
        Optional<Company> company = companyRepository.findCompanyByName(newCompany.getName());

        if (company.isPresent()) {
            return new ResponseEntity<>(String.format("Company %s already exists", newCompany.getName()), HttpStatus.BAD_REQUEST);
        }

        companyService.createJob(newCompany);
        return new ResponseEntity<>("Company created.", HttpStatus.OK);
    }

    @Operation(summary = "Get a company by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the company",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))}),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = companyService.findById(id);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
