package com.big_joe.Ojemba_Job_Portal.job.repository;

import com.big_joe.Ojemba_Job_Portal.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
}
