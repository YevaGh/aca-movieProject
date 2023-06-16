package aca.demo.movierating.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class ReviewRepositoryInMemory implements ReviewRepository {
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

    public List<Review> search(String description,Instant updatedBefore, Instant updatedAfter,Long userId,Double ratingHigherThan,Double ratingLowerThan) {
        log.debug("Reviews filtered by rating and/or date");
        Stream<Review> reviewsToFilter = reviews.stream();

        if(description != null){
            reviewsToFilter = reviewsToFilter.filter(g->g.getDescription().equals(description));
        }
        if(updatedBefore!=null){
            reviewsToFilter = reviewsToFilter.filter(ub-> updatedBefore.isBefore(ub.getUpdatedAt()));
        }
        if(updatedAfter!=null){
            reviewsToFilter = reviewsToFilter.filter(ua-> updatedAfter.isAfter(ua.getUpdatedAt()));
        }
        if(userId!=null){
            reviewsToFilter = reviewsToFilter.filter(uId-> uId.getUserId().equals(userId));
        }
        if(ratingHigherThan != null){
            reviewsToFilter = reviewsToFilter.filter(rh-> rh.getRating()>ratingHigherThan);
        }
        if(ratingLowerThan != null){
            reviewsToFilter = reviewsToFilter.filter(rl-> rl.getRating()<ratingLowerThan);
        }

        return reviewsToFilter.collect(Collectors.toList());
    }

}
