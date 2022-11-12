package org.example.service;

import org.example.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(int page, int size, String wildCardTitle);

    Movie getById(String id);
}
