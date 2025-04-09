package com.big_joe.Ojemba_Job_Portal.company;

import com.big_joe.Ojemba_Job_Portal.company.dto.CompanyResponse;
import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import com.big_joe.Ojemba_Job_Portal.company.model.CompanyInfo;

public class CompanyUtils {
    public static final String COMPANY_EXISTS_CODE = "001";
    public static final String COMPANY_REG_SUCCESS_CODE = "OO2";
    public static final String COMPANY_NOT_EXIST_CODE = "003";
    public static final String UPDATE_SUCCESSFUL_CODE = "004";
    public static final String INVALID_ID_FORMAT_CODE = "005";
    public static final String INVALID_ID_FORMAT_MESSAGE = "Invalid company ID format";
    public static final String COMPANY_EXISTS_MESSAGE = "A company with this email already exists";
    public static final String COMPANY_NOT_EXISTS_MESSAGE = "Company Not Found";
    public static final String UPDATE_SUCCESSFUL_MESSAGE = "Company details updated successfully";
    public static final String COMPANY_DELETION_CODE = "006";
    public static final String COMPANY_DELETION_MESSAGE = "Company deleted successfully";

    public static CompanyResponse buildCompanyResponse(
            String statusCode,
            String message,
            Company company) {

        return CompanyResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .registeredCompany(company != null ? CompanyInfo.builder()
                        .id(company.getId().toString())
                        .name(company.getName())
                        .address(company.getAddress())
                        .email(company.getEmail())
                        .phoneNumber(company.getPhoneNumber())
                        .regNumber(company.getRegNumber())
                        .yearsOfExistence(company.getYearsOfExistence())
                        .build() : null)
                .build();
    }
}
