package aca.demo.movierating.review;

import aca.demo.movierating.movie.Genre;
import aca.demo.movierating.movie.Movie;
import aca.demo.movierating.movie.MovieRepository;
import aca.demo.movierating.movie.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class ReviewRepository {
    private List<Review> reviews = new ArrayList<>();


    public List<Review> findByRating(double rating){
        log.debug("Finding reviews by rating");
        List<Review> byRating = reviews.stream().filter(g->g.getRating()==rating).collect(Collectors.toList());
        return byRating;
    }
    public Optional<Review> findById(Long id) {
        log.debug("Finding review by id");
        Optional<Review> byId =  reviews.stream().filter(m->m.getId()==id).findAny();

        return byId;
    }

    public void persist(Review review) {
        log.debug("Adding review to the list");

        reviews.add(review);
    }

    public void delete(Review review) {
        log.debug("Deleting review from the list");
        if(findById(review.getId()).isPresent()){
            reviews.remove(review);
        }
    }

    public List<Review> search(double rating, Instant createdBefore, Instant createdAfter) {
        log.debug("Reviews filtered by rating and/or date");
        Stream<Review> reviewsToFilter = reviews.stream();

        if(rating != 0){
            reviewsToFilter = reviewsToFilter.filter(g->g.getRating()==rating);
        }
        if(createdBefore!=null){
            reviewsToFilter = reviewsToFilter.filter(rb-> createdBefore.isBefore(rb.getCreatedAt()));
        }
        if(createdAfter!=null){
            reviewsToFilter = reviewsToFilter.filter(rb-> createdAfter.isAfter(rb.getCreatedAt()));
        }

        return reviewsToFilter.collect(Collectors.toList());
    }

}
