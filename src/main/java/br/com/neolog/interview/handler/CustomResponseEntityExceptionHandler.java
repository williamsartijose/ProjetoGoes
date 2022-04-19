package br.com.neolog.interview.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class CustomResponseEntityExceptionHandler
    extends
        ResponseEntityExceptionHandler
{

    @ExceptionHandler( Exception.class )
    ResponseEntity<Object> handleAll(
        final Exception ex )
    {
        final List<Error> errors = new ArrayList<>();
        errors.add( new Error( ex.getLocalizedMessage() ) );
        return ResponseEntity.badRequest().body( new ErrorResponseBody( errors ) );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex,
        final HttpHeaders headers,
        final HttpStatus status,
        final WebRequest request )
    {
        final List<Error> errors = new ArrayList<>();

        for( final FieldError error : ex.getBindingResult().getFieldErrors() ) {
            final String message = String.format( "%s: %s", error.getField(),
                error.getDefaultMessage() );
            errors.add( new Error( message ) );
        }
        for( final ObjectError error : ex.getBindingResult().getGlobalErrors() ) {
            final String message = String.format( "%s: %s", error.getObjectName(),
                error.getDefaultMessage() );
            errors.add( new Error( message ) );
        }
        return handleExceptionInternal( ex, new ErrorResponseBody( errors ), headers, status,
            request );
    }
}
