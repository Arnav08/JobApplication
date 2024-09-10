package com.embarkx.firstjobapp.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService js;

	public JobController(JobService js) {
		super();
		this.js = js;
	}

	@GetMapping
	public ResponseEntity<List<Job>> findAll() {

		return ResponseEntity.ok(js.findAll());
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		js.createJob(job);
		return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobbyId(@PathVariable Long id) {
		if (null == js.getJobById(id))
			return new ResponseEntity<Job>(js.getJobById(id), HttpStatus.NOT_FOUND);
		return new ResponseEntity<Job>(js.getJobById(id), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
		if (js.deleteJobById(id))
			return new ResponseEntity<String>("Job deleted successfully", HttpStatus.OK);
		return new ResponseEntity<String>("Job not fond !!", HttpStatus.NOT_FOUND);

	}

	
	@PutMapping("/{id}")
	public ResponseEntity<String> putJobById(@PathVariable Long id, @RequestBody Job job) {
		if (js.putById(id, job))
			return new ResponseEntity<String>("Job Updated successfully", HttpStatus.OK);
		return new ResponseEntity<String>("Job not fond !!", HttpStatus.NOT_FOUND);

	}

	
	
	
}
