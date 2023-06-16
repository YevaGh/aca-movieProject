package aca.demo.movierating.review;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    public List<Review> findByRating(double rating);

    public Optional<Review> findById(Long id);

    public void persist(Review review);

    public void delete(Review review);

    public List<Review> search(String description, Instant updatedBefore, Instant updatedAfter, Long userId, Double ratingHigherThan, Double ratingLowerThan);

}
