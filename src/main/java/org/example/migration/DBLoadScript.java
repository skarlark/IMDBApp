package org.example.migration;

import org.example.dao.Actor;
import org.example.dao.Appearance;
import org.example.dao.Movie;
import org.example.repository.ActorRepository;
import org.example.repository.AppearanceRepository;
import org.example.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBLoadScript {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private AppearanceRepository appearanceRepository;

    public void loadMovieDatabase() {
        Path path = Paths.get("C:\\Users\\jusan\\OneDrive\\Documents\\title.basics.tsv");
        List<Movie> filteredMovies = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] info = line.split("\\t");
                int startYear = info[5].equals("\\N") ? 0 : Integer.parseInt(info[5]);
                line = bufferedReader.readLine();
                if (info[3].length() > 200 || startYear < 2018)
                    continue;
                filteredMovies.add(new Movie(info[0], info[1], info[2], info[3], info[4].equals("0"), startYear, info[6].equals("\\N") ? 0 : Integer.parseInt(info[6]), info[7].equals("\\N") ? 0 : Integer.parseInt(info[7])));
                if (filteredMovies.size() == 2000000) {
                    movieRepository.saveAll(filteredMovies);
                    filteredMovies = new ArrayList<>();
                }
            }
            if (filteredMovies.size() > 0) {
                movieRepository.saveAll(filteredMovies);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void loadActorTable() {
        Path path = Paths.get("C:\\Users\\jusan\\OneDrive\\Documents\\name.basics.tsv");
        List<Actor> actors = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] info = line.split("\\t");
                line = bufferedReader.readLine();
                actors.add(new Actor(info[0], info[1], info[2].equals("\\N") ? 0 : Integer.parseInt(info[2]), info[3].equals("\\N") ? 0 : Integer.parseInt(info[3])));
                if (actors.size() == 2000000) {
                    actorRepository.saveAll(actors);
                    actors = new ArrayList<>();
                }
            }
            if (actors.size() > 0) {
                actorRepository.saveAll(actors);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void loadAppearanceTable() {
        Path path = Paths.get("C:\\Users\\jusan\\OneDrive\\Documents\\title.principals.tsv");
        List<Appearance> appearances = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] info = line.split("\\t");
                line = bufferedReader.readLine();
                String characters = info[5].equals("\\N") ? "" : (info[5]);
                if (characters.length() > 300)
                    continue;
                appearances.add(new Appearance("", info[0], info[2], characters));
                if (appearances.size() == 2000000) {
                    appearanceRepository.saveAll(appearances);
                    appearances = new ArrayList<>();
                }
            }
            if (appearances.size() > 0) {
                appearanceRepository.saveAll(appearances);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
