package microservice.exception;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse>
    handleCustom(CustomException ex) {
        return ResponseEntity
                .badRequest()
                .body( ErrorResponse.builder()
                                .success(false)
                                .errorCode(ex.getErrorCode().getCode())
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                                .success(false)
                                .errorCode("COMMON_500")
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );


    }


}
