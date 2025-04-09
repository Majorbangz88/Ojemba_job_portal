package com.big_joe.Ojemba_Job_Portal.company.service;

import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse registerCompany(RegCompanyRequest request);

    List<Company> allCompanies();

    CompanyResponse getByCompanyName(String companyName);
}
