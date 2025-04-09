package com.big_joe.Ojemba_Job_Portal.company.controller;

import com.big_joe.Ojemba_Job_Portal.company.CompanyUtils;
import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
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

@RestController
@RequestMapping("/api/vi/company")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody RegCompanyRequest request) {
        try {
            CompanyResponse response = companyService.registerCompany(request);
            return ResponseEntity.ok(response);
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
}
