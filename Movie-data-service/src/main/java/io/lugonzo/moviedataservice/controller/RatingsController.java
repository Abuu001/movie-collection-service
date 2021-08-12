package io.lugonzo.moviedataservice.controller;

import io.lugonzo.moviedataservice.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingsController {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        var movie1 = Rating.builder()
                .movieId(movieId)
                .rating(5)
                .build();

        return movie1;
    }
}
