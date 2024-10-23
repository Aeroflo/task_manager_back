package com.task_manager.demo.controllers.advice;

import com.task_manager.demo.exceptions.TaskNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Le handler d'exception de l'API,
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * renvoyer un message si aucune tache n'est pas trouvé pqr le biais TaskNotFoundException
     * @param exception l'exception TaskNotFoundException récuperée
     * @return une reponse error construite par apiModel
     */
    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Object> handleTaskNotFound(RuntimeException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(
                status.value(),
                status.name(),
                List.of(exception.getMessage())
        );
        return new ResponseEntity<>(apiError, status);
    }

    /**
     * renvoyer un message si les task en entrées sont invalides
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        HttpStatus requestStatus = HttpStatus.resolve(status.value());
        List<String> error = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();

        ApiError apiError = new ApiError(requestStatus.value(), requestStatus.name(), error);
        return new ResponseEntity<>(apiError, status);
    }
}
