package com.big_joe.Ojemba_Job_Portal.job.service;

import com.big_joe.Ojemba_Job_Portal.job.dto.JobRequest;
import com.big_joe.Ojemba_Job_Portal.job.dto.JobResponse;
import com.big_joe.Ojemba_Job_Portal.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;

    @Override
    public JobResponse postJob(JobRequest jobRequest) {

        return null;
    }
}
