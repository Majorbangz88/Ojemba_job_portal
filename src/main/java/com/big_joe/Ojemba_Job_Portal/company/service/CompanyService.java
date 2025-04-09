package com.big_joe.Ojemba_Job_Portal.company.service;

import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.dto.UpdateRequest;
import com.big_joe.Ojemba_Job_Portal.company.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    CompanyResponse registerCompany(RegCompanyRequest request);

    List<Company> allCompanies();

    CompanyResponse getByCompanyName(String companyName);

    CompanyResponse fullUpdateCompany(String id, UpdateRequest request);

    CompanyResponse partialUpdateCompany(String id, UpdateRequest request);

    CompanyResponse deleteCompany(UUID uuid);
}
