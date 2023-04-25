package aca.demo.movierating.movie;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Genre;
import aca.demo.movierating.movie.Movie;
import aca.demo.movierating.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> search(Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    public void create(CreateMovie createMovie) {
        if(movieRepository.findByTitle(createMovie.getTitle()).isPresent()){
           throw new IllegalArgumentException();
        }
        movieRepository.save(createMovie);
    }

    public Movie getById(Long id) {
        log.debug("Get movie by id");

        if (movieRepository.findById(id)!=null){
            return movieRepository.findById(id).get();
        }
        throw new MovieNotFoundException("No movie with given id");

    }

    public void update(Long id, UpdateMovie updateMovie) {
        log.debug("Update movie by id");

        if (movieRepository.findById(id)!=null){
            movieRepository.findById(id).get().update(updateMovie);
        }
        throw new MovieNotFoundException("No movie with given id");
    }

    public void delete(Long id) {
        log.debug("Delete movie by id");

        if (movieRepository.findById(id)!=null){
            movieRepository.delete(movieRepository.findById(id).get());
        }
        throw new MovieNotFoundException("No movie with given id");
    }

    public List<Movie> search(Genre genre, String title, LocalDate releasedBefore, LocalDate releasedAfter){
        log.debug("Search movies by genre and/or title and/or released date");

        return movieRepository.search(genre,title,releasedBefore,releasedAfter);

    }

}
