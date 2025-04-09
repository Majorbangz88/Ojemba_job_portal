package com.big_joe.Ojemba_Job_Portal.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyInfo {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String regNumber;
    private int yearsOfExistence;
}
