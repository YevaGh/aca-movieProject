package aca.demo.movierating.review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepositorySpringData extends JpaRepository<Review,Long> {
}
