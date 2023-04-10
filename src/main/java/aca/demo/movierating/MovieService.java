package aca.demo.movierating;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> search(Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    public void create(CreateMovie createMovie) {
        if(movieRepository.findByTitle(createMovie.getTitle()).equals(Optional.empty())){
           throw new IllegalArgumentException();
        }
        movieRepository.save(createMovie);
    }

    
}
