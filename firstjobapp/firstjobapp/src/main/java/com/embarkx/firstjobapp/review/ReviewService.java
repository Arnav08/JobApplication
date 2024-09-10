package com.embarkx.firstjobapp.review;

import java.util.List;

public interface ReviewService {

	List<Review> getAllReview(Long compid);

	Boolean addReview(Long compId, Review review);

	Review getReview(Long companyId, Long reviewId);

	Boolean updateReview(Long companyId, Long reviewId, Review review);

	Boolean deleteReview(Long companyId, Long reviewId);

}
 