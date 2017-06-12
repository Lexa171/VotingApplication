package by.ots.controller;

import by.ots.exception.ConflictException;
import by.ots.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlingController {

    private Logger logger;

    public GlobalExceptionHandlingController() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Conflict of values")
    @ExceptionHandler(ConflictException.class)
    public void conflict() {
        logger.error("Request raised a ConflictException");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
    @ExceptionHandler(NotFoundException.class)
    public void notFound() {
        logger.error("Request raised a NotFoundException");
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void integrityViolation() {
        logger.error("Request raised a DataIntegrityViolationException");
    }

}
