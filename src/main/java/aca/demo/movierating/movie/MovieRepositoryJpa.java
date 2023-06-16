package aca.demo.movierating.movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Primary
@Component
public class MovieRepositoryJpa implements MovieRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Movie> findById(Long id) {
       return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    @Override
    public void persist(Movie movie) {
        if (findById(movie.getId()).isPresent()) {
            entityManager.merge(movie);
        } else {
            entityManager.persist(movie);
        }
    }

    @Override
    public void delete(Movie movie) {
        entityManager.remove(movie);
    }

    @Override
    public List<Movie> findByGenre(Genre genre) {
        var query = "select m from Movie m where genre = :genre ";
        TypedQuery<Movie> managerQuery = entityManager.createQuery(query, Movie.class);
        return managerQuery.getResultList();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        var query = "select m from Movie m where title = :title ";
        TypedQuery<Movie> managerQuery = entityManager.createQuery(query, Movie.class);
        return Optional.ofNullable(managerQuery.getSingleResult());
    }

    @Override
    public List<Movie> search(Genre genre, String title, LocalDate releasedBefore, LocalDate releasedAfter) {
        var query = new StringBuilder();
        query.append("select m from Movie m where 1=1 ");
        if (genre != null) {
            query.append("and m.genre = :genre ");
        }
        if (title != null) {
            query.append("and m.title like :title ");
        }
        if (releasedBefore != null) {
            query.append("and m.releasedAt <= :releasedBefore ");
        }
        if (releasedAfter != null) {
            query.append("and m.releasedAt >= :releasedAfter ");
        }
        TypedQuery<Movie> managerQuery = entityManager.createQuery(query.toString(), Movie.class);
        if (genre != null) {
            managerQuery.setParameter("genre", genre);
        }
        if (title != null) {
            managerQuery.setParameter("title", "%" + title + "%");
        }
        if (releasedBefore != null) {
            managerQuery.setParameter("releasedBefore", releasedBefore);
        }
        if (releasedAfter != null) {
            managerQuery.setParameter("releasedAfter", releasedAfter);
        }
        return managerQuery.getResultList();
    }

}
