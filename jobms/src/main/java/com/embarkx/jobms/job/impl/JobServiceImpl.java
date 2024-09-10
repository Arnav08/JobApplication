package com.embarkx.jobms.job.impl;

import java.util.List;
 
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.JobRepository;
import com.embarkx.jobms.job.JobService;
import com.embarkx.jobms.job.dto.JobWithCompanyDTO;
import com.embarkx.jobms.job.external.Company;

@Service
public class JobServiceImpl implements JobService {
	
	JobRepository jr;

	public JobServiceImpl(JobRepository jr) {
		super();
		this.jr = jr;
	}


	@Autowired
	RestTemplate restTemplate; 
	
	@Override
	public List<JobWithCompanyDTO> findAll() {

		List<Job> jobs = jr.findAll();
		return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private JobWithCompanyDTO convertToDTO(Job job) {
		JobWithCompanyDTO jwc = new JobWithCompanyDTO();
		System.out.println("get conp ID"+job.getCompanyId());
		Company com = restTemplate.getForObject("http://company-service:8081/companies/" + job.getCompanyId(), Company.class);
		jwc.setJob(job);
		jwc.setCompany(com);                  
		return jwc;
	}

	@Override
	public void createJob(Job job) {
		/*
		 * job.setId(++id); jobs.add(job);
		 */
		jr.save(job);

	}

	@Override
	public JobWithCompanyDTO getJobById(Long id) {
		Job job= jr.findById(id).orElse(null);
		return convertToDTO(job);
	}

	@Override
	public Boolean deleteJobById(Long id) {

		// Optional<Job> job = jobs.stream().filter(j ->
		// j.getId().equals(id)).findFirst();
		try {
			jr.deleteById(id);
			return true;
		} catch (Exception w) {
			return false;
		}

	}

	@Override
	public Boolean putById(Long id, Job job) {
		Optional<Job> jobs = jr.findById(id);
		System.out.println("Job foudb: " + jobs);
		// Optional<Job> josb = jobs.stream().filter(j ->
		// j.getId().equals(id)).findFirst();
		if (jobs.isPresent()) {
			Job jb = jobs.get();
			jb.setDescription(job.getDescription());
			jb.setLocation(job.getLocation());
			jb.setMaxSalary(job.getMaxSalary());
			jb.setMinSalary(job.getMinSalary());
			jb.setTitle(job.getTitle());
			jr.save(jb);
			return true;
		}
		return false;

	}
}