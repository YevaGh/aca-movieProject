package aca.demo.movierating.review;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.UpdateMovie;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Slf4j
public class Review {
    @EqualsAndHashCode.Include
    private Long id;
    private Long movieId;
    private Long userId;
    private String description;
    private double rating;
    private Instant createdAt;
    private Instant updatedAt;


    public Review(CreateReview createReview) {
        log.debug("Constructing review with createReview - {}", createReview);
        this.movieId = createReview.getMovieId();
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
