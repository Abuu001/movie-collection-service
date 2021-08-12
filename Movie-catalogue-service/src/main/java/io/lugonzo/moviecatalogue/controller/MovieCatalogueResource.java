package io.lugonzo.moviecatalogue.controller;

import io.lugonzo.moviecatalogue.model.CatalogueItem;
import io.lugonzo.moviecatalogue.model.Movie;
import io.lugonzo.moviecatalogue.model.Rating;
import io.lugonzo.moviecatalogue.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog")
public class MovieCatalogueResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId){

        UserRating Allrating = restTemplate.getForObject("http://localhost:3002/api/v1/ratingsdata/users/"+ userId, UserRating.class);

        return Allrating.getUserrating().stream().map(rating -> {
           Movie movie = restTemplate.getForObject("http://localhost:3003/api/v1/movies/"+ rating.getMovieId().toString(), Movie.class);

            //Alternative to restTemplate 👇👇
           /* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:3003/api/v1/movies/"+ rating.getMovieId().toString())
                    .retrieve()
                    .bodyToMono(Movie.class) ;
               */
             return new CatalogueItem(movie.getName(),"test123",rating.getRating());

        }).collect(Collectors.toList());
    }
}
