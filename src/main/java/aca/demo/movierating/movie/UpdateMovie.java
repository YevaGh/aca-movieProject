package aca.demo.movierating.movie;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder
@Jacksonized
public class UpdateMovie {
    @NotNull
    String title;
    Genre genre;
    LocalDate releasedAt;
    String director;
    double rating;
}
