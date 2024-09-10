package com.embarkx.firstjobapp.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

	// private List<Job> jobs = new ArrayList<>();
	private Long id = 0L;

	JobRepository jr;

	public JobServiceImpl(JobRepository jr) {
		super();
		this.jr = jr;
	}

	@Override
	public List<Job> findAll() {

		return jr.findAll();
	}

	@Override
	public void createJob(Job job) {
		/*
		 * job.setId(++id); jobs.add(job);
		 */
		jr.save(job);

	}

	@Override
	public Job getJobById(Long id) {
		// Optional<Job> job = jobs.stream().filter(j ->
		// j.getId().equals(id)).findFirst();
		return jr.findById(id).orElse(null);
	}

	@Override
	public Boolean deleteJobById(Long id) {
		 
		//Optional<Job> job = jobs.stream().filter(j -> j.getId().equals(id)).findFirst();
		try {
		 jr.deleteById(id);
		 return true;
		}catch(Exception w) {
			return false;
		}

	}

	@Override
	public Boolean putById(Long id, Job job) {
		Optional<Job> jobs = jr.findById(id);
		System.out.println("Job foudb: "+jobs);
		//Optional<Job> josb = jobs.stream().filter(j -> j.getId().equals(id)).findFirst();
		if(jobs.isPresent()) {
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