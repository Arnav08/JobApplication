package com.embarkx.jobms.job.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.embarkx.jobms.job.external.Review;

@FeignClient(name = "review-service")
public interface ReviewClient {

	@GetMapping("/reviews")
	List<Review> getReviews(@RequestParam("companyId") Long id);

}
