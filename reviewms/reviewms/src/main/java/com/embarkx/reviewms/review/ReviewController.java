package com.embarkx.reviewms.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private ReviewService rs;

	public ReviewController(ReviewService rs) {
		super();
		this.rs = rs;
	}

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
		return new ResponseEntity<>(rs.getAllReview(companyId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addReviews(@RequestParam Long companyId, @RequestBody Review Review) {
		if (rs.addReview(companyId, Review))
			return new ResponseEntity<>("Review Added Successfully !", HttpStatus.OK);
		else
			return new ResponseEntity<>("Review not saved !", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {

		Review resp = rs.getReview(reviewId);
		return new ResponseEntity<Review>(resp, HttpStatus.OK);

	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {

		Boolean ures = rs.updateReview(reviewId, review);
		if(ures) {
			return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Review Failed to Updated", HttpStatus.NOT_FOUND);	
		}
		

	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {

		Boolean ures = rs.deleteReview(reviewId);
		if(ures) {
			return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Review Failed to delete", HttpStatus.NOT_FOUND);	
		}
		

	}
	
	
}
