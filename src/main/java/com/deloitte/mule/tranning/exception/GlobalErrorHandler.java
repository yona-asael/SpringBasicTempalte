package com.deloitte.mule.tranning.exception;

import com.deloitte.mule.tranning.dto.ResponseDTO;
import com.deloitte.mule.tranning.services.ResponseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> exception(ExceptionHandler ex, WebRequest request) {
        log.error("Handling Exception");
        return new ResponseEntity<ResponseDTO<String>>(
                responseService.createResponse("", List.of("INTERNAL::ER"), NOT_FOUND), NOT_FOUND
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> notFoundException(NotFoundException ex, WebRequest request) {
        log.error("Handling Not Found Exception");
        return new ResponseEntity<ResponseDTO<String>>(
                responseService.createResponse("", List.of(ex.message), NOT_FOUND), NOT_FOUND
        );
    }

    @ExceptionHandler(NoParametersException.class)
    public ResponseEntity<ResponseDTO<String>> noParametersException(NoParametersException ex, WebRequest request) {
        log.error("Handling No Parameters Exception");
        return new ResponseEntity<ResponseDTO<String>>(
                responseService.createResponse("", List.of(ex.message), BAD_REQUEST), BAD_REQUEST
        );
    }

}
