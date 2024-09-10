package com.embarkx.reviewms.review;

import java.util.List;

public interface ReviewService {

	List<Review> getAllReview(Long compid);

	Boolean addReview(Long compId, Review review);

	Boolean updateReview(Long revId, Review updatedReview);

	Boolean deleteReview(Long reviewId);

	Review getReview(Long reviewId);

}
