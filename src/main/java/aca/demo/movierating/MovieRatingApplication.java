package aca.demo.movierating;

import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static aca.demo.movierating.movie.Genre.*;

@SpringBootApplication
@Slf4j
@ConfigurationProperties(prefix = "application")

public class MovieRatingApplication {
	public static void main(String[] args) {

		var applicationContext = SpringApplication.run(MovieRatingApplication.class, args);

		var movieService = applicationContext.getBean(MovieService.class);

//		CreateMovie c1 = new CreateMovie("Forrest Gump",DRAMA);
//		CreateMovie c2 = new CreateMovie("American Beauty",DRAMA);
//		CreateMovie c3 = new CreateMovie("Horrible Bosses",COMEDY);

//		movieService.create(c1);
//		movieService.create(c2);
//		movieService.create(c3);

		log.debug(movieService.search(DRAMA).toString());
		log.debug(movieService.search(ROMANCE).toString());
		log.debug(movieService.search(COMEDY).toString());


	}

}
