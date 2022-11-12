package org.example.service;

import org.example.model.Movie;
import org.example.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> getAll(int page, int size, String wildCardTitle) {
        if (size > 1000)
            throw new IllegalArgumentException("Page Size cannot be greater than 1000");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("primaryTitle"));
        List<Movie> movies = new ArrayList<>();
        boolean isValidWildcard = false;
        if (isValidWildcard) {
            movieRepository.findByNameLike(wildCardTitle).forEach(movie -> movies.add(new Movie(movie.gettConst(), movie.getPrimaryTitle(), String.valueOf(movie.getStartYear()))));
        } else {
            movieRepository.findAll(pageRequest).get().forEach(movie -> movies.add(new Movie(movie.gettConst(), movie.getPrimaryTitle(), String.valueOf(movie.getStartYear()))));
        }
        return movies;
    }


    @Override
    public Movie getById(String id) {
        return Movie.toMovie(movieRepository.findById(id).get());
    }
}
