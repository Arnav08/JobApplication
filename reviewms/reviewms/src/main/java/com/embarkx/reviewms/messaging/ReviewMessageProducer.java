package com.embarkx.reviewms.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.embarkx.reviewms.dto.ReviewMessage;
import com.embarkx.reviewms.review.Review;

@Service
public class ReviewMessageProducer {

	private final RabbitTemplate rabbitTempplate;

	public ReviewMessageProducer(RabbitTemplate rabbitTempplate) {
		this.rabbitTempplate = rabbitTempplate;
	}

	public void sendMessage(Review review) {

		ReviewMessage reviewMsg = new ReviewMessage();
		reviewMsg.setCompany(review.getCompanyId());
		reviewMsg.setDescription(review.getDescription());
		reviewMsg.setId(review.getId());
		reviewMsg.setRating(review.getRating());
		reviewMsg.setTitle(review.getTitle());
		rabbitTempplate.convertAndSend("companyRatingQueue", reviewMsg);
	}
}
