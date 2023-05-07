package aca.demo.movierating.endpoint;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    int errorCode;
    String description;
    String field;

}
