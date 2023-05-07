package aca.demo.movierating.endpoint;

import aca.demo.movierating.movie.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(MovieNotFoundException m) {
        log.error("handled MovieNotFoundException error");
        ErrorResponse err = new ErrorResponse(1001,m.getMessage(),null);
        return ResponseEntity.status(400).body(err);
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(IllegalArgumentException i) {
        log.error("handled IllegalArgumentException error");
        ErrorResponse err = new ErrorResponse(1002,i.getMessage(),null);
        return ResponseEntity.status(400).body(err);
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handle(RuntimeException r) {
        log.error("handled IllegalArgumentException error");
        ErrorResponse err = new ErrorResponse(1100,r.getMessage(),null);
        return ResponseEntity.status(500).body(err);
    }

}
