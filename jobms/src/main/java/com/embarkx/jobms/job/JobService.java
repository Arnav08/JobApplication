package com.embarkx.jobms.job;

import java.util.List;

import com.embarkx.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {

	List<JobWithCompanyDTO> findAll();

	void createJob(Job job);

	JobWithCompanyDTO getJobById(Long id);

	Boolean deleteJobById(Long id);

	Boolean putById(Long id, Job job);

}
