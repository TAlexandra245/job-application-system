package com.job_application_system.job_application_system.review.impl;

import com.job_application_system.job_application_system.company.Company;
import com.job_application_system.job_application_system.company.CompanyRepository;
import com.job_application_system.job_application_system.company.CompanyService;
import com.job_application_system.job_application_system.review.Review;
import com.job_application_system.job_application_system.review.ReviewRepository;
import com.job_application_system.job_application_system.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long id, Review review) {
        Company company = companyService.findById(id);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long id, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(id);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);

    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.findById(companyId) != null) {
            updatedReview.setCompany(companyService.findById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (optionalCompany.isPresent() && reviewOptional.isPresent()) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = optionalCompany.get();
            company.getReviewList().remove(review);
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
