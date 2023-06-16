package aca.demo.movierating.movie;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder
@Jacksonized
public class CreateMovie {
    @NotNull
    Long id;

    @NotNull
    @Max(255)
    String title;

    @NotNull
    Genre genre;

    @NotNull
    LocalDate releasedAt;

    @NotNull
    String director;

    @Min(0)
    double rating;

}