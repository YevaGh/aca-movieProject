package aca.demo.movierating.movie;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MovieService {

   // @Qualifier("MovieRepositoryJpa")
    private final MovieRepository movieRepository;

//    private final MovieRepositorySpringData movieRepositorySpringData;

    @Transactional(readOnly = true)
    public List<Movie> search(Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    @Transactional
    public void create(CreateMovie createMovie) {

        if (movieRepository.findById(createMovie.getId()).isPresent()) {
            throw new IllegalArgumentException();
        }
        movieRepository.persist(new Movie(createMovie));
//        movieRepository.save(new Movie(createMovie));

    }

    @Transactional(readOnly = true)
    public Movie getById(Long id) {
        log.debug("Get movie by id");

        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("No movie with given id"));
    }

    @Transactional
    public void update(Long id, UpdateMovie updateMovie) {
        log.debug("Update movie by id");
        var movie = getById(id);
        movie.update(updateMovie);
        movieRepository.persist(movie);
        //movieRepository.save(new Movie(createMovie));

    }

    @Transactional
    public void delete(Long id) {
        log.debug("Delete movie by id");

        if (movieRepository.findById(id).isPresent()) {
            movieRepository.delete(movieRepository.findById(id).get());
        }else {
            throw new MovieNotFoundException("No movie with given id");
        }
    }

    @Transactional(readOnly = true)
    public List<Movie> search(Genre genre, String title, LocalDate releasedBefore, LocalDate releasedAfter) {
        log.debug("Search movies by genre and/or title and/or released date");

        return movieRepository.search(genre, title, releasedBefore, releasedAfter);

    }


}
