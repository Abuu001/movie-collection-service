package io.lugonzo.moviecatalogue.model;

 import lombok.AllArgsConstructor;
 import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 import java.util.Arrays;
 import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRating {

    private  String userId;
    private List<Rating> userrating= Arrays.asList(
            new Rating("100",3),
            new Rating("200",4),
            new Rating("300",5),
            new Rating("400",6)
    );
}
