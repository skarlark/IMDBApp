package org.example.service;

import org.example.dao.Appearance;
import org.example.model.Movie;
import org.example.repository.AppearanceRepository;
import org.example.repository.MovieRepository;
import org.example.throttling.APIBucket;
import org.example.throttling.DefaultAPIBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private AppearanceRepository appearanceRepository;

    @Override
    public List<Movie> getAll(int page, int size, String wildCardTitle) {
        if (size > 1000)
            throw new IllegalArgumentException("Page Size cannot be greater than 1000");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("primaryTitle"));
        List<Movie> movies = new ArrayList<>();
        boolean isValidWildcard = wildCardTitle != null && !wildCardTitle.trim().isEmpty();
        if (isValidWildcard) {
            movieRepository.findByNameLike(pageRequest, wildCardTitle).forEach(movie -> movies.add(new Movie(movie.gettConst(), movie.getPrimaryTitle(), String.valueOf(movie.getStartYear()))));
        } else {
            movieRepository.findAll(pageRequest).get().forEach(movie -> movies.add(new Movie(movie.gettConst(), movie.getPrimaryTitle(), String.valueOf(movie.getStartYear()))));
        }
        return movies;
    }


    @Override
    public Movie getById(String id) {
        return Movie.toMovie(movieRepository.findById(id).orElseThrow(NullPointerException::new));
    }

    @Override
    public List<Movie> getByActorId(String actorId, int page, int size, String wildCardMovie) {
        List<Movie> movies = new ArrayList<>();
        for (Appearance appearance : appearanceRepository.findByActorId(actorId)) {
            if (appearance.getMovieLite() != null) {
                movies.add(Movie.toMovie(appearance.getMovieLite()));
            }
        }
        return movies;
    }

    @Override
    public void consumeBucketToken(APIBucket defaultAPIBucket) throws HttpRetryException {
        defaultAPIBucket.consumeBucket();
    }
}
