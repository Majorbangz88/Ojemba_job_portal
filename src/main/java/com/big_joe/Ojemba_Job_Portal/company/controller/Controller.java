package com.big_joe.Ojemba_Job_Portal.company.controller;

import com.big_joe.Ojemba_Job_Portal.company.CompanyUtils;
import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.dto.UpdateRequest;
import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import com.big_joe.Ojemba_Job_Portal.company.service.CompanyService;
import com.big_joe.Ojemba_Job_Portal.company.service.RegCompanyRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vi/companies")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody RegCompanyRequest request) {
        try {
            CompanyResponse response = companyService.registerCompany(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while creating company: {}", e.getMessage(), e);
            CompanyResponse errorResponse = CompanyUtils.buildCompanyResponse(
                    CompanyUtils.INTERNAL_SERVER_ERROR_CODE,
                    "An unexpected error occurred. Please try again later.",
                    null
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAll() {
        List<Company> allCompanies = companyService.allCompanies();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    @GetMapping("/company")
    public ResponseEntity<CompanyResponse> getCompanyByName(String companyName) {
        CompanyResponse response = companyService.getByCompanyName(companyName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> fullUpdateCompany(@PathVariable String id, @RequestBody UpdateRequest request) {
        CompanyResponse response = companyService.fullUpdateCompany(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> partialUpdateCompany(@PathVariable String id, @RequestBody UpdateRequest request) {
        CompanyResponse response = companyService.partialUpdateCompany(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> deleteCompany(UUID id) {
        CompanyResponse response = companyService.deleteCompany(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
