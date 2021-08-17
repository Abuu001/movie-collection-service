package io.lugonzo.moviecatalogue.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.lugonzo.moviecatalogue.model.CatalogueItem;
import io.lugonzo.moviecatalogue.model.Movie;
import io.lugonzo.moviecatalogue.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItemMthd")
    public CatalogueItem getCatalogItem(Rating rating){

        //Alternative to restTemplate 👇👇
           /* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:3003/api/v1/movies/"+ rating.getMovieId().toString())
                    .retrieve()
                    .bodyToMono(Movie.class) ;
               */
        Movie movie = restTemplate.getForObject("http://movie-info-service/api/v1/movies/"+ rating.getMovieId().toString(), Movie.class);
        return new CatalogueItem(movie.getName(),
                movie.getDescription(),
                rating.getRating(),
                movie.getLanguage(),
                movie.getPopularity(),
                movie.getStatus(),
                movie.getVoteCount());
    }

    //this method runs as fallback whenever the circuit breaks
    public CatalogueItem getFallbackCatalogItemMthd(Rating rating){
        return CatalogueItem.builder()
                .name("Movie name not found")
                .description("")
                .rating(rating.getRating())
                .build();
    }
}
