package aca.demo.movierating.endpoint;


import aca.demo.movierating.CreateMovie;
import aca.demo.movierating.Genre;
import aca.demo.movierating.MovieService;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public void create(HttpServletRequest request, HttpServletResponse response) {
        log.info("Title : {}",request.getParameter("title"),"Genre : {}",request.getParameter("genre"));
        CreateMovie c = new CreateMovie(request.getParameter("title"), Genre.valueOf(request.getParameter("genre")));
        movieService.create(c);
        response.setStatus(200);
        try {
            response.getWriter().println("New Movie created!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}