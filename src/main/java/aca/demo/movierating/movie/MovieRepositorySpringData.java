package aca.demo.movierating.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepositorySpringData extends JpaRepository<Movie,Long> {

    List<Movie> findByTitleAndGenre(String title,Genre genre);
    @Query("select m Movie m where m.director = :director")
    List<Movie> getForDirector(@Param("director") String director);

//    @Query("select m Movie m where m.title = :title and m.")
//    List<Movie> searchByParams(@Param("director") String director);
}
