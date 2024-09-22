package com.ecommerce.inventory.exception;



import com.ecommerce.inventory.dto.Error;
import com.ecommerce.inventory.dto.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.FlowableException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleConstraintViolatedException(ConstraintViolationException constraintViolationException){
        String fieldName = constraintViolationException.getConstraintViolations().stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(path -> path.toString().split("\\.")[1])
                .findFirst()
                .orElse("Unknown field");

        Response response = buildResponse(fieldName,"403");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Response> handleIllegalAccessException(IllegalAccessException illegalAccessException){
        String errorMessage = illegalAccessException.getMessage();
        Response response = buildResponse(errorMessage,"403");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        String errorMessage = illegalArgumentException.getMessage();
        Response response = buildResponse(errorMessage,"403");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Response> handleDuplicateKeyException(DuplicateKeyException exception){
        String errorMessage = exception.getMessage();
        Response response = buildResponse(errorMessage,"403");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(FlowableException.class)
    public ResponseEntity<Response> handleFlowableException(FlowableException exception){
        log.error("{} Stacktrace: " , exception.getMessage(), exception);
        String errorMessage = "Unable to process your request, please try again later.";
        Response response = buildResponse(errorMessage, "500");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    Response buildResponse(String message, String errorCode){
        return  Response.builder()
                .success(false)
                .error(Error.builder()
                        .errorCode(errorCode)
                        .errorMessage(message)
                        .build())
                .build();
    }

}
