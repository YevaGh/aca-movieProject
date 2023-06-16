package aca.demo.movierating.review;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Movie;
import aca.demo.movierating.movie.UpdateMovie;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Review {
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private Long userId;
    private String description;
    private double rating;
    private Instant createdAt;
    private Instant updatedAt;


    public Review(CreateReview createReview) {
        log.debug("Constructing review with createReview - {}", createReview);
        this.userId = createReview.getUserId();
        this.id = createReview.getId();
        this.description = createReview.getDescription();
        this.createdAt = createReview.getCreatedAt();
        this.updatedAt = createReview.getCreatedAt();
        this.rating = createReview.getRating();
    }

    public void update(UpdateReview updateReview) {
        log.debug("Updating review with updateReview - {}", updateReview);
        this.userId = updateReview.getUserId();
        this.description = updateReview.getDescription();
        this.updatedAt = updateReview.getUpdatedAt();
        this.rating = updateReview.getRating();
    }
}
