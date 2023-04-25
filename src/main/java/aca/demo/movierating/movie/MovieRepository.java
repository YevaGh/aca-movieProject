package aca.demo.movierating.movie;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class MovieRepository {
    private List<Movie> movies = new ArrayList<>();

    public Optional<Movie> findByTitle(String title) {
        log.debug("Finding movie by title");

       Optional<Movie> byTitle =  movies.stream().filter(m->m.getTitle().equals(title)).findAny();

       return byTitle;
    }

    public Optional<Movie> findById(Long id) {
        log.debug("Finding movie by id");
        Optional<Movie> byId =  movies.stream().filter(m->m.getId()==id).findAny();

        return byId;
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

    public void persist(Movie movie) {
        log.debug("Adding movie to the list");

        movies.add(movie);
    }

    public void delete(Movie movie) {
        log.debug("Deleting movie from the list");
        if(findById(movie.getId()).isPresent()){
            movies.remove(movie);
        }
    }

    public List<Movie> search(Genre genre, String title, LocalDate releasedBefore, LocalDate releasedAfter) {
        log.debug("Movies filtered by genre and/or title and/or released date");
        Stream<Movie> moviesToFilter = null;

        if(genre!= null){
            moviesToFilter = movies.stream().filter(g->g.getGenre()==genre);
        }
        if(title!=null){
            moviesToFilter = moviesToFilter.filter(t->t.getTitle().equals(title));
        }
        if(releasedBefore!=null){
            moviesToFilter = moviesToFilter.filter(rb-> releasedBefore.isBefore(rb.getReleasedAt()));
        }
        if(releasedAfter!=null){
            moviesToFilter = moviesToFilter.filter(rb-> releasedAfter.isAfter(rb.getReleasedAt()));
        }

        List<Movie> filteredMovies = moviesToFilter.collect(Collectors.toList());

        return filteredMovies;
    }

}
