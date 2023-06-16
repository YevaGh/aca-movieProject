package aca.demo.movierating.movie;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    public Optional<Movie> findById(Long id);

    public void persist(Movie movie);

    void delete(Movie movie);
    public List<Movie> findByGenre(Genre genre);

    public Optional<Movie> findByTitle(String title);

    List<Movie> search(Genre genre, String title, LocalDate releasedBefore, LocalDate releasedAfter);
}
