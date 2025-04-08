package com.big_joe.Ojemba_Job_Portal.company.model;

import com.big_joe.Ojemba_Job_Portal.job.model.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String regNumber;
    private int yearsOfExistence;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();
}
