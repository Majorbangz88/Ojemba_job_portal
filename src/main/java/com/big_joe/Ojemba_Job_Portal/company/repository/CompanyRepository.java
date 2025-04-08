package com.big_joe.Ojemba_Job_Portal.company.repository;

import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
