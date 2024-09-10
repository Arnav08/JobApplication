package com.embarkx.firstjobapp.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	private ReviewService rs;

	public ReviewController(ReviewService rs) {
		super();
		this.rs = rs;
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
		return new ResponseEntity<>(rs.getAllReview(companyId), HttpStatus.OK);
	}

	@PostMapping("/reviews")
	public ResponseEntity<String> addReviews(@PathVariable Long companyId, @RequestBody Review Review) {
		if (rs.addReview(companyId, Review))
			return new ResponseEntity<>("Review Added Successfully !", HttpStatus.OK);
		else
			return new ResponseEntity<>("Review not saved !", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {

		Review resp = rs.getReview(companyId, reviewId);
		return new ResponseEntity<Review>(resp, HttpStatus.OK);

	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {

		Boolean ures = rs.updateReview(companyId, reviewId, review);
		if(ures) {
			return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Review Failed to Updated", HttpStatus.NOT_FOUND);	
		}
		

	}

	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {

		Boolean ures = rs.deleteReview(companyId, reviewId);
		if(ures) {
			return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Review Failed to delete", HttpStatus.NOT_FOUND);	
		}
		

	}
	
	
}
