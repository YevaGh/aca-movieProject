package aca.demo.movierating.movie;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Genre;
import aca.demo.movierating.movie.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();

    public Optional<Movie> findByTitle(String title) {
        log.debug("Finding movie by title");

       Optional<Movie> byTitle =  movies.stream().filter(m->m.getTitle().equals(title)).findAny();

       return byTitle;
    }

    public List<Movie> findByGenre(Genre genre) {
        log.debug("Finding movies by genre");

        List<Movie> byGenre =  movies.stream().filter(m->m.getGenre()==genre).collect(Collectors.toList());

        return byGenre;
    }

    public void save(CreateMovie createMovie) {
        log.debug("Creating and adding movie in list");

        movies.add(new Movie(createMovie));
    }
}
