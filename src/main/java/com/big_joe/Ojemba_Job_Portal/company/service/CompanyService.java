package com.big_joe.Ojemba_Job_Portal.company.service;

import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;

public interface CompanyService {

    CompanyResponse registerCompany(RegCompanyRequest request);
}
