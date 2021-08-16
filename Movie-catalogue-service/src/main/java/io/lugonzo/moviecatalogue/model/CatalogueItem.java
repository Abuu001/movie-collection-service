package io.lugonzo.moviecatalogue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogueItem {

    private String name;
    private String description;
    private Integer rating;
    private String language;
    private Integer popularity;
    private String status;
    private String voteCount;
}
