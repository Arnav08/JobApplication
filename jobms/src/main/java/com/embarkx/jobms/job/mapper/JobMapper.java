package com.embarkx.jobms.job.mapper;

import java.util.List;

import com.embarkx.jobms.job.Job;
import com.embarkx.jobms.job.dto.JobDTO;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;

public class JobMapper {

	public static JobDTO jobWithCompanyDTOMap(Job job, Company company, List<Review> reviews) {
		JobDTO jobWithCompanyDTO = new JobDTO();
		jobWithCompanyDTO.setId(job.getId());
		jobWithCompanyDTO.setTitle(job.getDescription());
		jobWithCompanyDTO.setDescription(job.getDescription());
		jobWithCompanyDTO.setLocation(job.getLocation());
		jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDTO.setMinSalary(job.getMinSalary());
		jobWithCompanyDTO.setCompany(company);
		jobWithCompanyDTO.setReview(reviews);
		return jobWithCompanyDTO;
	}
}
