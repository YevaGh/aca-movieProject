package aca.demo.movierating.review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Primary
@Component
public class ReviewRepositoryJpa implements ReviewRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Review> findByRating(double rating) {
        var query = "select m from Review m where rating = :rating ";
        TypedQuery<Review> managerQuery = entityManager.createQuery(query, Review.class);
        return managerQuery.getResultList();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Review.class, id));
    }

    @Override
    public void persist(Review review) {
        if (findById(review.getId()).isPresent()) {
            entityManager.merge(review);
        } else {
            entityManager.persist(review);
        }
    }

    @Override
    public void delete(Review review) {
        entityManager.remove(review);
    }

    @Override
    public List<Review> search(String description, Instant updatedBefore, Instant updatedAfter, Long userId, Double ratingHigherThan, Double ratingLowerThan) {
        var query = new StringBuilder();
        query.append("select m from Review m where 1=1");
        if (description != null) {
            query.append("and m.description like :description ");
        }
        if (updatedBefore != null) {
            query.append("and m.updatedAt <= :updatedBefore ");
        }
        if (updatedAfter != null) {
            query.append("and m.updatedAt >= :updatedAfter ");
        }
        if (userId != null) {
            query.append("and m.userId = :userId");
        }
        if (ratingHigherThan != null) {
            query.append("and m.rating >= :ratingHigherThan ");
        }
        if (ratingLowerThan != null) {
            query.append("and m.rating <= :ratingLowerThan ");
        }
        TypedQuery<Review> managerQuery = entityManager.createQuery(query.toString(), Review.class);
        if (description != null) {
            managerQuery.setParameter("description", description);
        }
        if (updatedBefore != null) {
            managerQuery.setParameter("updatedBefore", updatedBefore);
        }
        if (updatedAfter != null) {
            managerQuery.setParameter("updatedAfter", updatedAfter);
        }
        if (userId != null) {
            managerQuery.setParameter("userId", userId);
        }
        if (ratingHigherThan != null) {
            managerQuery.setParameter("ratingHigherThan", ratingHigherThan);
        }
        if (ratingLowerThan != null) {
            managerQuery.setParameter("ratingLowerThan", ratingLowerThan);
        }
        return managerQuery.getResultList();
    }

}
