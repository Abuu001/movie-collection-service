package io.lugonzo.movieinfoservice.controller;

import io.lugonzo.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        var movie1 = Movie.builder()
                .movieId(movieId)
                .name("The CHI")
                .build();

        return movie1;
    }
}
