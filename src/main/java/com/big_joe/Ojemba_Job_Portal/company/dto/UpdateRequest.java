package com.big_joe.Ojemba_Job_Portal.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRequest {

    private String name;
    private String address;
    private String email;
    private String password;
    private String phoneNumber;
    private String regNumber;
    private int yearsOfExistence;
}
