package aca.demo.movierating.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Movie {
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private LocalDate releasedAt;
    private String director;
    private double rating;


    public Movie(CreateMovie createMovie) {
        log.debug("Constructing movie with createMovie - {}", createMovie);
        this.title = createMovie.getTitle();
        this.genre = createMovie.getGenre();
        this.id = createMovie.getId();
        this.releasedAt = createMovie.getReleasedAt();
        this.director = createMovie.getDirector();
        this.rating = createMovie.getRating();
    }


    public void update(UpdateMovie updateMovie) {
        log.debug("Updating movie with updateMovie - {}", updateMovie);
        this.title = updateMovie.getTitle();
        this.genre = updateMovie.getGenre();
        this.releasedAt = updateMovie.getReleasedAt();
        this.director = updateMovie.getDirector();
        this.rating = updateMovie.getRating();
    }
}
