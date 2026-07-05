package microservice.exception;

import lombok.Builder;
import java.time.LocalDateTime;


@Builder
public record ErrorResponse(

        boolean success,
        String errorCode,
        String message,
        LocalDateTime timestamp
){}
