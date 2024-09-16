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
import com.embarkx.jobms.job.client.CompanyClient;
import com.embarkx.jobms.job.client.ReviewClient;
import com.embarkx.jobms.job.dto.JobDTO;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;
import com.embarkx.jobms.job.mapper.JobMapper;

@Service
public class JobServiceImpl implements JobService {
	
	JobRepository jr;
	
	@Autowired
	RestTemplate restTemplate; 
	private CompanyClient companyClient;
	private ReviewClient reviewClient;
	
	public JobServiceImpl(JobRepository jr,CompanyClient companyClient, ReviewClient reviewClient) {
		super();
		this.jr = jr;
		this.companyClient = companyClient;
		this.reviewClient = reviewClient;
	}


	
	@Override
	public List<JobDTO> findAll() {

		List<Job> jobs = jr.findAll();
		return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private JobDTO convertToDTO(Job job) {
		 
		System.out.println("get conp ID"+job.getCompanyId());
		Company com = companyClient.getCompany(job.getCompanyId());
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		/*
		Company com = restTemplate.getForObject("http://company-service:8081/companies/" + job.getCompanyId(), Company.class);
		
		ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://review-service:8083/reviews?companyId=" + job.getCompanyId(), 
				HttpMethod.GET,
				null, 
				new ParameterizedTypeReference<List<Review>>() {
		});
		List<Review> reviews = reviewResponse.getBody();
		*/
		
		JobDTO jto = JobMapper.jobWithCompanyDTOMap(job, com, reviews);                
		return jto;
	}

	@Override
	public void createJob(Job job) {
		/*
		 * job.setId(++id); jobs.add(job);
		 */
		jr.save(job);

	}

	@Override
	public JobDTO getJobById(Long id) {
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