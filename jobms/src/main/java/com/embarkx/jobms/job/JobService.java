package com.embarkx.jobms.job;

import java.util.List;

import com.embarkx.jobms.job.dto.JobDTO;

public interface JobService {

	List<JobDTO> findAll();

	void createJob(Job job);

	JobDTO getJobById(Long id);

	Boolean deleteJobById(Long id);

	Boolean putById(Long id, Job job);

}
