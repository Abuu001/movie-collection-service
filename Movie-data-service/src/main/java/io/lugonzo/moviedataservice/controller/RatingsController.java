package io.lugonzo.moviedataservice.controller;

import io.lugonzo.moviedataservice.model.Rating;
import io.lugonzo.moviedataservice.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/ratingsdata")
public class RatingsController {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        var movie1 = Rating.builder()
                .movieId(movieId)
                .rating(5)
                .build();

        return movie1;
    }

    @GetMapping("/users/{usersId}")
    public UserRating getAllRatings(@PathVariable("usersId") String usersId){
        var movie1 = Rating.builder()
                .movieId("1234")
                .rating(6)
                .build();

        var movie2 = Rating.builder()
                .movieId("5678")
                .rating(7)
                .build();

        var userRating = UserRating.builder()
                .userrating(Arrays.asList(movie1,movie2))
                .build();

        return userRating;
    }
}
