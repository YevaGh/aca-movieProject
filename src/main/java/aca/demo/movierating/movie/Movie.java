package aca.demo.movierating.movie;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Genre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@EqualsAndHashCode
@Slf4j
public class Movie {
    private String title;
    private Genre genre;

    Movie(CreateMovie createMovie) {
        log.debug("Constructing movie with createMovie - {}",createMovie);
        this.title= createMovie.getTitle();
        this.genre=createMovie.getGenre();
    }
}
