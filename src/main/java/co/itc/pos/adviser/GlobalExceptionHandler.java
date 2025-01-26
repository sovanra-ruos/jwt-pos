package co.itc.pos.adviser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
//        Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
//        errorDetails.put("message", ex.getMessage());
//
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("error", errorDetails);
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.CONFLICT.value());
        errorDetails.put("message", ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errorDetails);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("message", "Validation failed");

        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );
        errorDetails.put("errors", validationErrors);

        log.info("Error: {}", ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errorDetails);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
