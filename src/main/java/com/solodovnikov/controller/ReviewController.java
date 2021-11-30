package com.solodovnikov.controller;

import com.solodovnikov.domain.Review;
import com.solodovnikov.dto.ReviewDto;
import com.solodovnikov.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/reviews")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDto>> getAll() {
        List<Review> reviews = reviewService.getAll();
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDto reviewDto = new ReviewDto(
                    review.getId(),
                    review.getText(),
                    review.getDate()
            );
            reviewDtos.add(reviewDto);
        }
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable Integer id) {
        Review review;
        try {
            review = reviewService.getById(id);
            ReviewDto reviewDto = new ReviewDto(
                    review.getId(),
                    review.getText(),
                    review.getDate()
            );
            return new ResponseEntity<>(reviewDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Review newReview) {
        reviewService.create(newReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ReviewDto> update(@PathVariable Integer id, @RequestBody Review review) {
        Review reviewOld;
        try {
            reviewOld = reviewService.getById(id);
            if (reviewOld != null) {
                reviewService.update(id, review);
                ReviewDto reviewOldDto = new ReviewDto(
                        review.getId(),
                        review.getText(),
                        review.getDate()
                );
                return new ResponseEntity<>(reviewOldDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (reviewService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
