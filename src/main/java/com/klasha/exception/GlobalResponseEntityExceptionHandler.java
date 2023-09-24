package com.klasha.exception;

import com.klasha.utils.DataResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        private static final Logger log = LoggerFactory.getLogger(GlobalResponseEntityExceptionHandler.class);

//        @Override
//        protected ResponseEntity<Object> handleMethodArgumentNotValid(
//                MethodArgumentNotValidException ex,
//                HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//                var error = DataResponseUtils.errorResponse("Validation Failed");
//                error.setData(ex.getBindingResult().getFieldErrors());
//                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }


        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
                return new ResponseEntity<>(DataResponseUtils.errorResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleException(Exception e) {
                log.error(e.getMessage(), e);
                return new ResponseEntity<>(DataResponseUtils.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
                return ResponseEntity.badRequest().body(DataResponseUtils.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
        }

}
