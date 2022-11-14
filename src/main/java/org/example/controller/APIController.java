package org.example.controller;

import org.example.model.Actor;
import org.example.model.Movie;
import org.example.service.ActorService;
import org.example.service.MovieService;
import org.example.throttling.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpRetryException;
import java.util.List;

@CrossOrigin
@RestController
public class APIController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;

    @Autowired
    private MoviesBucket moviesBucket;
    @Autowired
    private MoviesByIdBucket moviesByIdBucket;
    @Autowired
    private ActorsBucket actorsBucket;
    @Autowired
    private ActorsByIdBucket actorsByIdBucket;
    @Autowired
    private AppearancesByActorIdBucket appearancesByActorIdBucket;

    @RequestMapping("/load")
    String hello() {
        return "Hello World!";
    }

    @RequestMapping("/movies")
    List<Movie> getMovies(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size, @RequestParam(required = false) String name) throws HttpRetryException {
        movieService.consumeBucketToken(moviesBucket);
        return movieService.getAll(page, size, name);
    }

    @RequestMapping("/movies/{id}")
    Movie getMovieById(@PathVariable String id) throws HttpRetryException {
        movieService.consumeBucketToken(moviesByIdBucket);
        return movieService.getById(id);
    }

    @RequestMapping("/actors")
    List<Actor> getActors(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size, @RequestParam(required = false) String name) throws HttpRetryException {
        actorService.consumeBucketToken(actorsBucket);
        return actorService.getAll(page, size, name);
    }

    @RequestMapping("/actors/{id}")
    Actor getActorById(@PathVariable String id) throws HttpRetryException {
        actorService.consumeBucketToken(actorsByIdBucket);
        return actorService.getById(id);
    }

    @RequestMapping("/actors/{id}/appearances")
    List<Movie> getMoviesByActor(@PathVariable String id, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size, @RequestParam(required = false) String name) throws HttpRetryException {
        movieService.consumeBucketToken(appearancesByActorIdBucket);
        return movieService.getByActorId(id, page, size, name);
    }


}
