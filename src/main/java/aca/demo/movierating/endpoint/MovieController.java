package aca.demo.movierating.endpoint;


import aca.demo.movierating.movie.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("movies")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MovieController {
    private final MovieService movieService;


    @GetMapping("{id}")
    public Movie getById(@PathVariable Long id) {
        log.info("Get movies by Id");
        return movieService.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateMovie createMovie) {
        log.info("create new movie title {}",createMovie.getTitle());
        movieService.create(createMovie);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id,@RequestBody UpdateMovie updateMovie) {
        log.info("Update movies by Id");
        movieService.update(id,updateMovie);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        log.info("Deleting movie ");
        movieService.delete(id);
    }
    @GetMapping
    public List<Movie> search(@RequestParam(required = false) Genre genre,@RequestParam(required = false) String title,@RequestParam(required = false) LocalDate releasedBefore,@RequestParam(required = false) LocalDate releasedAfter) {
        log.info("Search movie by given parameters");
        return movieService.search(genre, title, releasedBefore, releasedAfter);

    }

}