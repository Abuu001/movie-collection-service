package io.lugonzo.movieinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieSummary {
    private String title;
    private String overview;
 /*   private String language;
    private Integer popularity;
    private String status;
    private String voteCount;*/
}
