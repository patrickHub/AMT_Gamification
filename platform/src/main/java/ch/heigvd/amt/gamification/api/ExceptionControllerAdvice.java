package ch.heigvd.amt.gamification.api;

import ch.heigvd.amt.gamification.errors.ErrorMessageGenerator;
import ch.heigvd.amt.gamification.errors.HttpErrorResponse;
import ch.heigvd.amt.gamification.errors.HttpStatusException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.http.HTTPException;


/**
 * @author Christopher Browne
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {org.springframework.dao.DataIntegrityViolationException.class})
    protected ResponseEntity handleDataIntegrityViolation(RuntimeException ex, WebRequest request) {
        HttpErrorResponse err = new HttpErrorResponse();
        err.setCode(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Bad request");
        return new ResponseEntity<HttpErrorResponse>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpStatusException.class})
    protected ResponseEntity handleHttpStatus(HttpStatusException ex) {
        HttpErrorResponse err = new HttpErrorResponse();
        err.setCode(ex.getStatus().value());
        err.setMessage(ex.getMessage());
        return new ResponseEntity<HttpErrorResponse>(err, ex.getStatus());
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity handleNullPointer(NullPointerException ex) {
        HttpErrorResponse err = new HttpErrorResponse();
        err.setCode(HttpStatus.NOT_FOUND.value());
        err.setMessage("Ressource not found");
        return new ResponseEntity<HttpErrorResponse>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {org.springframework.dao.EmptyResultDataAccessException.class})
    protected ResponseEntity handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
        HttpErrorResponse err = new HttpErrorResponse();
        err.setCode(HttpStatus.NOT_FOUND.value());
        err.setMessage("Ressource not found");
        return new ResponseEntity<HttpErrorResponse>(err, HttpStatus.NOT_FOUND);
    }
}