package com.rating.service.services;

import com.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    // operations

    // create rating
    Rating create(Rating rating);
    // get all rating
    List<Rating> getAllRatings();
    // get rating by user_id
    List<Rating> getRatingByUserId(String userId);
    // get rating by hotel_id
    List<Rating> getRatingByHotelId(String hotelId);
}
