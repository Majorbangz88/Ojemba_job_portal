package com.big_joe.Ojemba_Job_Portal.company.service;

import com.big_joe.Ojemba_Job_Portal.company.CompanyUtils;
import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import com.big_joe.Ojemba_Job_Portal.company.model.CompanyInfo;
import com.big_joe.Ojemba_Job_Portal.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public CompanyResponse registerCompany(RegCompanyRequest request) {
        Optional<Company> optionalCompany = companyRepository.findByEmail(request.getEmail());

        if (optionalCompany.isPresent()) {
            CompanyResponse.builder()
                    .statusCode(CompanyUtils.COMPANY_EXISTS_CODE)
                    .message("This Company exists already!")
                    .registeredCompany(null)
                    .build();
        }

        Company registeredCoy = Company.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .regNumber(request.getRegNumber())
                .yearsOfExistence(request.getYearsOfExistence())
                .createdAt(LocalDateTime.now())
                .build();

        Company savedCompany = companyRepository.save(registeredCoy);

        return CompanyResponse.builder()
                .statusCode(CompanyUtils.COMPANY_REG_SUCCESS_CODE)
                .message("Company Registration Successful")
                .registeredCompany(CompanyInfo.builder()
                        .id(UUID.fromString(savedCompany.getPassword()))
                        .name(savedCompany.getName())
                        .address(savedCompany.getAddress())
                        .email(savedCompany.getEmail())
                        .phoneNumber(savedCompany.getPhoneNumber())
                        .regNumber(savedCompany.getRegNumber())
                        .yearsOfExistence(savedCompany.getYearsOfExistence())
                        .build())
                .build();
    }
}
