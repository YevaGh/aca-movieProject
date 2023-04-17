package aca.demo.movierating.endpoint;


import aca.demo.movierating.movie.CreateMovie;
import aca.demo.movierating.movie.Genre;
import aca.demo.movierating.movie.Movie;
import aca.demo.movierating.movie.MovieService;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/movies")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        log.info("Title : {}, Genre : {}",request.getParameter("title"),request.getParameter("genre"));
        CreateMovie c = new CreateMovie(request.getParameter("title"), Genre.valueOf(request.getParameter("genre")));
        movieService.create(c);
        response.setStatus(200);
        try {
            response.getWriter().println("New Movie created!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("movies")
    public void search(HttpServletRequest request, HttpServletResponse response) {
        log.info("Genre : {}",request.getParameter("genre"));
        List<Movie> movies = movieService.search(Genre.valueOf(request.getParameter("genre")));
        response.setStatus(200);
        try {
            response.getWriter().println(movies.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}