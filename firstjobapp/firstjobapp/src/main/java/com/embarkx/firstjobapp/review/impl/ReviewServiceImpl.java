package com.embarkx.firstjobapp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository rp;
	private CompanyService cs;

	 

	public ReviewServiceImpl(ReviewRepository rp, CompanyService cs) {
		super();
		this.rp = rp;
		this.cs = cs;
	}

	@Override
	public List<Review> getAllReview(Long compid) {

		List<Review> listofreview = rp.findByCompanyId(compid);
		return listofreview;
	}

	@Override
	public Boolean addReview(Long compId, Review review) {
		Company comp = cs.getCompanyById(compId);
		if(comp != null) {
			review.setCompany(comp);
			rp.save(review);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Review getReview(Long companyId, Long reviewId) {
		List<Review> reviews = rp.findByCompanyId(companyId);
		return reviews.stream().filter(r -> r.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		if (cs.getCompanyById(companyId) !=  null) {
			updatedReview.setCompany(cs.getCompanyById(companyId));
			updatedReview.setId(reviewId);
			rp.save(updatedReview);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Boolean deleteReview(Long companyId, Long reviewId) {
		if(cs.getCompanyById(companyId) != null && rp.existsById(reviewId)){
			Review review = rp.findById(reviewId).orElse(null);
			Company  comp = review.getCompany();
			comp.getReviews().remove(review);
			cs.updateCompany(comp, companyId);
			rp.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
