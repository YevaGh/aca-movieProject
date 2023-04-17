package aca.demo.movierating.movie;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Genre;
import aca.demo.movierating.movie.Movie;
import aca.demo.movierating.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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


}
