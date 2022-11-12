package org.example.model;

public class Movie {

    private String id;
    private String title;
    private String year;

    public Movie(String id, String title, String year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public static Movie toMovie(org.example.dao.Movie movie) {
        return new Movie(movie.gettConst(), movie.getPrimaryTitle(), String.valueOf(movie.getStartYear()));
    }

}
