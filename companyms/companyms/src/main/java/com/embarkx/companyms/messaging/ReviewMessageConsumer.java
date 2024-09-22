package com.embarkx.companyms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.embarkx.companyms.company.CompanyService;
import com.embarkx.companyms.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {
	
	private final CompanyService companyService;

	public ReviewMessageConsumer(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
	 companyService.updateCompanyRating(reviewMessage);
 }

}
