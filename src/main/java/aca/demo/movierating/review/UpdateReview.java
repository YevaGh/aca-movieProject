package aca.demo.movierating.review;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Value
@Builder
@Jacksonized
public class UpdateReview {
    @NotNull
    Long userId;
    String description;
    double rating;
    Instant updatedAt;

}
