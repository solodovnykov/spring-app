package com.solodovnikov.service;

import com.solodovnikov.domain.Review;
import com.solodovnikov.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements AbstractService<Review, Integer> {

    public final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getById(Integer id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public Review create(Review newReview) {
        return reviewRepository.save(newReview);
    }

    @Override
    public Review update(Integer id, Review review) {
        if (reviewRepository.findById(id).isPresent()) {
            return reviewRepository.save(review);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (reviewRepository.findById(id).isPresent()) {
            reviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
