package com.big_joe.Ojemba_Job_Portal.company.service;

import com.big_joe.Ojemba_Job_Portal.company.CompanyUtils;
import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import com.big_joe.Ojemba_Job_Portal.company.model.CompanyInfo;
import com.big_joe.Ojemba_Job_Portal.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
                        .id(savedCompany.getPassword())
                        .name(savedCompany.getName())
                        .address(savedCompany.getAddress())
                        .email(savedCompany.getEmail())
                        .phoneNumber(savedCompany.getPhoneNumber())
                        .regNumber(savedCompany.getRegNumber())
                        .yearsOfExistence(savedCompany.getYearsOfExistence())
                        .build())
                .build();
    }

    @Override
    public List<Company> allCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyResponse getByCompanyName(String companyName) {
        Optional<Company> optionalCompany = companyRepository.findByName(companyName);

        if(optionalCompany.isEmpty()) {
            CompanyResponse.builder()
                    .statusCode(CompanyUtils.COMPANY_NOT_EXIST_CODE)
                    .message("This company data was not found")
                    .registeredCompany(null)
                    .build();
        }

        Company foundCompany = optionalCompany.get();

        return CompanyResponse.builder()
                .statusCode(CompanyUtils.COMPANY_EXISTS_CODE)
                .message("Company found")
                .registeredCompany(CompanyInfo.builder()
                        .id(foundCompany.getId().toString())
                        .name(foundCompany.getName())
                        .address(foundCompany.getAddress())
                        .email(foundCompany.getEmail())
                        .phoneNumber(foundCompany.getPhoneNumber())
                        .regNumber(foundCompany.getRegNumber())
                        .yearsOfExistence(foundCompany.getYearsOfExistence())
                        .build())
                .build();
    }


}
