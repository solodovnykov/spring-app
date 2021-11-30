package com.solodovnikov.dto;

import com.solodovnikov.domain.Country;
import com.solodovnikov.domain.Review;

public class HotelDto {
    private Integer id;

    private String name;

    private Review review;

    private Country country;

    public HotelDto() {

    }

    public HotelDto(Integer id, String name, Review review, Country country) {
        this.id = id;
        this.name = name;
        this.review = review;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
