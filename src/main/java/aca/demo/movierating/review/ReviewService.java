package aca.demo.movierating.review;

import aca.demo.movierating.movie.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

   // @Qualifier("ReviewRepositoryJpa")
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;


    public Review getById(Long id,Long movieId) {
        log.info("Get review by id");


        if (reviewRepository.findById(id).isPresent() && reviewRepository.findById(id).get().getMovie().equals(movieService.getById(movieId))) {
            return reviewRepository.findById(id).get();
        } else {
            throw new ReviewNotFoundException("No review with given id");
        }

    }

    public List<Review> getByRating(double rating,Long movieId){
        log.info("get reviews by rating");



        List<Review> byRating = reviewRepository.findByRating(rating).stream().filter(mId->mId.getMovie().equals(movieService.getById(movieId))).collect(Collectors.toList());

        return  byRating;
    }
    public void create(Long movieId,CreateReview createReview) {
        log.info("Create and add review to the list");
        movieService.getById(movieId);

        if (reviewRepository.findById(createReview.getId()).isPresent()) {
            throw new IllegalArgumentException("There is review with given id");
        }
        reviewRepository.persist(new Review(createReview));
    }


    public void update(Long id,Long movieId, UpdateReview updateReview) {
        log.info("Update review by id");

        movieService.getById(movieId);

        if (reviewRepository.findById(id).isPresent() && reviewRepository.findById(id).get().getMovie().equals(movieService.getById(movieId))) {
            reviewRepository.findById(id).get().update(updateReview);
        } else {
            throw new ReviewNotFoundException("No review with given id");
        }
    }

    public void delete(Long id,Long movieId) {
        log.debug("Delete review by id");

        movieService.getById(movieId);

        if (reviewRepository.findById(id).isPresent() && reviewRepository.findById(id).get().getMovie().equals(movieService.getById(movieId))) {
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
