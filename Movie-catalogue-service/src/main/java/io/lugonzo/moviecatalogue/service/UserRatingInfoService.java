package io.lugonzo.moviecatalogue.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.lugonzo.moviecatalogue.model.Rating;
import io.lugonzo.moviecatalogue.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatingMthd",
                    commandProperties = {
                            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
                    }
    ) //is circuit breaker that breaks whenever an error has occurred .prevents whole system go down when there is a prob in one microservice
    public UserRating getUserRating(@PathVariable("userId") String userId){
        return restTemplate.getForObject("http://movie-ratings-data-service/api/v1/ratingsdata/users/"+ userId, UserRating.class);
    }

    //this method runs as fallback whenever the circuit breaks
    public UserRating getFallbackUserRatingMthd(@PathVariable("userId") String userId) {
        var userRating = UserRating.builder()
                .userId(userId)
                .userrating(Arrays.asList( new Rating("0",0)))
                .build();

        return  userRating;
    }
}
