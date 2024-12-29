package com.user.service.external.services;

import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    // GET (getting

    // POST (for creating)
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating values);

    // DELETE (for deleting)
    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);

    // PUT (for updating)
    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);
}
