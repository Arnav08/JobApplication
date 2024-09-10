package com.embarkx.reviewms.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.reviewms.review.Review;
import com.embarkx.reviewms.review.ReviewRepository;
import com.embarkx.reviewms.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository rp;

	public ReviewServiceImpl(ReviewRepository rp) {
		super();
		this.rp = rp;
	}

	@Override
	public List<Review> getAllReview(Long compid) {

		List<Review> listofreview = rp.findByCompanyId(compid);
		return listofreview;
	}

	@Override
	public Boolean addReview(Long compId, Review review) {
		if (compId != null && review != null) {
			review.setCompanyId(compId);
			rp.save(review);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Review getReview(Long reviewId) {
		return rp.findById(reviewId).orElse(null);
		 
	}

	@Override
	public Boolean updateReview(Long revId, Review updatedReview) {
		Review review = rp.findById(revId).orElse(null);
		if (review != null) {
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating()); 
			review.setCompanyId(updatedReview.getCompanyId());
			rp.save(updatedReview);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Boolean deleteReview(Long reviewId) {
		Review review = rp.findById(reviewId).orElse(null);
		if (review != null) {
			rp.delete(review);
			return true;
		}
		return false;
	}

}
