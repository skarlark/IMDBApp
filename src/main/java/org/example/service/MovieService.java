package org.example.service;

import org.example.model.Movie;
import org.example.throttling.APIThrottling;

import java.util.List;

public interface MovieService extends APIThrottling {

    List<Movie> getAll(int page, int size, String wildCardTitle);

    Movie getById(String id);

    List<Movie> getByActorId(String actorId, int page, int size, String wildCardMovie);
}
