package aca.demo.movierating.movie;

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

        if (movieRepository.findById(createMovie.getId()).isPresent()) {
            throw new IllegalArgumentException();
        }
        movieRepository.persist(new Movie(createMovie));
    }

    public Movie getById(Long id) {
        log.debug("Get movie by id");

        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("No movie with given id"));
    }

    public void update(Long id, UpdateMovie updateMovie) {
        log.debug("Update movie by id");

        if (movieRepository.findById(id).isPresent()) {
            movieRepository.findById(id).get().update(updateMovie);
        }else {
            throw new MovieNotFoundException("No movie with given id");
        }
    }

    public void delete(Long id) {
        log.debug("Delete movie by id");

        if (movieRepository.findById(id).isPresent()) {
            movieRepository.delete(movieRepository.findById(id).get());
        }else {
            throw new MovieNotFoundException("No movie with given id");
        }
    }

    public List<Movie> search(Genre genre, String title, LocalDate releasedBefore, LocalDate releasedAfter) {
        log.debug("Search movies by genre and/or title and/or released date");

        return movieRepository.search(genre, title, releasedBefore, releasedAfter);

    }


}
