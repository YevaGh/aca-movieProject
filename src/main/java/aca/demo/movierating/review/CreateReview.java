package aca.demo.movierating.review;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Value
@Builder
@Jacksonized
public class CreateReview {
    @NotNull
    Long id;

    @NotNull
    Long userId;

    @NotNull
    String description;

    @Min(0)
    double rating;

    @NotNull
    Instant createdAt;
}
