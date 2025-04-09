package com.big_joe.Ojemba_Job_Portal.company.repository;

import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByEmail(String email);

    Optional<Company> findByName(String companyName);

    boolean existsByEmail(String email);
}
