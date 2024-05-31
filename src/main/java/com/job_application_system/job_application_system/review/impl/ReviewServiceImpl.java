package com.job_application_system.job_application_system.review.impl;

import com.job_application_system.job_application_system.review.Review;
import com.job_application_system.job_application_system.review.ReviewRepository;
import com.job_application_system.job_application_system.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }
}
