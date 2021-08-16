package io.lugonzo.moviecatalogue.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "getFallbackCatalogMthd") //is circuit breaker that breaks whenever an error has occurred .prevents whole system go down when there is a prob in one microservice
    public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId){

        UserRating Allrating = restTemplate.getForObject("http://movie-ratings-data-service/api/v1/ratingsdata/"+ userId, UserRating.class);
        System.out.println("==============================================");
        System.out.println(Allrating);
        return Allrating.getUserrating().stream().map(rating -> {
           Movie movie = restTemplate.getForObject("http://movie-info-service/api/v1/movies/"+ rating.getMovieId().toString(), Movie.class);

            //Alternative to restTemplate 👇👇
           /* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:3003/api/v1/movies/"+ rating.getMovieId().toString())
                    .retrieve()
                    .bodyToMono(Movie.class) ;
               */
             return new CatalogueItem(movie.getName(),
                     movie.getDescription(),
                     rating.getRating(),
                     movie.getLanguage(),
                     movie.getPopularity(),
                     movie.getStatus(),
                     movie.getVoteCount());

        }).collect(Collectors.toList());
    }

    //this method runs as fallback whenever the circuit breaks
    public List<CatalogueItem> getFallbackCatalogMthd(@PathVariable("userId") String userId){
        return Arrays.asList(new CatalogueItem("No movie","",0,"N/A",0,"N/A","N/A"));
    }
}
