package com.job_application_system.job_application_system.review;

import java.util.List;

public interface ReviewService {
List<Review> getAllReviews(Long companyId);

boolean createReview(Long id, Review review);

Review getReview(Long id, Long reviewId);

boolean updateReview(Long companyId, Long reviewId, Review review);
}
