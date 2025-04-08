package com.big_joe.Ojemba_Job_Portal.job.model;

import com.big_joe.Ojemba_Job_Portal.company.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String location;
    private String qualification;
    private String requirements;
    private String salary;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;
}
