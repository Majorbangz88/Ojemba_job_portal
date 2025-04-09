package com.big_joe.Ojemba_Job_Portal.company.service;

import com.big_joe.Ojemba_Job_Portal.company.CompanyUtils;
import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.dto.UpdateRequest;
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
            return CompanyResponse.builder()
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

        return CompanyUtils.buildCompanyResponse(CompanyUtils.COMPANY_REG_SUCCESS_CODE, "Company Registration Successful", savedCompany);
    }

    @Override
    public List<Company> allCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyResponse getByCompanyName(String companyName) {
        Optional<Company> optionalCompany = companyRepository.findByName(companyName);

        if(optionalCompany.isEmpty()) {
            return CompanyUtils.buildCompanyResponse(CompanyUtils.COMPANY_NOT_EXIST_CODE, "This company data was not found", null);
        }

        Company foundCompany = optionalCompany.get();

        return CompanyUtils.buildCompanyResponse(CompanyUtils.COMPANY_EXISTS_CODE, "Company found", foundCompany);
    }

    @Override
    public CompanyResponse updateCompany(String id, UpdateRequest request) {
        UUID uuid;

        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return CompanyUtils.buildCompanyResponse(CompanyUtils.INVALID_ID_FORMAT_CODE, CompanyUtils.INVALID_ID_FORMAT_MESSAGE, null);
        }

        Optional<Company> optionalCompany = companyRepository.findById(uuid);

        if (optionalCompany.isEmpty()) {
            return CompanyUtils.buildCompanyResponse(CompanyUtils.COMPANY_NOT_EXIST_CODE, CompanyUtils.COMPANY_NOT_EXISTS_MESSAGE, null);
        }

        Company foundCompany = optionalCompany.get();

        if (!foundCompany.getEmail().equals(request.getEmail()) &&
                companyRepository.existsByEmail(request.getEmail())) {

            return CompanyUtils.buildCompanyResponse(CompanyUtils.COMPANY_EXISTS_CODE, CompanyUtils.COMPANY_EXISTS_MESSAGE, null);
        }

        foundCompany.setName(request.getName());
        foundCompany.setAddress(request.getAddress());
        foundCompany.setEmail(request.getEmail());
        foundCompany.setPhoneNumber(request.getPhoneNumber());
        foundCompany.setRegNumber(request.getRegNumber());
        foundCompany.setYearsOfExistence(request.getYearsOfExistence());

        Company updatedCompany = companyRepository.save(foundCompany);

         return CompanyUtils.buildCompanyResponse(CompanyUtils.UPDATE_SUCCESSFUL_CODE, CompanyUtils.UPDATE_SUCCESSFUL_MESSAGE, updatedCompany);
    }


}
