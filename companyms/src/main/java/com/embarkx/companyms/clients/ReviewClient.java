package com.embarkx.companyms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("review-service")
public interface ReviewClient {

	@GetMapping("/reviews/averageRating")
	Double getAvarageRatingForCompany(@RequestParam("companyId") Long companyId);
}
