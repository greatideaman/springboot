package com.att.exceptionHandling;

import com.att.web.configuarations.ConfigurationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Achille on 10/5/2018.
 */
@ControllerAdvice
public class ErrorHandlingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(Exception excception) {
        ExceptionResponse ex = new ExceptionResponse();
        ex.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ex.setDescription(excception.getMessage());
        LOGGER.error(excception.getMessage(),excception.getStackTrace());
        return new ResponseEntity<ExceptionResponse>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConfigurationException.class)
    public ResponseEntity<ExceptionResponse> configurationlException(Exception excception) {
        ExceptionResponse ex = new ExceptionResponse();
        ex.setCode(HttpStatus.BAD_REQUEST.value());
        ex.setDescription(excception.getMessage());
        LOGGER.error(excception.getMessage(),excception.getStackTrace());

        return new ResponseEntity<ExceptionResponse>(ex, HttpStatus.BAD_REQUEST);
    }
}


