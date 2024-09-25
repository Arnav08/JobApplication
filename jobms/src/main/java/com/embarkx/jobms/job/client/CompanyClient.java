package com.embarkx.jobms.job.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.embarkx.jobms.job.external.Company;

@FeignClient(name = "company-service", url = "${company-service.url}")
public interface CompanyClient {

	@GetMapping("/companies/{id}")
	Company getCompany(@PathVariable("id") Long id);

}
