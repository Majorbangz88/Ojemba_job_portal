package com.big_joe.Ojemba_Job_Portal.job.service;

import com.big_joe.Ojemba_Job_Portal.job.dto.JobRequest;
import com.big_joe.Ojemba_Job_Portal.job.dto.JobResponse;

public interface JobService {

    JobResponse postJob(JobRequest jobRequest);
}
