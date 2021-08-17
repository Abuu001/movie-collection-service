package io.lugonzo.moviecatalogue.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.lugonzo.moviecatalogue.model.CatalogueItem;
import io.lugonzo.moviecatalogue.model.Movie;
import io.lugonzo.moviecatalogue.model.Rating;
import io.lugonzo.moviecatalogue.model.UserRating;
import io.lugonzo.moviecatalogue.service.MovieInfoService;
import io.lugonzo.moviecatalogue.service.UserRatingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog")
public class MovieCatalogueResource {

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingInfoService userRatingInfoService;

    @GetMapping("/{userId}")
    public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId){

        UserRating userRating = userRatingInfoService.getUserRating(userId);

        return userRating.getUserrating().stream()
                .map(rating -> movieInfoService.getCatalogItem(rating)
        ).collect(Collectors.toList());
    }

}

