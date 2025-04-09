package com.big_joe.Ojemba_Job_Portal.company.dto;

import com.big_joe.Ojemba_Job_Portal.company.model.CompanyInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    private String statusCode;
    private String message;
    private CompanyInfo registeredCompany;
}
