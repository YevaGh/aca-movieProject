package aca.demo.movierating.endpoint;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Genre;
import aca.demo.movierating.movie.Movie;
import aca.demo.movierating.movie.UpdateMovie;
import aca.demo.movierating.review.CreateReview;
import aca.demo.movierating.review.Review;
import aca.demo.movierating.review.ReviewService;
import aca.demo.movierating.review.UpdateReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/movies/{movieId}/reviews/")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity create(@RequestBody CreateReview createReview) {
        log.info("create new review for movie {} ",createReview.getMovieId());
        reviewService.create(createReview);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public Review getById(@PathVariable Long movieId, @PathVariable Long id) {
        log.info("Get review by Id");
        return reviewService.getById(id,movieId);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("movieId") Long movieId,@PathVariable("id") Long id,@RequestBody UpdateReview updateReview) {
        log.info("Update review by Id");
        reviewService.update(id,movieId,updateReview);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("movieId") Long movieId,@PathVariable("id") Long id) {
        log.info("Deleting review ");
        reviewService.delete(id,movieId);
    }

    @GetMapping
    public List<Review> search(@RequestParam(required = false) Long movieId,@RequestParam(required = false) String description, @RequestParam(required = false) Instant updatedBefore, @RequestParam(required = false) Instant updatedAfter, @RequestParam(required = false) Long userId,@RequestParam(required = false) double ratingHigherThan,@RequestParam(required = false) double ratingLowerThan) {
        log.info("Search movie by given parameters");
        return reviewService.search(movieId,description, updatedBefore, updatedAfter, userId, ratingHigherThan, ratingLowerThan);

    }
}
