package io.lugonzo.moviecatalogue.controller;

import io.lugonzo.moviecatalogue.model.CatalogueItem;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class MovieCatalogueResource {


    @GetMapping
    public List<CatalogueItem> getCatalogue(String userId){

        var movie1=CatalogueItem.builder()
                .name("Fast and Furious 9")
                .description("Action")
                .rating(4)
                .build();

        var movie2=CatalogueItem.builder()
                .name("Transformer")
                .description("Action")
                .rating(3)
                .build();

        return List.of(movie1,movie2);
    }
}
