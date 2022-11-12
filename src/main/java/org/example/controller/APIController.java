package org.example.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.example.model.Actor;
import org.example.model.Movie;
import org.example.service.ActorService;
import org.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpRetryException;
import java.time.Duration;
import java.util.List;

@CrossOrigin
@RestController
public class APIController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;

    private final Bucket bucket;

    public APIController() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    private void consumeBucketToken() throws HttpRetryException {
        if(!bucket.tryConsume(1))
            throw new HttpRetryException("Too many requests. Rate limit exceeded.", 429);
    }

    @RequestMapping("/load")
    String hello() {
        return "Hello World!";
    }

    @RequestMapping("/movies")
    List<Movie> getMovies(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size, @RequestParam(required = false) String name) throws HttpRetryException {
        consumeBucketToken();
        return movieService.getAll(page, size, name);
    }

    @RequestMapping("/movies/{id}")
    Movie getMovieById(@PathVariable String id) {
        return movieService.getById(id);
    }

    @RequestMapping("/actors")
    List<Actor> getActors(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size, @RequestParam(required = false) String name) {
        return actorService.getAll(page, size, name);
    }

    @RequestMapping("/actors/{id}")
    Actor getActorById(@PathVariable String id) {
        return actorService.getById(id);
    }


}
