package com.embarkx.firstjobapp.job;

import java.util.List;

public interface JobService {

	List<Job> findAll();
	void createJob(Job job);
	Job getJobById(Long id);
	Boolean deleteJobById(Long id);
	Boolean putById(Long id, Job job);
	
	
}
