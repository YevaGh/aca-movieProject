package aca.demo.movierating.review;

import aca.demo.movierating.movie.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;


    public Review getById(Long id,Long movieId) {
        log.info("Get review by id");
        movieService.getById(movieId);

        if (reviewRepository.findById(id).isPresent() && reviewRepository.findById(id).get().getMovieId().equals(movieId)) {
            return reviewRepository.findById(id).get();
        } else {
            throw new ReviewNotFoundException("No review with given id");
        }

    }

    public List<Review> getByRating(double rating,Long movieId){
        log.info("get reviews by rating");

        movieService.getById(movieId);

        List<Review> byRating = reviewRepository.findByRating(rating).stream().filter(mId->mId.getMovieId().equals(movieId)).collect(Collectors.toList());

        return  byRating;
    }
    public void create(CreateReview createReview) {
        log.info("Create and add review to the list");
        movieService.getById(createReview.getMovieId());

        if (reviewRepository.findById(createReview.getId()).isPresent()) {
            throw new IllegalArgumentException("There is review with given id");
        }
        reviewRepository.persist(new Review(createReview));
    }


    public void update(Long id,Long movieId, UpdateReview updateReview) {
        log.info("Update review by id");

        movieService.getById(movieId);

        if (reviewRepository.findById(id).isPresent() && reviewRepository.findById(id).get().getMovieId().equals(movieId)) {
            reviewRepository.findById(id).get().update(updateReview);
        } else {
            throw new ReviewNotFoundException("No review with given id");
        }
    }

    public void delete(Long id,Long movieId) {
        log.debug("Delete review by id");

        movieService.getById(movieId);

        if (reviewRepository.findById(id).isPresent() && reviewRepository.findById(id).get().getMovieId().equals(movieId)) {
            reviewRepository.delete(reviewRepository.findById(id).get());
        } else {
            throw new ReviewNotFoundException("No review with given id");
        }
    }

    public List<Review> search(Long movieId,String description,Instant updatedBefore, Instant updatedAfter,Long userId,double ratingHigherThan,double ratingLowerThan) {
        log.debug("Search reviews by description and/or update date and/or rating and/or user Id");
        movieService.getById(movieId);

        return reviewRepository.search(description, updatedBefore, updatedAfter,userId,ratingHigherThan,ratingLowerThan);

    }
}
